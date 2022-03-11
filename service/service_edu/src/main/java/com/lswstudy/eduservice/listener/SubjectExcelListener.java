package com.lswstudy.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lswstudy.eduservice.bean.EduSubject;
import com.lswstudy.eduservice.bean.excel.SubjectData;
import com.lswstudy.eduservice.service.EduSubjectService;
import com.lswstudy.servicebase.exceptionhandler.EduException;

/**
 * @author lswstudy
 * @create 2022-01-12-14:10
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    private EduSubjectService eduSubjectService;
    public SubjectExcelListener() {}
    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    //一行一行地读取excel表格中的内容
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null){
            throw new EduException(20001,"文件数据为空");
        }
        //一行一行的进行读取，第一个值是一级分类，第二个值是二级分类
        EduSubject existOneSubject = this.existOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        if (existOneSubject == null){
            //说明没有相同的一级分类，将其添加到数据库中
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(existOneSubject);
        }
        String pid = existOneSubject.getId();
        EduSubject existTwoSubject = this.existTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(), pid);
        if (existTwoSubject == null){
            //说明没有相同的一级分类，将其添加到数据库中
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            eduSubjectService.save(existTwoSubject);
        }
    }

    //用于判断一级分类是否重复
    private EduSubject existOneSubject(EduSubjectService subjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        return subjectService.getOne(wrapper);
    }
    //用于判断二级分类是否重复
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        return subjectService.getOne(wrapper);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


}
