package com.wee0.box.examples.multiModule.module1.impl;

import com.wee0.box.BoxConfig;
import com.wee0.box.beans.annotation.BoxBean;
import com.wee0.box.examples.multiModule.module1.api.IWxApi;
import com.wee0.box.log.ILogger;
import com.wee0.box.log.LoggerFactory;
import com.wee0.box.util.IHttpUtils;
import com.wee0.box.util.shortcut.CheckUtils;
import com.wee0.box.util.shortcut.HttpUtils;
import com.wee0.box.util.shortcut.JsonUtils;
import com.wee0.box.util.shortcut.MapUtils;

import java.util.Map;
import java.util.UUID;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2019/9/22 7:12
 * @Description 一个微信Api相关操作的简单实现
 * <pre>
 * 补充说明
 * </pre>
 **/
@BoxBean
public class SimpleWxApi implements IWxApi {

    // 日志对象
    private static ILogger log = LoggerFactory.getLogger(SimpleWxApi.class);

    private final String _APP_ID = BoxConfig.impl().get("box.wx.appId");
    private final String _APP_SECRET = BoxConfig.impl().get("box.wx.appSecret");

    private final String _TEST_ACCESS_TOKEN = BoxConfig.impl().get("box.wx.testAccessToken");
    private final String _TEST_REFRESH_TOKEN = BoxConfig.impl().get("box.wx.testRefreshToken");
    private final String _TEST_OPEN_ID = BoxConfig.impl().get("box.wx.testOpenId");
    private final String _TEST_UNION_ID = BoxConfig.impl().get("box.wx.testUnionId");

    @Override
    public String getPcLoginUrl(String callbackUrl) {
        final String _STATE = UUID.randomUUID().toString();
        StringBuilder _builder = new StringBuilder();
        _builder.append("https://open.weixin.qq.com/connect/qrconnect?appid=").append(_APP_ID);
        _builder.append("&redirect_uri=").append(callbackUrl);
        _builder.append("&response_type=code&scope=snsapi_login&state=").append(_STATE);
        _builder.append("#wechat_redirect");
        return _builder.toString();
    }

    @Override
    public Map<String, Object> getTokenByCode(String code) {
        final String _URL_TEMPLATE = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
        String _url = String.format(_URL_TEMPLATE, _APP_ID, _APP_SECRET, code);
        IHttpUtils.IHttpResult _httpResult = HttpUtils.impl().httpGet(_url);
        // 失败：errcode, errmsg
        // 成功：access_token, refresh_token, openid, unionid, scope, expires_in
        return _parseWxResult(_httpResult);
    }

    @Override
    public Map<String, Object> refreshToken(String refreshToken) {
        String _url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";
        _url = String.format(_url, _APP_ID, _TEST_REFRESH_TOKEN);

        IHttpUtils.IHttpResult _httpResult = HttpUtils.impl().httpGet(_url);
        // 失败：errcode, errmsg
        // 成功：access_token, refresh_token, openid, scope, expires_in
        return _parseWxResult(_httpResult);
    }

    @Override
    public Map<String, Object> getUserInfo(String wxId, String accessToken) {
        String _url = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
        _url = String.format(_url, accessToken, wxId);

        IHttpUtils.IHttpResult _httpResult = HttpUtils.impl().httpGet(_url);
        // 失败：errcode, errmsg
        // 成功：unionid, openid, nickname, sex, headimgurl, country, province, city, language, privilege,
        return _parseWxResult(_httpResult);
    }

    // 解析微信返回数据
    private static Map<String, Object> _parseWxResult(IHttpUtils.IHttpResult httpResult) {
        log.trace("httpResult: {}", httpResult);
        if (null == httpResult || httpResult.getCode() >= 400) {
            log.warn("Invalid httpResult: {}", httpResult);
            throw new IllegalStateException("Invalid http result!");
        }
        Map<String, Object> _result = JsonUtils.readToMap(httpResult.getContent());
        log.trace("_result: {}", _result);
        // 失败：errcode, errmsg
        if (_result.containsKey("errcode")) {
//            String _errCode = String.valueOf(_jsonResult.get("errcode"));
//            if (!"0".equals(_errCode))
            throw new IllegalStateException("validate error code:" + _result.get("errcode") + ", msg:" + _result.get("errmsg"));
        }
        return _result;
    }

}
