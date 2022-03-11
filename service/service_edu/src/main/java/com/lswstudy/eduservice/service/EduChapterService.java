package com.lswstudy.eduservice.service;

import com.lswstudy.eduservice.bean.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lswstudy.eduservice.bean.chapter.ChapterBean;
import com.lswstudy.eduservice.bean.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lswstudy
 * @since 2022-01-18
 */
public interface EduChapterService extends IService<EduChapter> {

    //根据课程的id查询出课程所有的章节和小节
    List<ChapterBean> getChapterVideoById(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);

    List<ChapterVo> getChapterVideoByCourseId(String courseId);
}
