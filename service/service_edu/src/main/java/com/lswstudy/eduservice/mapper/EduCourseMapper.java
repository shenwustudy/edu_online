package com.lswstudy.eduservice.mapper;

import com.lswstudy.eduservice.bean.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lswstudy.eduservice.bean.vo.CourseWebVo;
import com.lswstudy.eduservice.bean.vo.PublishCourseVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author lswstudy
 * @since 2022-01-18
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public PublishCourseVo getPublishCourseInfo(String courseId);

    CourseWebVo getBaseCourseInfo(String courseId);
}
