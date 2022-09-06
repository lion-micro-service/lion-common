package com.lion.common.expose.file;

import com.lion.common.bean.definition.file.FileDefinition;
import com.lion.core.Optional;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 文件服务接口暴露
 * @author: mr.liu
 * @create: 2020-10-08 13:43
 **/
public interface FileExposeService {

    /**
     * 获取文件URL
     * @param id
     * @return
     */
    public String getUrl(Long id);

    /**
     * 更具文件id查找文件
     * @return
     */
    public Optional<FileDefinition> getById(Long id);

    /**
     * 根据id的list集合查找
     * @param ids
     * @return
     */
    public List<FileDefinition> getByIds(List<Long> ids);
}
