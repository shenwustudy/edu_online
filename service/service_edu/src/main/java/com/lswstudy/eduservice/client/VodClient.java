package com.lswstudy.eduservice.client;

import com.lswstudy.commonutils.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author lswstudy
 * @create 2022-02-22-13:54
 */
@FeignClient(name = "service-vod",fallback = VodFeignClient.class)
@Component
public interface VodClient {

    /*
    引入service-vod服务中定义的方法    根据视频id删除视频
    此处，@PathVariable("")中一定要指定参数名称，否则报错
     */
    @DeleteMapping("/eduvod/video/removeVideo/{videoId}")
    public ResultData removeVideoById(@PathVariable("videoId") String videoId);

    //同时删除多个阿里云视频点播服务中存储的视频
    @DeleteMapping("/eduvod/video/removeBatch")
    public ResultData removeBatch(@RequestParam("videoIdList") List<String> list);
}
