package com.lion.common.service.file.impl;

import com.lion.common.entity.file.File;
import com.lion.common.service.file.FileDownloadService;
import com.lion.common.service.file.FileService;
import com.lion.config.properties.MinioProperties;
import io.minio.DownloadObjectArgs;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @Author Mr.Liu
 * @Description //TODO
 * @Date 2021/4/27 下午2:53
 **/
@Service
@ConditionalOnProperty(prefix = "file",name = "store.type",havingValue = "minio")
@ConditionalOnClass({MinioClient.class})
public class MinioFileDownloadServiceImpl implements FileDownloadService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private FileService fileService;

    @Autowired
    private MinioProperties minioProperties;

    @Override
    public InputStream downlod(File file) {
        if (Objects.isNull(file)){
            return null;
        }
        DownloadObjectArgs downloadObjectArgs = DownloadObjectArgs.builder().bucket(MinioProperties.PUBLIC_BUCKET)
                .object(minioProperties.getPath()+"/"+ (StringUtils.hasText(file.getFileName())?("/"+file.getFileName()):file.getUrl().substring(file.getUrl().lastIndexOf("/")))).build();
        GetObjectArgs getObjectArgs = new GetObjectArgs(downloadObjectArgs);
        try {
            InputStream inputStream = minioClient.getObject(getObjectArgs);
            return inputStream;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
