package com.lswstudy.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lswstudy.commonutils.ResultData;
import com.lswstudy.eduservice.bean.EduTeacher;
import com.lswstudy.eduservice.bean.vo.TeacherQuery;
import com.lswstudy.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lswstudy
 * @since 2021-10-08
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    //查询讲师表中的所有数据
    @GetMapping("getAll")
    public ResultData getAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return ResultData.ok().data("items",list);

    }

    //根据id逻辑删除指定讲师
    @DeleteMapping("{id}")
    public ResultData deleteTeacherById(@PathVariable String id){
        return eduTeacherService.removeById(id) ? ResultData.ok() : ResultData.error();
    }

    //分页查询讲师
    @GetMapping("pageTeacher/{current}/{size}")
    public ResultData pageListTeacher(@PathVariable long current, @PathVariable long size){
        Page<EduTeacher> page = new Page<>(current,size);
        eduTeacherService.page(page,null);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        return ResultData.ok().data("total",total).data("rows",records);
    }

    //带分页的条件查询
    @PostMapping("pageTeacherCondition/{current}/{size}")
    public ResultData pageTeacherCondition(@PathVariable long current, @PathVariable long size,@RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> eduTeacherPage = new Page<>(current,size);

        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) wrapper.like("name",name);
        if (!StringUtils.isEmpty(level)) wrapper.eq("level",level);
        if (!StringUtils.isEmpty(begin)) wrapper.ge("gmt_create",begin);
        if (!StringUtils.isEmpty(end)) wrapper.le("gmt_create",end);

        //根据创建时间来对数据进行排序
        wrapper.orderByDesc("gmt_create");

        eduTeacherService.page(eduTeacherPage, wrapper);
        long total = eduTeacherPage.getTotal();
        List<EduTeacher> records = eduTeacherPage.getRecords();
        return ResultData.ok().data("total",total).data("rows",records);
    }

    //添加讲师的方法
    @PostMapping("addTeacher")
    public ResultData addTeacher(@RequestBody EduTeacher eduTeacher){
        return eduTeacherService.save(eduTeacher) ? ResultData.ok() : ResultData.error();
    }

    //根据讲师id进行查询
    @GetMapping("getTeacher/{id}")
    public ResultData getTeacher(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return ResultData.ok().data("teacher",eduTeacher);
    }

    //修改讲师数据
    @PostMapping("updateTeacher")
    public ResultData updateTeacher(@RequestBody EduTeacher eduTeacher){
        return eduTeacherService.updateById(eduTeacher) ? ResultData.ok() : ResultData.error();
    }
}

