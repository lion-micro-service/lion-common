package com.lion.common.service.file;

import com.lion.common.entity.file.File;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @description:
 * @author: mr.liu
 * @create: 2020-10-08 14:44
 **/
public interface FileUploadService {

    public static final String URL_PREFIX = "/file";

    /**
     *
     * @param file
     * @return
     * @throws Exception
     */
    public File upload(MultipartFile file) throws Exception;
}
