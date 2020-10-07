package com.lion.common.controller.captcha;

import com.lion.annotation.AuthorizationIgnore;
import com.lion.core.IResultData;
import com.lion.core.ResultData;
import com.wf.captcha.SpecCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @description: 验证码
 * @author: mr.liu
 * @create: 2020-10-07 14:24
 **/
public abstract class AbstractCaptchaController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/fresh")
    @AuthorizationIgnore
    public IResultData captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        // 存入redis并设置过期时间为30秒
        redisTemplate.opsForValue().set(key,verCode,30, TimeUnit.SECONDS);
        // 将key和base64返回给前端
        ResultData resultData = ResultData.instance();
        resultData.setData("key",key);
        resultData.setData("image",specCaptcha.toBase64());
        return resultData;
    }
}
