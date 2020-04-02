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
import com.wee0.box.examples.multiModule.module1.dao.SysUserDao;
import com.wee0.box.examples.multiModule.module1.entity.SysUserEntity;
import com.wee0.box.log.ILogger;
import com.wee0.box.log.LoggerFactory;
import com.wee0.box.sql.dao.IPage;
import com.wee0.box.sql.dao.PageHelper;
import com.wee0.box.util.shortcut.StringUtils;
import com.wee0.box.web.annotation.BoxAction;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2019/11/23 10:52
 * @Description 数据访问层操作示例
 * <pre>
 * 补充说明
 * </pre>
 **/
@BoxAction
public class Dao {

    // 日志对象
    private static ILogger log = LoggerFactory.getLogger(Dao.class);

    @BoxInject
    private SysUserDao sysUserDao;

    public List<SysUserEntity> getUsers() {
        List<SysUserEntity> _users = sysUserDao.queryAll();
        return _users;
    }

    public IPage getUsersPage(Integer pageNum, Integer pageSize) {
        Map<String, Object> _params = PageHelper.impl().createPageParams(pageNum, pageSize);
        sysUserDao.queryAllByPage(_params);
        IPage _result = PageHelper.impl().parseMap(_params);
        return _result;
    }

    public IPage getUsersPageInt(int pageNum, int pageSize) {
        Map<String, Object> _params = PageHelper.impl().createPageParams(pageNum, pageSize);
        sysUserDao.queryAllByPage(_params);
        return PageHelper.impl().parseMap(_params);
    }

    public IPage getUsersPageString(String pageNum, String pageSize) {
        int _pageNum = StringUtils.parseInt(pageNum, 1);
        int _pageSize = StringUtils.parseInt(pageSize, 10);
        Map<String, Object> _params = PageHelper.impl().createPageParams(_pageNum, _pageSize);
        sysUserDao.queryAllByPage(_params);
        return PageHelper.impl().parseMap(_params);
    }

}
