package com.lion.common.service.file.impl;

import com.lion.common.entity.file.File;
import com.lion.common.service.file.FileUploadService;
import com.lion.exception.BusinessException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 阿里云对象存储文件上传
 * @author: mr.liu
 * @create: 2020-10-08 14:53
 **/
@Service
@ConditionalOnProperty(prefix = "file",name = "store.type",havingValue = "ali-oos")
public class AliOssFileUploadServiceImpl implements FileUploadService {
    @Override
    public File upload(MultipartFile file) {
        new BusinessException("阿里云OOS未实现");
        return null;
    }
}
