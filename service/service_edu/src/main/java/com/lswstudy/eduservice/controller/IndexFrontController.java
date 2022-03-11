package com.lswstudy.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lswstudy.commonutils.ResultData;
import com.lswstudy.eduservice.bean.EduCourse;
import com.lswstudy.eduservice.bean.EduTeacher;
import com.lswstudy.eduservice.service.EduCourseService;
import com.lswstudy.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lswstudy
 * @create 2022-02-23-13:37
 */
@RestController
@RequestMapping("/eduservice/indexfront")
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;

    //查询前8条热门课程，查询前4条名师
    @Cacheable(key = "'getHotCourseTeacher'",value = "front")
    @GetMapping("index")
    public ResultData index() {
        //查询前8条热门课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> eduList = courseService.list(wrapper);

        //查询前4条名师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);

        return ResultData.ok().data("eduList",eduList).data("teacherList",teacherList);
    }
}
