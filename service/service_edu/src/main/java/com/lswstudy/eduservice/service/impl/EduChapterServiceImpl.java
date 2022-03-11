package com.lswstudy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lswstudy.eduservice.bean.EduChapter;
import com.lswstudy.eduservice.bean.EduVideo;
import com.lswstudy.eduservice.bean.chapter.ChapterBean;
import com.lswstudy.eduservice.bean.vo.ChapterVo;
import com.lswstudy.eduservice.bean.vo.VideoVo;
import com.lswstudy.eduservice.mapper.EduChapterMapper;
import com.lswstudy.eduservice.service.EduChapterService;
import com.lswstudy.eduservice.service.EduVideoService;
import com.lswstudy.servicebase.exceptionhandler.EduException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author lswstudy
 * @since 2022-01-18
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterBean> getChapterVideoById(String courseId) {
        ArrayList<ChapterBean> list = new ArrayList<>();

        //查询课程所有的章节
        QueryWrapper<EduChapter> chapterWrapper = new QueryWrapper<EduChapter>().eq("course_id",courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(chapterWrapper);

        //遍历查询到的所有章节将其对应的小节信息查询出并封装
        for (int i = 0; i < eduChapterList.size(); i++) {
            //用于封装章节信息
            ChapterBean chapterBean = new ChapterBean();
            //用于封装每个章节的所有小节的信息
            ArrayList<ChapterBean> videoBeans = new ArrayList<>();

            String chapterId = eduChapterList.get(i).getId();
            chapterBean.setId(chapterId);
            String chapterTitle = eduChapterList.get(i).getTitle();
            chapterBean.setTitle(chapterTitle);

            //根据章节的id查询出该章节对应的小节信息并将其封装
            QueryWrapper<EduVideo> wrapper = new QueryWrapper<EduVideo>().eq("chapter_id", chapterId);
            List<EduVideo> videoList = eduVideoService.list(wrapper);
            for (int j = 0; j < videoList.size(); j++) {
                //用于封装每个小节信息
                ChapterBean videoBean = new ChapterBean();
                String videoId = videoList.get(j).getId();
                String videoTitle = videoList.get(j).getTitle();
                videoBean.setId(videoId);
                videoBean.setTitle(videoTitle);
                videoBeans.add(videoBean);
            }
            chapterBean.setChildren(videoBeans);
            list.add(chapterBean);

        }

        return list;
    }

    //删除章节的实现方法，如果章节下面有小节，不予删除
    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        if (eduVideoService.count(wrapper) > 0){
            //说明该章节有小节，不进行删除
            throw new EduException(20001,"请先删除该章节下的小节!!!");
        }else {
            //删除章节
            int isDelete = baseMapper.deleteById(chapterId);
            return isDelete > 0;
        }
    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }

    //课程大纲列表,根据课程id进行查询
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

        //1 根据课程id查询课程里面所有的章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);

        //2 根据课程id查询课程里面所有的小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduVideo> eduVideoList = eduVideoService.list(wrapperVideo);

        //创建list集合，用于最终封装数据
        List<ChapterVo> finalList = new ArrayList<>();

        //3 遍历查询章节list集合进行封装
        //遍历查询章节list集合
        for (int i = 0; i < eduChapterList.size(); i++) {
            //每个章节
            EduChapter eduChapter = eduChapterList.get(i);
            //eduChapter对象值复制到ChapterVo里面
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            //把chapterVo放到最终list集合
            finalList.add(chapterVo);

            //创建集合，用于封装章节的小节
            List<VideoVo> videoList = new ArrayList<>();

            //4 遍历查询小节list集合，进行封装
            for (int m = 0; m < eduVideoList.size(); m++) {
                //得到每个小节
                EduVideo eduVideo = eduVideoList.get(m);
                //判断：小节里面chapterid和章节里面id是否一样
                if(eduVideo.getChapterId().equals(eduChapter.getId())) {
                    //进行封装
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    //放到小节封装集合
                    videoList.add(videoVo);
                }
            }
            //把封装之后小节list集合，放到章节对象里面
            chapterVo.setChildren(videoList);
        }
        return finalList;
    }
}
