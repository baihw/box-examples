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

import com.wee0.box.beans.annotation.BoxInject;
import com.wee0.box.code.BizCodeDef;
import com.wee0.box.examples.multiModule.module1.dao.SysUserDao;
import com.wee0.box.examples.multiModule.module1.entity.SysUserEntity;
import com.wee0.box.exception.BizExceptionFactory;
import com.wee0.box.exception.BoxRuntimeException;
import com.wee0.box.log.ILogger;
import com.wee0.box.log.LoggerFactory;
import com.wee0.box.subject.IPasswordToken;
import com.wee0.box.subject.ISubject;
import com.wee0.box.subject.SubjectContext;
import com.wee0.box.subject.annotation.BoxRequireLogical;
import com.wee0.box.subject.annotation.BoxRequirePermissions;
import com.wee0.box.subject.annotation.BoxRequireRoles;
import com.wee0.box.util.shortcut.ValidateUtils;
import com.wee0.box.web.annotation.BoxAction;

import java.util.List;

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

    @BoxRequireRoles("admin")
    public List<SysUserEntity> queryAllUsers() {
        return sysUserDao.queryAll();
    }

    @BoxRequireRoles(value = {"admin", "guest"}, logical = BoxRequireLogical.OR)
    public List<SysUserEntity> findAllUsers() {
        return sysUserDao.findAll();
    }

    @BoxRequirePermissions("common_delete")
    public int deleteById(String userId) {
        return sysUserDao.deleteById(userId);
    }

    public boolean login(String loginId, String loginPwd) {
        if (!ValidateUtils.impl().validatePattern(loginId, "S6-16"))
            BizExceptionFactory.create(BizCodeDef.ValidateFailed, "登陆标识不合法！");
        ISubject _subject = SubjectContext.getSubject();
        IPasswordToken _passwordToken = SubjectContext.getTokenFactory().createPasswordToken(loginId, loginPwd);
//        try {
        _subject.login(_passwordToken);
//        } catch (Exception e) {
//            throw new BoxRuntimeException("登陆失败！", e);
//        }
        return true;
    }

    public boolean logout() {
        ISubject _subject = SubjectContext.getSubject();
        _subject.logout();
        return true;
    }

    public String ex1() {
        throw BizExceptionFactory.create();
    }

}
