package com.lion.common.expose.file.impl;

import com.lion.common.entity.file.File;
import com.lion.common.expose.file.FileExposeService;
import com.lion.core.service.impl.BaseExposeServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @description:
 * @author: mr.liu
 * @create: 2020-10-09 09:40
 **/
@DubboService(interfaceClass = FileExposeService.class)
public class FileExposeServiceImpl extends BaseExposeServiceImpl<File> implements FileExposeService {
}
