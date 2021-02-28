package com.wee0.box.examples.simple.cli;

import com.wee0.box.BoxConfig;
import com.wee0.box.IBoxConfig;
import com.wee0.box.log.ILogger;
import com.wee0.box.log.LoggerFactory;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2021/2/27 13:17
 * @Description 功能描述
 * <pre>
 * 补充说明
 * </pre>
 **/
public class App {
    // 日志对象
    private static ILogger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        IBoxConfig _boxConfig = BoxConfig.impl();
        log.debug("_boxConfig: {}", _boxConfig);
    }
}
