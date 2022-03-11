package com.lswstudy.eduservice.bean.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lswstudy
 * @create 2022-01-27-13:05
 */
@Data
public class ChapterBean {
    private String id;

    private String title;

    //表示一个章节的小节，当此类代表课程的小节时此属性为null
    private List<ChapterBean> children = new ArrayList<>();
}
