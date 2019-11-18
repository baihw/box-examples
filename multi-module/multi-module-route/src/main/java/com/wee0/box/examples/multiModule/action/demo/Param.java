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

package com.wee0.box.examples.multiModule.action.demo;

import com.wee0.box.beans.annotation.BoxInject;
import com.wee0.box.examples.multiModule.action.vo.SimpleUser;
import com.wee0.box.examples.multiModule.module1.api.IHello;
import com.wee0.box.log.ILogger;
import com.wee0.box.log.LoggerFactory;
import com.wee0.box.subject.annotation.BoxRequireIgnore;
import com.wee0.box.web.annotation.BoxAction;

import java.util.List;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2019/11/16 13:52
 * @Description 参数示例
 * <pre>
 * 补充说明
 * </pre>
 **/
@BoxAction
public class Param {

    // 日志对象
    private static ILogger log = LoggerFactory.getLogger(Param.class);

    @BoxInject
    private IHello hello;

    // 字符串参数
    public String string1(String name) {
        return hello.hello(name);
    }

    // 对象参数
    public String helloUser(SimpleUser user) {
        // curl -i http://127.0.0.1:9000/api/user/helloUser -d "user={\"name\":\"u1\", \"age\":18}" --cookie "boxId=07133f522167460c836e8cf0a6ee6831;"
        return hello.hello(user.getName());
    }

    // 字符串数组参数
    public String helloStrings(List<String> users) {
        // curl -i http://127.0.0.1:9000/api/user/helloStrings -d "users=[\"user1\", \"user2\", \"user3\"]"
        return hello.hello(users.toString());
    }

}
