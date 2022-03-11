package com.lswstudy.eduservice.client;

import org.springframework.stereotype.Component;

/**
 * @author lswstudy
 * @create 2022-03-02-16:33
 */
@Component
public class OrderClientImpl implements OrderClient {
    @Override
    public boolean isBuyCourse(String memberId, String id) {
        System.out.println("出错了");
        return false;
    }
}
