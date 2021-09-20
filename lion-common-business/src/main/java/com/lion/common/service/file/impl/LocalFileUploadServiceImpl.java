package com.lion.common.service.file.impl;

import com.lion.common.entity.file.File;
import com.lion.common.service.file.FileUploadService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

/**
 * @author Mr.Liu
 * @Description:
 * @date 2021/3/25上午10:28
 */
@Service
@ConditionalOnProperty(prefix = "file",name = "store.type",havingValue = "local")
public class LocalFileUploadServiceImpl implements FileUploadService {

    @Value("${local.storage.path}")
    private String storage_path;

    @Override
    public File upload(MultipartFile file) throws Exception {
        String fileName = UUID.randomUUID().toString()+file.getOriginalFilename();
        String filePath = storage_path +(Objects.equals(storage_path.substring(storage_path.length()-1),"/")?"":"/")+fileName;
        FileUtils.copyInputStreamToFile(file.getInputStream(), new java.io.File(filePath));
        File entity = new File();
        entity.setSize(file.getSize());
        entity.setFileName(fileName);
        entity.setOriginalFileName(file.getOriginalFilename());
        entity.setUrl("/"+ fileName);
        return entity;
    }
}
