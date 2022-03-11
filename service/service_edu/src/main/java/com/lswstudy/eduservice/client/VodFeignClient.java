package com.lswstudy.eduservice.client;

import com.lswstudy.commonutils.ResultData;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lswstudy
 * @create 2022-02-22-21:16
 */
@Component
public class VodFeignClient implements VodClient {
    @Override
    public ResultData removeVideoById(String videoId) {
        return ResultData.error().message("删除视频出错...");
    }

    @Override
    public ResultData removeBatch(List<String> list) {
        return ResultData.error().message("删除该课程下视频出错了...");
    }
}
