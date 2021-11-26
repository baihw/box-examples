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

package com.wee0.box.examples.allInOne.action.demo;

import com.wee0.box.BoxConfig;
import com.wee0.box.exception.BizExceptionFactory;
import com.wee0.box.log.ILogger;
import com.wee0.box.log.LoggerFactory;
import com.wee0.box.web.annotation.BoxAction;

import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2019/11/16 13:50
 * @Description 异常示例
 * <pre>
 * 补充说明
 * </pre>
 **/
@BoxAction
public class Ex {

    // 日志对象
    private static ILogger log = LoggerFactory.getLogger(Ex.class);

    /**
     * 默认的无参业务异常
     */
    public void bizDefault(HttpServletResponse response) {
        throw BizExceptionFactory.create();
    }

    /**
     * 带一个参数的业务异常
     */
    public void bizArg1() {
        throw BizExceptionFactory.create(BoxConfig.impl().getConfigObject().getSystemErrorInfoBizCode(), "参数1的值");
    }

}
