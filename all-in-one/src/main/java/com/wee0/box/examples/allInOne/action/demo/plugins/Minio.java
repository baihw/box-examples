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

package com.wee0.box.examples.allInOne.action.demo.plugins;

import com.wee0.box.log.ILogger;
import com.wee0.box.log.LoggerFactory;
import com.wee0.box.plugin.PluginManager;
import com.wee0.box.plugins.storage.ICloudStoragePlugin;
import com.wee0.box.web.annotation.BoxAction;

import java.util.Map;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2020/8/15 7:02
 * @Description MinIo操作示例
 * <pre>
 * 补充说明
 * </pre>
 **/
@BoxAction
public class Minio {

    // 日志对象
    private static ILogger log = LoggerFactory.getLogger(Minio.class);

    // 云存储插件实现类实例
    private static final ICloudStoragePlugin _IMPL = PluginManager.getPlugin(ICloudStoragePlugin.class);

    /**
     * 获取文件上传授权数据
     *
     * @param fid 文件唯一标识
     * @return 上传授权数据
     */
    public String getPutUrl(String fid) {
        return _IMPL.getUploadUrl(fid);
    }

    /**
     * 获取文件上传授权数据
     *
     * @param fid 文件唯一标识
     * @return 上传授权数据
     */
    public Map getUploadUrl(String fid) {
        return _IMPL.getUploadForm(fid);
    }

    /**
     * 获取文件授权下载地址
     *
     * @param fid 文件唯一标识
     * @return 授权下载地址
     */
    public String getDownloadUrl(String fid) {
        return _IMPL.getDownloadUrl(fid, 300);
    }

    // about
    public String about() {
        return _IMPL.toString();
    }


}
