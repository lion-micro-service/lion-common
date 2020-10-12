package com.lion.common.service.file.impl;

import com.lion.common.entity.file.File;
import com.lion.common.service.file.FileUploadService;
import com.lion.config.properties.MinioProperties;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.UUID;

/**
 * @description: minio文件上传
 * @author: mr.liu
 * @create: 2020-10-08 14:47
 **/
@Service
@ConditionalOnProperty(prefix = "file",name = "store.type",havingValue = "minio")
@ConditionalOnClass({MinioClient.class})
public class MinioFileUploadServiceImpl implements FileUploadService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioProperties minioProperties;

    @Override
    public File upload(MultipartFile file) throws Exception{
        String fileStoreName = UUID.randomUUID().toString()+file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        PutObjectArgs putObjectArgs = PutObjectArgs.builder().bucket(MinioProperties.PUBLIC_BUCKET).object(minioProperties.getBucket()+"/"+fileStoreName).stream(
                inputStream, inputStream.available(), -1)
                .build();
        ObjectWriteResponse objectWriteResponse = minioClient.putObject(putObjectArgs);
        if (Objects.nonNull(objectWriteResponse) && StringUtils.hasText(objectWriteResponse.etag())) {
            File entity = new File();
            entity.setSize(file.getSize());
            entity.setOriginalFileName(file.getOriginalFilename());
            entity.setUrl("/" + FileUploadService.URL_PREFIX + "/" + MinioProperties.PUBLIC_BUCKET + "/" + minioProperties.getBucket() + "/" + fileStoreName);
            return entity;
        }
        return null;
    }

}
