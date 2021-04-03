package com.lion.common.controller.file;

import com.lion.annotation.AuthorizationIgnore;
import com.lion.common.entity.file.File;
import com.lion.common.service.file.FileService;
import com.lion.common.service.file.FileUploadService;
import com.lion.core.IResultData;
import com.lion.core.ResultData;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    @ApiOperation(value = "上传文件(支持多文件)",notes = "上传文件(支持多文件)")
//    @ApiImplicitParams(value = {
//            @ApiImplicitParam(value = "", name = "",type = "multipart/form-data")
//    })
    public IResultData<List<File>> upload(@ApiIgnore StandardMultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        Map<String, MultipartFile> files = multipartHttpServletRequest.getFileMap();
        List<File> fileList = new ArrayList<File>();
        for(String originalFileName : files.keySet()) {
            MultipartFile file = files.get(originalFileName);
            if (file.getSize() <= 0) {
                continue;
            }
            com.lion.common.entity.file.File entity = fileUploadService.upload(file);
            if (Objects.nonNull(entity)) {
                fileService.save(entity);
                if (Objects.nonNull(entity.getId())) {
                    String url = entity.getUrl();
                    if (!Objects.equals(url.substring(0,1),"/")) {
                        url = "/"+url;
                    }
                    url = FileUploadService.URL_PREFIX+url;
                    entity.setUrl(url);
                    fileList.add(entity);
                }
            }
        }
        return ResultData.instance().setData(fileList);
    }
}
