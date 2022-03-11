package com.lswstudy.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author lswstudy
 * @create 2022-02-21-9:35
 */
@Component
public class ConstantVodUtils implements InitializingBean {

    @Value("${aliyun.vod.file.keyid}")
    private String keyId;
    @Value("${aliyun.vod.file.keysecret}")
    private String keySecret;

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;


    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
    }
}
