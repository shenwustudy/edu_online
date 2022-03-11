package com.lswstudy.eduservice.service;

import com.lswstudy.eduservice.bean.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author lswstudy
 * @since 2022-01-18
 */
public interface EduVideoService extends IService<EduVideo> {


    void deleteVideoById(String videoId);

    void removeVideoByCourseId(String courseId);

    void removeVideoByVideoId(String videoId);

    void removeVideoListByCourseId(String courseId);
}
