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

import com.wee0.box.BoxConfig;
import com.wee0.box.beans.annotation.BoxInject;
import com.wee0.box.log.ILogger;
import com.wee0.box.log.LoggerFactory;
import com.wee0.box.util.shortcut.IoUtils;
import com.wee0.box.web.annotation.BoxAction;
import com.wee0.box.web.annotation.BoxIgnoreReturnValue;
import com.wee0.box.web.servlet.IUploadFile;
import com.wee0.box.web.servlet.IUploadRequest;
import com.wee0.box.web.servlet.IUploadRequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="78026399@qq.com">白华伟</a>
 * @CreateDate 2019/10/13 22:50
 * @Description 上传下载
 * <pre>
 * 补充说明
 * </pre>
 **/
@BoxAction
public class UploadRequest {

    // 日志对象
    private static ILogger log = LoggerFactory.getLogger(UploadRequest.class);

    @BoxInject
    private IUploadRequestUtils uploadRequestUtils;

    public List<String> upload(HttpServletRequest request) {
        IUploadRequest _uploadRequest = uploadRequestUtils.parseRequest(request);
        List<String> _names = new ArrayList<>(2);
        IUploadFile _file1 = _uploadRequest.getUploadFile("file1");
        if (null != _file1) {
            log.debug("file1: {}", _file1);
            _names.add(_file1.getName());
        }
        IUploadFile _file2 = _uploadRequest.getUploadFile("file2");
        if (null != _file2) {
            log.debug("file2: {}", _file2);
            _names.add(_file2.getName());
        }
        return _names;
    }

    @BoxIgnoreReturnValue
    public void download(HttpServletResponse response) {
        String _fileName = "word1Template.docx";
//        response.setHeader("content-type", "application/octet-stream");
//        response.setContentType("application/octet-stream");
        // 设置强制下载不打开
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + _fileName);

        try (InputStream _in = BoxConfig.impl().getResourceAsStream("templates/" + _fileName);) {
            IoUtils.impl().copy(_in, response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
