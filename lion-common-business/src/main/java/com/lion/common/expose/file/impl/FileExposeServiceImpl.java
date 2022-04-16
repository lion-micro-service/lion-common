package com.lion.common.expose.file.impl;

import com.lion.common.entity.file.File;
import com.lion.common.expose.file.FileExposeService;
import com.lion.common.service.file.FileUploadService;
import com.lion.core.Optional;
import com.lion.core.service.impl.BaseServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Objects;

/**
 * @description:
 * @author: mr.liu
 * @create: 2020-10-09 09:40
 **/
@DubboService(interfaceClass = FileExposeService.class)
public class FileExposeServiceImpl extends BaseServiceImpl<File> implements FileExposeService {

    @Override
    public String getUrl(Long id) {
        if (Objects.isNull(id)){
            return "";
        }
        Optional<File> optional = this.findById(id);
        if (!optional.isPresent()) {
            return "";
        }
        File file = optional.get();
        String url = file.getUrl();
        if (!Objects.equals(url.substring(0,1),"/")) {
            url = "/"+url;
        }
        if (url.indexOf("/file")<0){
            url = FileUploadService.URL_PREFIX+url;
        }
        return url;
    }
}