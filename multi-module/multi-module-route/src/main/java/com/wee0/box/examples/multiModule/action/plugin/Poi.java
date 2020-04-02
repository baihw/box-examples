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

package com.wee0.box.examples.multiModule.action.plugin;

import com.wee0.box.BoxConfig;
import com.wee0.box.log.ILogger;
import com.wee0.box.log.LoggerFactory;
import com.wee0.box.plugin.PluginManager;
import com.wee0.box.plugins.office.IOfficePlugin;
import com.wee0.box.plugins.storage.ICloudStoragePlugin;
import com.wee0.box.web.annotation.BoxAction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2020/1/5 8:08
 * @Description poi插件调用示例
 * <pre>
 * 补充说明
 * </pre>
 **/
@BoxAction
public class Poi {

    // 日志对象
    private static ILogger log = LoggerFactory.getLogger(Poi.class);

    private IOfficePlugin _office = PluginManager.getPlugin(IOfficePlugin.class);

    public boolean wordTemplateProcess() {
        Map<String, Object> _data = new HashMap<>(16);
        _data.put("title1", "xxx什么的标题一");
        _data.put("title2", "标题二");
        _data.put("p1", "小标题一");
        _data.put("p2", "小标题二");
        _data.put("p3", "随便来些什么吧");
        _data.put("p4", "某某四");
        _data.put("p5", "不知道打些什么了");
        _data.put("date", "2020/1/9 13:32:32");
        _data.put("phone", "13666000000");
        _data.put("1", "张三");
        _data.put("2", "男");
        _data.put("3", 18);
        _data.put("120", 120);
        _data.put("121", 121);

        File _templatesDir = new File(BoxConfig.impl().getResourceDir(), "templates");
        try (FileInputStream _in = new FileInputStream(new File(_templatesDir, "word1Template.docx"));
             FileOutputStream _out = new FileOutputStream(new File(_templatesDir, "word1.docx"));) {
            _office.getWordUtils().templateProcess(_in, _out, _data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}
