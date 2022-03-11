package com.lswstudy.eduservice.bean.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lswstudy
 * @create 2022-02-27-10:09
 */
@Data
public class ChapterVo {

    private String id;

    private String title;

    //表示小节
    private List<VideoVo> children = new ArrayList<>();
}
