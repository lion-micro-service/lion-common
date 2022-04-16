package com.lion.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Mr.Liu
 * @classname Captcha
 * @description
 * @date 2022/04/16 上午10:15
 */
@Schema
@Data
public class Captcha {
    @Schema()
    private String key;

    @Schema(description = "验证码图片base64编码")
    private String image;
}
