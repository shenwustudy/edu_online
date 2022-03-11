package com.lswstudy.eduservice.bean.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lswstudy
 * @create 2022-02-13-13:14
 */
@Data
public class PublishCourseVo {
    private String id;
    private String title;
    private String subjectParentTitle;
    private String subjectTitle;
    private String teacherName;
    private Integer lessonNum;
    private String description;
    private String cover;
    private String price;
}
