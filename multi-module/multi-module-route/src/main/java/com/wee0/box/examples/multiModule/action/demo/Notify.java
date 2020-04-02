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

import com.wee0.box.log.ILogger;
import com.wee0.box.log.LoggerFactory;
import com.wee0.box.notify.sms.SmsHelper;
import com.wee0.box.struct.CMD;
import com.wee0.box.subject.annotation.BoxRequireLogical;
import com.wee0.box.subject.annotation.BoxRequirePermissions;
import com.wee0.box.subject.annotation.BoxRequireRoles;
import com.wee0.box.web.annotation.BoxAction;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2020/3/28 10:12
 * @Description 通知示例
 * <pre>
 * 补充说明
 * </pre>
 **/
@BoxAction
public class Notify {

    // 日志对象
    private static ILogger log = LoggerFactory.getLogger(Notify.class);

    // 需要具备admin角色
    public CMD sms() {
        java.util.Map<String, String> _params = new java.util.HashMap<>();
        _params.put("PhoneNumbers", "1311111111");
        _params.put("TemplateParam", "{\"code\":\"123456\"}");
        return SmsHelper.impl().sendSms(_params);
    }


}
