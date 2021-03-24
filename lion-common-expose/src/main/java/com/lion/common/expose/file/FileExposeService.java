package com.lion.common.expose.file;

import com.lion.common.entity.file.File;

/**
 * @description: 文件服务接口暴露
 * @author: mr.liu
 * @create: 2020-10-08 13:43
 **/
public interface FileExposeService extends com.lion.core.service.BaseService<File> {

    /**
     * 获取文件URL
     * @param id
     * @return
     */
    public String getUrl(Long id);
}
