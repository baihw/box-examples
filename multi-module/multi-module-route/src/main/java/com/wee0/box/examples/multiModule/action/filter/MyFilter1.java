package com.wee0.box.examples.multiModule.action.filter;

import com.wee0.box.log.ILogger;
import com.wee0.box.log.LoggerFactory;
import com.wee0.box.web.IActionFilter;
import com.wee0.box.web.IActionRequest;
import com.wee0.box.web.IActionResponse;

import java.util.Random;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2019/12/22 15:20
 * @Description 一个测试的请求过滤器
 * <pre>
 * 补充说明
 * </pre>
 **/
public class MyFilter1 implements IActionFilter {

    // 日志对象
    private static ILogger log = LoggerFactory.getLogger(MyFilter1.class);

    @Override
    public boolean doFilter(IActionRequest request, IActionResponse response) {
        log.debug("request.context: {}", request.getContextPath());
        log.debug("request.uri: {}", request.getURI());
        log.debug("request.fullUri: {}", request.getFullURI());
        log.debug("request.ip: {}", request.getRequestIP());
        log.debug("request.parameters: {}", request.getParameters());
        log.debug("request.parameter.name: {}", request.getParameter("name", null));

        boolean _result = new Random().nextBoolean();
        if (_result) {
            response.setStatus(501);
            response.setData("Random false!");
//            // 也可以直接抛出业务异常
//            throw BizExceptionFactory.create(BoxConfig.impl().getConfigObject().getSystemErrorInfoBizCode(), "random error.");
        }
        return _result;
    }

}
