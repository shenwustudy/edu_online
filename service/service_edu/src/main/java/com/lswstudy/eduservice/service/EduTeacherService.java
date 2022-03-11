package com.lswstudy.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lswstudy.eduservice.bean.EduCourse;
import com.lswstudy.eduservice.bean.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author lswstudy
 * @since 2021-10-08
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> pageListWeb(Page<EduTeacher> pageParam);

    List<EduCourse> getCourseInfoByTeacherId(String teacherId);
}
