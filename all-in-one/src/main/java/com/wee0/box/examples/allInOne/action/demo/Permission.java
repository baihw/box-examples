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

import com.wee0.box.log.ILogger;
import com.wee0.box.log.LoggerFactory;
import com.wee0.box.subject.annotation.BoxRequireLogical;
import com.wee0.box.subject.annotation.BoxRequirePermissions;
import com.wee0.box.subject.annotation.BoxRequireRoles;
import com.wee0.box.web.annotation.BoxAction;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2019/11/16 13:55
 * @Description 权限示例
 * <pre>
 * 补充说明
 * </pre>
 **/
@BoxAction
public class Permission {

    // 日志对象
    private static ILogger log = LoggerFactory.getLogger(Permission.class);

    // 需要具备admin角色
    @BoxRequireRoles("admin")
    public boolean roleAdmin() {
        return true;
    }

    @BoxRequireRoles(value = {"admin", "guest"}, logical = BoxRequireLogical.OR)
    public boolean roleAdminOrGuest() {
        return true;
    }

    @BoxRequireRoles(value = {"admin", "guest"})
    public boolean roleAdminAndGuest() {
        return true;
    }

    @BoxRequirePermissions("common_delete")
    public boolean commonDelete() {
        return true;
    }

}
