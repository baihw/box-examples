package com.wee0.box.examples.allInOne.beans;

import com.wee0.box.beans.annotation.BoxBean;
import com.wee0.box.log.ILogger;
import com.wee0.box.log.LoggerFactory;
import com.wee0.box.subject.IAuthorizationInfoProvider;

import java.util.Set;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2019/9/21 9:29
 * @Description 自定义认证域数据提供者
 * <pre>
 * 补充说明
 * </pre>
 **/
//@BoxBean(name = IAuthorizationInfoProvider.DEF_BEAN_NAME)
public class MyAuthorizationInfoProvider implements IAuthorizationInfoProvider {

    // 日志对象
    private static ILogger log = LoggerFactory.getLogger(MyAuthorizationInfoProvider.class);

    @Override
    public Set<String> getRoles(String s) {
        log.debug("getRoles...");
        return null;
    }

    @Override
    public Set<String> getPermissions(String s) {
        log.debug("getPermissions...");
        return null;
    }

}
