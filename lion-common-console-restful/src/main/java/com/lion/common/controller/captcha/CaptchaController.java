package com.lion.common.controller.captcha;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: mr.liu
 * @create: 2020-10-07 14:34
 **/
@RestController
@RequestMapping("/common/captcha/console")
@Api(tags = {"二维码"})
public class CaptchaController extends AbstractCaptchaController {
}
