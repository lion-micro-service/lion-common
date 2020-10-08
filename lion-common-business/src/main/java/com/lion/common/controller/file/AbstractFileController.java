package com.lion.common.controller.file;

import com.lion.annotation.AuthorizationIgnore;
import com.lion.common.entity.file.File;
import com.lion.common.service.file.FileService;
import com.lion.common.service.file.FileUploadService;
import com.lion.core.IResultData;
import com.lion.core.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.util.*;

/**
 * @description: 文件控制层，只处理上传文件
 * @author: mr.liu
 * @create: 2020-10-08 11:40
 **/
public abstract class AbstractFileController {

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    @AuthorizationIgnore
    public IResultData upload(StandardMultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        Map<String, MultipartFile> files = multipartHttpServletRequest.getFileMap();
        List<Map<String,String>> fileList = new ArrayList<Map<String,String>>();
        for(String originalFileName : files.keySet()) {
            MultipartFile file = files.get(originalFileName);
            if (file.getSize() <= 0) {
                continue;
            }
            File entity = fileUploadService.upload(file);
            if (Objects.nonNull(entity)) {
                fileService.save(entity);
                if (Objects.nonNull(entity.getId())) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("id", String.valueOf(entity.getId()));
                    map.put("url", entity.getUrl());
                    fileList.add(map);
                }
            }
        }
        return ResultData.instance().setData("files",fileList);
    }
}
