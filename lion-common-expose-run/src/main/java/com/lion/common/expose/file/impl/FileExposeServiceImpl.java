package com.lion.common.expose.file.impl;

import com.lion.common.entity.file.File;
import com.lion.common.expose.file.FileExposeService;
import com.lion.common.service.file.FileUploadService;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Objects;

/**
 * @description:
 * @author: mr.liu
 * @create: 2020-10-09 09:40
 **/
@DubboService(interfaceClass = FileExposeService.class)
public class FileExposeServiceImpl extends com.lion.core.service.impl.BaseServiceImpl<File> implements FileExposeService, com.lion.core.service.BaseService<File> {

    @Override
    public String getUrl(Long id) {
        if (Objects.isNull(id)){
            return "";
        }
        File file = this.findById(id);
        if (Objects.isNull(file)){
            return "";
        }
        String url = file.getUrl();
        if (!Objects.equals(url.substring(0,1),"/")) {
            url = "/"+url;
        }
        url = FileUploadService.URL_PREFIX+url;
        return url;
    }
}
