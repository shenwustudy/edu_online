package com.lswstudy.edusms.controller;

import com.lswstudy.commonutils.ResultData;
import com.lswstudy.edusms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author lswstudy
 * @create 2022-02-24-16:57
 */
@RestController
@RequestMapping("/edusms")
public class SmsController {
    @Autowired
    private SmsService smsService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping(value = "/sendSms/{phoneNum}")
    public ResultData sendSms(@PathVariable String phoneNum) {
        if(StringUtils.isEmpty(phoneNum)) return ResultData.error().message("手机号为空，发送验证码失败");
        //根据手机号从redis缓存中查询是否存在验证码
        String codeStr = redisTemplate.opsForValue().get(phoneNum);
        if(!StringUtils.isEmpty(codeStr)) return ResultData.ok();

        //将手机号传递给service调用方法发送验证码短信并得到发送的验证码
        codeStr = smsService.sendMessage(phoneNum);
        if(codeStr != "000000") {
            //若验证码不等于000000，则发送成功，将手机号，验证码保存到redis中,并设置过期时间
            redisTemplate.opsForValue().set(phoneNum, codeStr,5, TimeUnit.MINUTES);
            return ResultData.ok();
        } else {
            return ResultData.error().message("发送验证码失败");
        }
    }
}
