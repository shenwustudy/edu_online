package com.lswstudy.eduservice.client;

import com.lswstudy.commonutils.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author lswstudy
 * @create 2022-02-27-13:51
 */
@Component
@FeignClient(name="service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {
    //根据member_id获取会员信息
    @PostMapping("/educenter/comment/getUcenterInfo/{id}")
    public UcenterMemberVo getInfo(@PathVariable String id);
}
