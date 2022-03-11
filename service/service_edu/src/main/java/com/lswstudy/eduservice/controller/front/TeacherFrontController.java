package com.lswstudy.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lswstudy.commonutils.ResultData;
import com.lswstudy.eduservice.bean.EduCourse;
import com.lswstudy.eduservice.bean.EduTeacher;
import com.lswstudy.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author lswstudy
 * @create 2022-02-26-11:41
 */
@RestController
@RequestMapping("/eduservice/teacherfront")
public class TeacherFrontController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "分页讲师列表")
    @GetMapping(value = "getTeacherPageList/{page}/{limit}")
    public ResultData pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        Page<EduTeacher> pageParam = new Page<EduTeacher>(page, limit);

        Map<String, Object> map = eduTeacherService.pageListWeb(pageParam);

        return  ResultData.ok().data(map);
    }

    @ApiOperation(value = "根据讲师id查询讲师基本信息以及讲师讲的课程")
    @GetMapping(value = "getTeacherInfoById/{teacherId}")
    public ResultData getTeacherInfoById(@PathVariable String teacherId){
        EduTeacher teacherInfo = eduTeacherService.getById(teacherId);
        List<EduCourse> list = eduTeacherService.getCourseInfoByTeacherId(teacherId);
        return ResultData.ok().data("teacherInfo",teacherInfo).data("courseInfo",list);
    }

}
