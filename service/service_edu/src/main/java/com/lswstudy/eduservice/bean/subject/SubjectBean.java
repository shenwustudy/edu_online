package com.lswstudy.eduservice.bean.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lswstudy
 * @create 2022-01-17-13:34
 */
@Data
public class SubjectBean {
    private String id;
    private String title;

    //当实例为一级分类课程时，此属性为其对应的二级分类list集合，
    //如果此实例为二级分类课程，则此属性为null.
    private List<SubjectBean> children = new ArrayList<>();
}
