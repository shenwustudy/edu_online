package com.lswstudy.eduorder.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author lswstudy
 * @create 2022-03-02-9:47
 */
@Component
public class ConstantWxUtils implements InitializingBean {
    @Value("${appid}")
    private String appId;

    @Value("${partner}")
    private String partner;

    @Value("${partnerkey}")
    private String partnerkey;

    @Value("${notifyurl}")
    private String notifyurl;

    public static String WX_APP_ID;
    public static String WX_PARTNER;
    public static String WX_PARTNER_KEY;
    public static String WX_NOTIFY_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        WX_APP_ID = appId;
        WX_PARTNER = partner;
        WX_PARTNER_KEY = partnerkey;
        WX_NOTIFY_URL = notifyurl;
    }
}
