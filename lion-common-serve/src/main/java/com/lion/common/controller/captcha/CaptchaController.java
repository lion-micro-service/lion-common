package com.lion.common.controller.captcha;

import com.lion.annotation.AuthorizationIgnore;
import com.lion.common.vo.Captcha;
import com.lion.core.IResultData;
import com.lion.core.ResultData;
import com.lion.core.controller.BaseController;
import com.lion.core.controller.impl.BaseControllerImpl;
import com.wf.captcha.SpecCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: mr.liu
 * @create: 2020-10-07 14:34
 **/
@RestController
@RequestMapping("/captcha/console")
@Api(tags = {"验证码"})
public class CaptchaController extends BaseControllerImpl implements BaseController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/fresh")
    @AuthorizationIgnore
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    public IResultData<Captcha> captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        // 存入redis并设置过期时间为30秒
        redisTemplate.opsForValue().set(key,verCode,30, TimeUnit.SECONDS);
        // 将key和base64返回给前端
        ResultData resultData = ResultData.instance();
        Captcha captcha = new Captcha();
        captcha.setKey(key);
        captcha.setImage(specCaptcha.toBase64());
        resultData.setData(captcha);
        return resultData;
    }
}
