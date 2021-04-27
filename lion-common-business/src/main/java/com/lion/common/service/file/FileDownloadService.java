package com.lion.common.service.file;

import com.lion.common.entity.file.File;

import java.io.InputStream;

/**
 * @Author Mr.Liu
 * @Description //TODO
 * @Date 2021/4/27 下午2:42
 **/
public interface FileDownloadService {

    /**
     * 文件下载
     * @param file
     * @return
     */
    public InputStream downlod(File file);
}
