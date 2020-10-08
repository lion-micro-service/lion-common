package com.lion.common.service.file.impl;

import com.lion.common.dao.File.FileDao;
import com.lion.common.entity.file.File;
import com.lion.common.service.file.FileService;
import com.lion.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: mr.liu
 * @create: 2020-10-08 15:23
 **/
@Service
public class FileServiceImpl extends BaseServiceImpl<File> implements FileService {

    @Autowired
    private FileDao fileDao;
}
