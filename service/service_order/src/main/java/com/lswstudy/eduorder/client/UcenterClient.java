package com.lswstudy.eduorder.client;

import com.lswstudy.commonutils.UcenterMemberOrder;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lswstudy
 * @create 2022-02-28-15:09
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    @ApiOperation(value = "根据用户id获取用户信息")
    @GetMapping("/educenter/member/getMemberInfoById/{id}")
    public UcenterMemberOrder getMemberInfoById(@PathVariable("id") String id);
}
