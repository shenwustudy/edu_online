package com.lswstudy.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.lswstudy.commonutils.ResultData;
import com.lswstudy.servicebase.exceptionhandler.EduException;
import com.lswstudy.vod.service.VodService;
import com.lswstudy.vod.utils.ConstantVodUtils;
import com.lswstudy.vod.utils.InitVodClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lswstudy
 * @create 2022-02-20-16:27
 */
@RestController
@RequestMapping("/eduvod/video")
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("uploadVideo")
    public ResultData uploadVideo(MultipartFile file){
        String videoId = vodService.uploadVideo(file);
        return ResultData.ok().data("videoId",videoId);
    }

    //删除阿里云视频点播服务中存储的视频
    @DeleteMapping("removeVideo/{videoId}")
    public ResultData removeVideoById(@PathVariable String videoId){
        vodService.removeVideoById(videoId);
        return ResultData.ok();
    }

    //同时删除多个阿里云视频点播服务中存储的视频
    @DeleteMapping("removeBatch")
    public ResultData removeBatch(@RequestParam("videoIdList") List<String> list){
        vodService.removeVideoListById(list);
        return ResultData.ok();
    }

    //根据视频id获取视频播放凭证
    @GetMapping("getPlayAuthById/{videoId}")
    public ResultData getPlayAuthById(@PathVariable String videoId){
        try {
            DefaultAcsClient client = InitVodClientUtils.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(videoId);
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return ResultData.ok().data("playAuth",playAuth);
        } catch (ClientException e) {
            throw new EduException(20001,"获取阿里云视频凭证失败");
        }
    }

}
