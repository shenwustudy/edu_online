package com.lswstudy.eduservice.controller;


import com.lswstudy.commonutils.ResultData;
import com.lswstudy.eduservice.bean.EduChapter;
import com.lswstudy.eduservice.bean.chapter.ChapterBean;
import com.lswstudy.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author lswstudy
 * @since 2022-01-18
 */
@RestController
@RequestMapping("/eduservice/chapter")
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;

    //返回课程大纲
    @GetMapping("getChapterVideo/{courseId}")
    public ResultData getChapterVideo(@PathVariable String courseId){
        List<ChapterBean> list = chapterService.getChapterVideoById(courseId);
        return ResultData.ok().data("chapterVideo",list);
    }

    //添加章节
    @PostMapping("addChapter")
    public ResultData addChapter(@RequestBody EduChapter eduChapter){
        chapterService.save(eduChapter);
        return ResultData.ok();
    }

    //根据id查询章节
    @GetMapping("getChapterInfo/{chapterId}")
    public ResultData getChapterInfo(@PathVariable String chapterId){
        EduChapter eduChapter = chapterService.getById(chapterId);
        return ResultData.ok().data("chapter",eduChapter);
    }

    //修改章节
    @PostMapping("updateChapter")
    public ResultData updateChapter(@RequestBody EduChapter eduChapter){
        chapterService.updateById(eduChapter);
        return ResultData.ok();
    }

    //删除章节
    @DeleteMapping("{chapterId}")
    public ResultData deleteChapter(@PathVariable String chapterId){
        boolean isDelete = chapterService.deleteChapter(chapterId);
        if (isDelete){
            return ResultData.ok();
        }else {
            return ResultData.error();
        }
    }
}

