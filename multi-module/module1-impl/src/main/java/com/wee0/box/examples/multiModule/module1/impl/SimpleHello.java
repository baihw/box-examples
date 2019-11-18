package com.wee0.box.examples.multiModule.module1.impl;

import com.wee0.box.beans.annotation.BoxBean;
import com.wee0.box.examples.multiModule.module1.api.IHello;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2019/11/2 13:03
 * @Description 一个测试接口的简单实现
 * <pre>
 * 补充说明
 * </pre>
 **/
@BoxBean
public class SimpleHello implements IHello {

    @Override
    public String hello(String name) {
        return "Hello " + name;
    }

}
