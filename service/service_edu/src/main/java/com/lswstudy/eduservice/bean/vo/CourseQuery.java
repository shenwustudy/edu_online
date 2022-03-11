package com.lswstudy.eduservice.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lswstudy
 * @create 2022-02-17-14:07
 */
@ApiModel(value = "Course查询对象", description = "课程查询对象封装")
@Data
public class CourseQuery implements Serializable {
    private static final long serialVersionUID = 2L;
    @ApiModelProperty(value = "课程名称,模糊查询")
    private String title;
    @ApiModelProperty(value = "状态 Normal已发布 Draft未发布")
    private String status;
    @ApiModelProperty(value = "查询开始时间", example = "2021-10-08-16:33")
    private String begin;
    @ApiModelProperty(value = "查询结束时间", example = "2021-10-08-16:33")
    private String end;
}
