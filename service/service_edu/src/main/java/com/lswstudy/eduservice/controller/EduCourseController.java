package com.lswstudy.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lswstudy.commonutils.ResultData;
import com.lswstudy.eduservice.bean.EduCourse;
import com.lswstudy.eduservice.bean.vo.CourseInfoVo;
import com.lswstudy.eduservice.bean.vo.CourseQuery;
import com.lswstudy.eduservice.bean.vo.PublishCourseVo;
import com.lswstudy.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author lswstudy
 * @since 2022-01-18
 */
@RestController
@RequestMapping("/eduservice/course")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    //添加课程基本信息
    @PostMapping("addCourseInfo")
    public ResultData addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String courseId = eduCourseService.saveCourseInfo(courseInfoVo);
        return ResultData.ok().data("courseId",courseId);
    }

    //根据课程id查询课程信息
    @GetMapping("getCourseInfo/{courseId}")
    public ResultData getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfo = eduCourseService.getCourseInfo(courseId);
        return ResultData.ok().data("courseInfo",courseInfo);
    }

    //修改课程信息
    @PostMapping("updateCourseInfo")
    public ResultData updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);
        return ResultData.ok().data("courseId",courseInfoVo.getId());
    }

    //根据id查询最终发布时需确认的课程信息
    @GetMapping("getPublishCourseInfo/{id}")
    public ResultData getPublishCourseInfo(@PathVariable String id){
        PublishCourseVo info = eduCourseService.getPublishCourseInfo(id);
        return ResultData.ok().data("publishCourseInfo",info);
    }

    //课程的最终发布(将课程在edu_course表中的status属性从Draft修改为Normal)
    @PostMapping("publishCourse/{courseId}")
    public ResultData publishCourse(@PathVariable String courseId){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return ResultData.ok();
    }

    //带分页的条件查询课程
    @PostMapping("getCourseList/{current}/{size}")
    public ResultData getCourseList(@PathVariable long current, @PathVariable long size, @RequestBody(required = false) CourseQuery courseQuery){
        Page<EduCourse> eduCoursePage = new Page<>(current,size);

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        String begin = courseQuery.getBegin();
        String end = courseQuery.getEnd();
        if (!StringUtils.isEmpty(title)) wrapper.like("title",title);
        if (!StringUtils.isEmpty(status)) wrapper.eq("status",status);
        if (!StringUtils.isEmpty(begin)) wrapper.ge("gmt_create",begin);
        if (!StringUtils.isEmpty(end)) wrapper.le("gmt_create",end);

        //根据创建时间来对数据进行排序
        wrapper.orderByDesc("gmt_create");

        eduCourseService.page(eduCoursePage, wrapper);
        long total = eduCoursePage.getTotal();
        List<EduCourse> records = eduCoursePage.getRecords();
        return ResultData.ok().data("rows",records).data("total",total);
    }

    //删除课程
    @PostMapping("removeCourseById/{courseId}")
    public ResultData removeCourseById(@PathVariable String courseId){
        eduCourseService.removeVideoById(courseId);
        eduCourseService.removeCourseById(courseId);
        return ResultData.ok();
    }
}

