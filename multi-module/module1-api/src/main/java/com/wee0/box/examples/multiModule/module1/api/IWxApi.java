package com.wee0.box.examples.multiModule.module1.api;

import java.util.Map;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2019/9/22 7:11
 * @Description 微信Api相关操作
 * <pre>
 * 补充说明
 * </pre>
 **/
public interface IWxApi {

    /**
     * 获取本应用的微信PC扫码登陆页访问地址
     *
     * @param callbackUrl 用户扫码登陆之后跳转的应用回调地址
     * @return PC扫码登陆页访问地址
     */
    String getPcLoginUrl(String callbackUrl);

    /**
     * 通过微信授权登陆后返回的code获取访问令牌相关信息
     *
     * @param code 微信授权登陆后返回的code
     * @return 访问令牌相关信息
     */
    Map<String, Object> getTokenByCode(String code);

    /**
     * 刷新访问令牌
     *
     * @param refreshToken 刷新令牌
     * @return 访问令牌相关信息
     */
    Map<String, Object> refreshToken(String refreshToken);

    /**
     * 获取用户信息
     *
     * @param wxId        微信用户唯一标识
     * @param accessToken 访问令牌
     * @return 用户信息
     */
    Map<String, Object> getUserInfo(String wxId, String accessToken);
}
