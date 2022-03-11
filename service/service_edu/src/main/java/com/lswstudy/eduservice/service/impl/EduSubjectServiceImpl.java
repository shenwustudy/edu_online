package com.lswstudy.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lswstudy.eduservice.bean.EduSubject;
import com.lswstudy.eduservice.bean.excel.SubjectData;
import com.lswstudy.eduservice.bean.subject.SubjectBean;
import com.lswstudy.eduservice.listener.SubjectExcelListener;
import com.lswstudy.eduservice.mapper.EduSubjectMapper;
import com.lswstudy.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author lswstudy
 * @since 2022-01-12
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try {
            //得到文件的输入流
            InputStream inputStream = file.getInputStream();

            //调用easyExcel的方法进行读取
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SubjectBean> getAllSubject() {
        ArrayList<SubjectBean> list = new ArrayList<>();
        //查询一共多少一级分类课程来确定循环次数
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<EduSubject>().eq("parent_id", "0");
        int i = baseMapper.selectCount(wrapperOne);
        List<EduSubject> oneEduSubjects = baseMapper.selectList(wrapperOne);

        for (int k = 0; k < i; k++) {
            SubjectBean subjectBean = new SubjectBean();
            //给一级分类注入id和title属性
            subjectBean.setId(oneEduSubjects.get(k).getId());
            subjectBean.setTitle(oneEduSubjects.get(k).getTitle());

            //得到一级分类的id就是对应的二级分类的pid
            String pid = oneEduSubjects.get(k).getId();
            //根据pid查询出其对应的二级分类
            QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<EduSubject>().eq("parent_id", pid);
            int j = baseMapper.selectCount(wrapperTwo);
            List<EduSubject> twoEduSubjects = baseMapper.selectList(wrapperTwo);
            //一级分类的children属性就是对应二级分类，遍历查询到的二级分类里将其注入
            ArrayList<SubjectBean> children = new ArrayList<>();
            for (int l = 0; l < j; l++) {
                SubjectBean subjectBean2 = new SubjectBean();
                //给二级分类注入id和title属性
                subjectBean2.setId(twoEduSubjects.get(l).getId());
                subjectBean2.setTitle(twoEduSubjects.get(l).getTitle());
                //将二级分类注入到一级分类的children属性中
                children.add(subjectBean2);
            }
            //给一级分类注入children属性
            subjectBean.setChildren(children);
            //将注入属性完成的一级分类添加到list中
            list.add(subjectBean);
        }

        return list;
    }
}
