package com.lswstudy.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lswstudy.eduservice.bean.EduCourse;
import com.lswstudy.eduservice.bean.EduCourseDescription;
import com.lswstudy.eduservice.bean.EduVideo;
import com.lswstudy.eduservice.bean.vo.CourseInfoVo;
import com.lswstudy.eduservice.bean.vo.CourseQueryVo;
import com.lswstudy.eduservice.bean.vo.CourseWebVo;
import com.lswstudy.eduservice.bean.vo.PublishCourseVo;
import com.lswstudy.eduservice.client.VodClient;
import com.lswstudy.eduservice.mapper.EduCourseMapper;
import com.lswstudy.eduservice.service.EduChapterService;
import com.lswstudy.eduservice.service.EduCourseDescriptionService;
import com.lswstudy.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lswstudy.eduservice.service.EduVideoService;
import com.lswstudy.servicebase.exceptionhandler.EduException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author lswstudy
 * @since 2022-01-18
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private VodClient vodClient;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //将课程信息加入edu_course表中
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0)
            throw new EduException(20001,"添加课程信息失败");

        //获取添加的课程的id
        String courseId = eduCourse.getId();

        //将课程简介加入edu_course_description表中
        EduCourseDescription description = new EduCourseDescription();
        description.setDescription(courseInfoVo.getDescription());
        //将课程的id设置为课程描述的id(即创建俩张表的关联条件:id相同)
        description.setId(courseId);
        eduCourseDescriptionService.save(description);

        return courseId;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);

        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);

        EduCourseDescription description = eduCourseDescriptionService.getById(courseId);
        BeanUtils.copyProperties(description,courseInfoVo);
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int isSuccessCourse = baseMapper.updateById(eduCourse);
        if (isSuccessCourse == 0)
            throw new EduException(20001,"修改课程信息失败");
        EduCourseDescription description = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,description);
        boolean isSuccessDescription = eduCourseDescriptionService.updateById(description);
        if (!isSuccessDescription)
            throw new EduException(20001,"修改课程描述失败");
    }

    @Override
    public PublishCourseVo getPublishCourseInfo(String id) {
        PublishCourseVo info = baseMapper.getPublishCourseInfo(id);
        return info;
    }

    //删除课程(同时要将该课程下的章节、章节下的小节、课程描述等信息全部删掉)
    @Override
    public void removeCourseById(String courseId) {
        eduVideoService.removeVideoByCourseId(courseId);
        eduChapterService.removeChapterByCourseId(courseId);
        eduCourseDescriptionService.removeById(courseId);
        baseMapper.deleteById(courseId);
    }

    //将课程下所有的视频从阿里云中删除
    @Override
    public void removeVideoById(String courseId) {
        eduVideoService.removeVideoListByCourseId(courseId);
    }

    @Override
    public Map<String, Object> pageListWeb(Page<EduCourse> pageParam, CourseQueryVo courseQuery) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseQuery.getSubjectParentId())) {
            queryWrapper.eq("subject_parent_id", courseQuery.getSubjectParentId());
        }

        if (!StringUtils.isEmpty(courseQuery.getSubjectId())) {
            queryWrapper.eq("subject_id", courseQuery.getSubjectId());
        }

        if (!StringUtils.isEmpty(courseQuery.getBuyCountSort())) {
            queryWrapper.orderByDesc("buy_count");
        }

        if (!StringUtils.isEmpty(courseQuery.getGmtCreateSort())) {
            queryWrapper.orderByDesc("gmt_create");
        }

        if (!StringUtils.isEmpty(courseQuery.getPriceSort())) {
            queryWrapper.orderByDesc("price");
        }

        baseMapper.selectPage(pageParam, queryWrapper);

        List<EduCourse> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    @Override
    public CourseWebVo getBaseCourseInfo(String id) {
        this.updatePageViewCount(id);
        return baseMapper.getBaseCourseInfo(id);
    }

    @Override
    public void updatePageViewCount(String id) {
        EduCourse course = baseMapper.selectById(id);
        course.setViewCount(course.getViewCount() + 1);
        baseMapper.updateById(course);
    }

}
