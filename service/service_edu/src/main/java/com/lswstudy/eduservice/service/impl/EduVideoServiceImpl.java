package com.lswstudy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lswstudy.eduservice.bean.EduVideo;
import com.lswstudy.eduservice.client.VodClient;
import com.lswstudy.eduservice.mapper.EduVideoMapper;
import com.lswstudy.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author lswstudy
 * @since 2022-01-18
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private VodClient vodClient;


    @Override
    public void deleteVideoById(String videoId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("id",videoId);
        baseMapper.delete(wrapper);
    }

    @Override
    public void removeVideoByCourseId(String courseId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }

    @Override
    public void removeVideoByVideoId(String videoId) {
        EduVideo eduVideo = eduVideoService.getById(videoId);
        String videoSourceId = eduVideo.getVideoSourceId();
        if (videoSourceId != null)
            vodClient.removeVideoById(videoSourceId);
    }

    //根据courseId查询出该课程下所有的视频id并从阿里云中删除,
    // 然后删除该课程下的小节信息
    @Override
    public void removeVideoListByCourseId(String courseId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.select("video_source_id");
        List<EduVideo> videoList = baseMapper.selectList(wrapper);
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            String videoSourceId = videoList.get(i).getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId))
                ids.add(videoSourceId);
        }
        if (!StringUtils.isEmpty(ids))
            vodClient.removeBatch(ids);

        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);

    }
}
