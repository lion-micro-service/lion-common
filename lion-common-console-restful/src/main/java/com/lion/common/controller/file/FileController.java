package com.lion.common.controller.file;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: mr.liu
 * @create: 2020-10-08 15:47
 **/
@RestController
@RequestMapping("/common/file/console")
@Api(tags = {"文件"})
public class FileController extends AbstractFileController {

}
