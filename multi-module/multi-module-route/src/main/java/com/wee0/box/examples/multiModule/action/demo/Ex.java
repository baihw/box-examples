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
import com.wee0.box.code.BizCodeDef;
import com.wee0.box.examples.multiModule.action.vo.SimpleUser;
import com.wee0.box.examples.multiModule.module1.api.IHello;
import com.wee0.box.examples.multiModule.module1.dao.SysUserDao;
import com.wee0.box.examples.multiModule.module1.entity.SysUserEntity;
import com.wee0.box.exception.BizExceptionFactory;
import com.wee0.box.log.ILogger;
import com.wee0.box.log.LoggerFactory;
import com.wee0.box.subject.IPasswordToken;
import com.wee0.box.subject.ISubject;
import com.wee0.box.subject.SubjectContext;
import com.wee0.box.subject.annotation.BoxRequireIgnore;
import com.wee0.box.subject.annotation.BoxRequireLogical;
import com.wee0.box.subject.annotation.BoxRequirePermissions;
import com.wee0.box.subject.annotation.BoxRequireRoles;
import com.wee0.box.util.shortcut.ValidateUtils;
import com.wee0.box.web.annotation.BoxAction;

import java.util.List;

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
    public void bizDefault() {
        throw BizExceptionFactory.create();
    }

    /**
     * 带一个参数的业务异常
     */
    public void bizArg1() {
        throw BizExceptionFactory.create(BizCodeDef.S000001, "参数1的值");
    }

}
