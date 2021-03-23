package com.lion.common.expose.file;

import com.lion.common.entity.file.File;
import com.lion.core.service.BaseExposeService;
import org.springframework.data.domain.Page;

/**
 * @description: 文件服务接口暴露
 * @author: mr.liu
 * @create: 2020-10-08 13:43
 **/
public interface FileExposeService extends BaseExposeService<File> {

    /**
     * 获取文件URL
     * @param id
     * @return
     */
    public String getUrl(Long id);
}
