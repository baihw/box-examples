/*
 * Copyright (c) 2019-present, wee0.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wee0.box.examples.multiModule.action;

import com.wee0.box.BoxConfig;
import com.wee0.box.beans.annotation.BoxInject;
import com.wee0.box.cache.CacheManager;
import com.wee0.box.examples.multiModule.module1.api.IWxApi;
import com.wee0.box.examples.multiModule.module1.dao.SysUserDao;
import com.wee0.box.examples.multiModule.module1.entity.SysUserEntity;
import com.wee0.box.exception.BizExceptionFactory;
import com.wee0.box.log.ILogger;
import com.wee0.box.log.LoggerFactory;
import com.wee0.box.subject.*;
import com.wee0.box.subject.annotation.BoxRequireIgnore;
import com.wee0.box.util.shortcut.CheckUtils;
import com.wee0.box.util.shortcut.ValidateUtils;
import com.wee0.box.web.annotation.BoxAction;
import com.wee0.box.web.annotation.BoxParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2019/9/1 22:50
 * @Description 系统用户操作
 * <pre>
 * 补充说明
 * </pre>
 **/
@BoxAction
public class User {

    // 日志对象
    private static ILogger log = LoggerFactory.getLogger(User.class);

    @BoxInject
    private SysUserDao sysUserDao;

    @BoxInject
    private IWxApi wxApi;

    /**
     * @return 当前登陆用户信息
     */
    public SysUserEntity getUserInfo() {
        Map<String, Object> _r = wxApi.getUserInfo(BoxConfig.impl().get("box.wx.testUnionId"), BoxConfig.impl().get("box.wx.testAccessToken"));
        log.debug("r: {}", _r);
        ISubject _subject = SubjectContext.getSubject();
        if (!_subject.isLogin())
            return null;
        return sysUserDao.findById(_subject.getId());
    }

    // 获取手机验证码
    @BoxRequireIgnore
    public String getCode(@BoxParam(pattern = "*6-12", message = "手机号必须在6到12位。") String mobile) {
        if (!ValidateUtils.impl().validatePattern(mobile, "m"))
            BizExceptionFactory.create(BoxConfig.impl().getConfigObject().getSystemErrorInfoBizCode(), "手机号码不合法！");
        // 根据手机号查询用户是否存在？同时取得用户唯一标识。
        // 如果手机号不存在，提示用户手机号不存在。
        // 如果手机号存在，生成验证码，发送给手机，然后根据规则，存入用户唯一标识。
        // 生成的验证码
        String _code = "1234";
        // 生成缓存键：配置的验证码前缀 + _ + 手机号 + _ + 验证码
        String _cacheKey = "BoxQueryCode_" + "_" + mobile + "_" + _code;
        // 手机号对应的用户唯一标识
        String _cacheVal = "0a19ba58e8ab11e9b3700242ac12010a";
        // 存入缓存中，5分钟内有效。
        CacheManager.impl().getDefaultCache().put(_cacheKey, _cacheVal, 300);
        // 测试需要，所以返回验证码。
        return _code;
    }

    // 手机验证码登陆
    @BoxRequireIgnore
    public String mobileLogin(String mobile, String code) {
        if (!ValidateUtils.impl().validatePattern(mobile, "m"))
            BizExceptionFactory.create(BoxConfig.impl().getConfigObject().getSystemErrorInfoBizCode(), "手机号码不合法！");
        String _cacheKey = "BoxQueryCode_" + "_" + mobile + "_" + code;
        if (!CacheManager.impl().getDefaultCache().exists(_cacheKey)) {
            // 验证码不存在，或者已过期。
            return "invalid code: " + code;
        }
        ISubject _subject = SubjectContext.getSubject();
        if (_subject.isLogin()) {
            log.debug("already login {}.", _subject);
            return _subject.getToken();
        }
        IPasswordToken _passwordToken = SubjectContext.getTokenFactory().createPasswordToken(mobile, code);

        try {
            _subject.login(_passwordToken);
            log.debug("login: {}", _subject);
            CacheManager.impl().getDefaultCache().remove(_cacheKey);
            return _subject.getToken();
        } catch (RuntimeException e) {
            //登出（清除cookie)
            return e.getMessage();
        }

    }

    /**
     * 通过自定义认证域登陆
     *
     * @return
     */
    @BoxRequireIgnore
    public String customLogin(String code, HttpServletRequest request, HttpServletResponse response) {
        log.debug("customLogin... code: {}", code);
        log.debug("SubjectContext.impl: {}", SubjectContext.impl());
        log.debug("tokenFactory: {}", SubjectContext.getTokenFactory());
        ICustomToken _token = SubjectContext.getTokenFactory().createCustomToken("0a19ba58e8ab11e9b3700242ac12010a", "xx");
        doWebLogin(_token, request, response);
        return "custom...";
    }

    /**
     * @return 获取微信登陆地址
     */
    @BoxRequireIgnore
    public String getWxLoginUrl() {
        final String _callbackUrl = BoxConfig.impl().get("box.wx.testCallbackUrl");
        return wxApi.getPcLoginUrl(_callbackUrl);
    }

    public boolean wxLogin(HttpServletRequest request, HttpServletResponse response) {
        String _code = CheckUtils.checkNotTrimEmpty(request.getParameter("code"), "code cannot be empty!");
        log.debug("_code: {}", _code);

        //:todo 使用 code 去微信服务器获取token。
        //:todo 使用 token 去微信服务器获取用户头像与 unionId 等信息。
        //:todo 使用 unionId 获取用户唯一标识。
        //:todo 如果用户不存在，执行创建用户流程。
        //:todo 如果用户存在，登陆成功，返回用户绑定的手机号等信息。

//        String _userId = SqlHelper.impl().queryScalar(this.queryUser1, new Object[]{_unionId}, String.class);
//        if (null == _userId || 0 == (_userId = _userId.trim()).length())
//            throw new IncorrectCredentialsException("认证失败! Invalid unionId: " + _unionId);

        ICustomToken _token = SubjectContext.getTokenFactory().createCustomToken("0a19ba58e8ab11e9b3700242ac12010a", "xx");
        return doWebLogin(_token, request, response);
    }

    // 账号密码登陆
    public boolean login(HttpServletRequest request, HttpServletResponse response) {
        String loginId = request.getParameter("loginId");
        String loginPwd = request.getParameter("loginPwd");
        if (!ValidateUtils.impl().validatePattern(loginId, "S3-16"))
            throw BizExceptionFactory.create(BoxConfig.impl().getConfigObject().getSystemErrorInfoBizCode(), "登陆标识不合法！");
        IPasswordToken _passwordToken = SubjectContext.getTokenFactory().createPasswordToken(loginId, loginPwd);
        return doWebLogin(_passwordToken, request, response);
    }

    public boolean logout(HttpServletRequest request, HttpServletResponse response) {
        ISubject _subject = SubjectContext.getSubject();
        _subject.logout(request, response);
        return true;
    }

    private boolean doWebLogin(IToken token, HttpServletRequest request, HttpServletResponse response) {
        ISubject _subject = SubjectContext.getSubject();
        if (_subject.isLogin()) {
            log.debug("already login {}.", _subject);
            return true;
        }
        try {
            _subject.login(token, request, response);
        } catch (RuntimeException e) {
            //登出（清除cookie)
            log.warn("login error!", e);
            _subject.logout(request, response);
            return false;
        }
        log.debug("login: {}", _subject);
        return true;
    }


}
