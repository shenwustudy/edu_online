package com.lswstudy.staservice.client;

import com.lswstudy.commonutils.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lswstudy
 * @create 2022-03-03-14:46
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    @GetMapping("/educenter/member/countRegister/{date}")
    public ResultData countRegister(@PathVariable String date);
}
