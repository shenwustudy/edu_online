package com.lswstudy.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lswstudy.commonutils.CourseWebVoOrder;
import com.lswstudy.commonutils.JwtUtils;
import com.lswstudy.commonutils.ResultData;
import com.lswstudy.eduservice.bean.EduCourse;
import com.lswstudy.eduservice.bean.vo.ChapterVo;
import com.lswstudy.eduservice.bean.vo.CourseQueryVo;
import com.lswstudy.eduservice.bean.vo.CourseWebVo;
import com.lswstudy.eduservice.client.OrderClient;
import com.lswstudy.eduservice.service.EduChapterService;
import com.lswstudy.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author lswstudy
 * @create 2022-02-27-9:16
 */
@RestController
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrderClient orderClient;

    @ApiOperation(value = "分页课程列表")
    @PostMapping(value = "{page}/{limit}")
    public ResultData pageList(@PathVariable Long page,@PathVariable Long limit,@RequestBody(required = false) CourseQueryVo courseQuery){
        Page<EduCourse> pageParam = new Page<EduCourse>(page, limit);
        Map<String, Object> map = courseService.pageListWeb(pageParam, courseQuery);
        return  ResultData.ok().data(map);
    }

    @ApiOperation(value = "根据课程id查询出课程的基本信息、章节信息、小节信息并返回")
    @PostMapping("getFrontCourseInfo/{courseId}")
    public ResultData getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        //根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);
        //根据课程id和用户id查询当前课程是否已经支付过了
        boolean isBuy = orderClient.isBuyCourse(courseId,JwtUtils.getMemberIdByJwtToken(request));
        System.out.println(isBuy);
        return ResultData.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList).data("isBuy",isBuy);
    }

    @ApiOperation("根据课程的id来查询课程信息")
    @GetMapping("getCourseInfoById/{id}")
    public CourseWebVoOrder getCourseInfoById(@PathVariable String id){
        CourseWebVo course = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseInfo = new CourseWebVoOrder();
        BeanUtils.copyProperties(course,courseInfo);
        return courseInfo;
    }

    @GetMapping("getMemberId")
    public ResultData getMemberId(HttpServletRequest request){
        return ResultData.ok().data("memberId",JwtUtils.getMemberIdByJwtToken(request));
    }

}
