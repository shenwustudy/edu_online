package com.lswstudy.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lswstudy.eduservice.bean.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lswstudy.eduservice.bean.vo.CourseInfoVo;
import com.lswstudy.eduservice.bean.vo.CourseQueryVo;
import com.lswstudy.eduservice.bean.vo.CourseWebVo;
import com.lswstudy.eduservice.bean.vo.PublishCourseVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lswstudy
 * @since 2022-01-18
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    PublishCourseVo getPublishCourseInfo(String id);

    void removeCourseById(String courseId);

    void removeVideoById(String courseId);

    Map<String, Object> pageListWeb(Page<EduCourse> pageParam, CourseQueryVo courseQuery);

    CourseWebVo getBaseCourseInfo(String id);

    void updatePageViewCount(String id);
}
