package com.lswstudy.eduservice.service;

import com.lswstudy.eduservice.bean.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lswstudy.eduservice.bean.subject.SubjectBean;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author lswstudy
 * @since 2022-01-12
 */
public interface EduSubjectService extends IService<EduSubject> {

    //添加课程分类
    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);

    List<SubjectBean> getAllSubject();
}
