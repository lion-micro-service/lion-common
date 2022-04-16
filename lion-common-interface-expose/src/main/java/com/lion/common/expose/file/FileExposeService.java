package com.lion.common.expose.file;

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
}
