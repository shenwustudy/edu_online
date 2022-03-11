package com.lswstudy.eduservice.controller;


import com.lswstudy.commonutils.ResultData;
import com.lswstudy.eduservice.bean.subject.SubjectBean;
import com.lswstudy.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author lswstudy
 * @since 2022-01-12
 */
@RestController
@RequestMapping("/eduservice/subject")
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    //添加课程分类
    //获取上传过来的文件，把文件内容读取出来
    @PostMapping("addSubject")
    public ResultData addSubject(MultipartFile file){
        //上传过来excel文件
        subjectService.saveSubject(file,subjectService);
        return ResultData.ok();
    }

    //课程分类列表
    @GetMapping("listSubject")
    public ResultData listSubject(){
        List<SubjectBean> list = subjectService.getAllSubject();
        return ResultData.ok().data("list",list);
    }
}

