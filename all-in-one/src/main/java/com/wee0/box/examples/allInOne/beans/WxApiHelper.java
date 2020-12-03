package com.wee0.box.examples.allInOne.beans;

import com.wee0.box.BoxConfig;
import com.wee0.box.IBoxConfig;
import com.wee0.box.beans.annotation.BoxBean;
import com.wee0.box.log.ILogger;
import com.wee0.box.log.LoggerFactory;
import com.wee0.box.subject.IAuthorizationInfoProvider;
import com.wee0.box.util.IHttpUtils;
import com.wee0.box.util.shortcut.CheckUtils;
import com.wee0.box.util.shortcut.HttpUtils;
import com.wee0.box.util.shortcut.JsonUtils;
import com.wee0.box.util.shortcut.MapUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.IniSecurityManagerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2019/9/22 7:12
 * @Description 微信相关操作助手
 * <pre>
 * 补充说明
 * </pre>
 **/
@BoxBean
public class WxApiHelper {

    // 日志对象
    private static ILogger log = LoggerFactory.getLogger(WxApiHelper.class);

    private final String _APP_ID = BoxConfig.impl().get("box.wx.appId");
    private final String _APP_SECRET = BoxConfig.impl().get("box.wx.appSecret");
    private final String _TEST_ACCESS_TOKEN = BoxConfig.impl().get("box.wx.testAccessToken");
    private final String _TEST_REFRESH_TOKEN = BoxConfig.impl().get("box.wx.testRefreshToken");
    private final String _TEST_OPEN_ID = BoxConfig.impl().get("box.wx.testOpenId");
    private final String _TEST_UNION_ID = BoxConfig.impl().get("box.wx.testUnionId");

    // 获取token
    void testGetToken(String code) {
        String _url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
        _url = String.format(_url, _APP_ID, _APP_SECRET, code);
        IHttpUtils.IHttpResult _httpResult = HttpUtils.impl().httpGet(_url);
        log.debug("httpResult: {}", _httpResult);
        if (null == _httpResult || _httpResult.getCode() >= 400) {
            log.warn("Invalid httpResult: {}", _httpResult);
            throw new IllegalStateException("Invalid http result!");
        }
        Map<String, Object> _jsonResult = JsonUtils.readToMap(_httpResult.getContent());
        log.trace("jsonResult: {}", _jsonResult);
        if (null == _jsonResult || _jsonResult.isEmpty())
            throw new IllegalStateException("Invalid json result!");
        if (_jsonResult.containsKey("errcode")) {
//            String _errCode = String.valueOf(_jsonResult.get("errcode"));
//            if (!"0".equals(_errCode))
            throw new IllegalStateException("validate error code:" + _jsonResult.get("errcode") + ", msg:" + _jsonResult.get("errmsg"));
        }
        String _unionId = CheckUtils.checkTrimEmpty(MapUtils.getString(_jsonResult, "unionid"), null);
        if (null == _unionId) {
//            String _openId = CheckUtils.checkTrimEmpty(MapUtils.getString(_jsonResult, "openid"), null);
//            if (null == _openId)
//                throw new IllegalStateException("openid not found!");
            throw new IllegalStateException("unionid not found!");
        }
        log.debug("_unionId: {}", _unionId);
    }

    // 刷新token
    void testRefreshToken() {
        String _url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";
        _url = String.format(_url, _APP_ID, _TEST_REFRESH_TOKEN);
        IHttpUtils.IHttpResult _httpResult = HttpUtils.impl().httpGet(_url);
        log.debug("httpResult: {}", _httpResult);
    }

    // 获取用户信息
    void testGetUserInfo() {
        String _url = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
        _url = String.format(_url, _TEST_ACCESS_TOKEN, _TEST_UNION_ID);
//        _url = String.format(_url, _TEST_ACCESS_TOKEN, _TEST_OPEN_ID);
        IHttpUtils.IHttpResult _httpResult = HttpUtils.impl().httpGet(_url);
        log.debug("httpResult: {}", _httpResult);
    }

    public static void main(String[] args) {
        WxApiHelper _helper = new WxApiHelper();
//        _helper.testGetUserInfo();
//        _helper.testGetToken("021eZrHa10pg7A09l9Ja1fWKm42eZrHU");
        _helper.testRefreshToken();
    }

}
