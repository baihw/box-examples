/*
 *
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
 *
 */

package com.wee0.box.examples.multiModule;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

import java.sql.JDBCType;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2019/8/31 19:18
 * @Description 项目启动入口
 * <pre>
 * 补充说明
 * </pre>
 **/
@SpringBootApplication(exclude = MultipartAutoConfiguration.class)
public class App {

    public static void main(String[] args) {
//        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
//        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplicationBuilder _builder = new SpringApplicationBuilder(App.class);
        _builder.run(args);
    }

}
