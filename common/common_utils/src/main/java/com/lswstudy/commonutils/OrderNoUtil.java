package com.lswstudy.commonutils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author lswstudy
 * @create 2022-02-28-15:17
 */
public class OrderNoUtil {
    /**
     * 获取订单号
     * @return
     */
    public static String getOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result += random.nextInt(10);
        }
        return newDate + result;
    }
}
