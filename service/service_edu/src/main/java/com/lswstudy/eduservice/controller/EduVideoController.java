package com.lswstudy.eduservice.controller;


import com.lswstudy.commonutils.ResultData;
import com.lswstudy.eduservice.bean.EduVideo;
import com.lswstudy.eduservice.client.VodClient;
import com.lswstudy.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author lswstudy
 * @since 2022-01-18
 */
@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;

    //添加小节
    @PostMapping("addVideo")
    public ResultData addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return ResultData.ok();
    }

    //删除小节
    @GetMapping("{videoId}")
    public ResultData deleteVideo(@PathVariable String videoId){
        //去阿里云中删除小节的视频
        eduVideoService.removeVideoByVideoId(videoId);
        //去数据库中删除小节信息
        eduVideoService.deleteVideoById(videoId);
        return ResultData.ok();
    }

    //修改小节
    @PostMapping("updateVideo")
    public ResultData updateVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return ResultData.ok();
    }

    //根据id查询课时信息
    @PostMapping("getVideoById/{videoId}")
    public ResultData getVideoById(@PathVariable String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return ResultData.ok().data("eduVideo",eduVideo);
    }
}

