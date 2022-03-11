package com.lswstudy.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author lswstudy
 * @create 2022-03-02-13:03
 */
@Component
@FeignClient(value = "service-order",fallback = OrderClientImpl.class)
public interface OrderClient {
    //查询订单信息
    @PostMapping("/eduorder/order/isBuyCourse/{id}/{memberId}")
    public boolean isBuyCourse(@PathVariable("id") String id,@PathVariable("memberId") String memberId);
}
