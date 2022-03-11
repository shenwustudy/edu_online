package com.lswstudy.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lswstudy.commonutils.JwtUtils;
import com.lswstudy.commonutils.ResultData;
import com.lswstudy.commonutils.UcenterMemberVo;
import com.lswstudy.eduservice.bean.EduComment;
import com.lswstudy.eduservice.client.UcenterClient;
import com.lswstudy.eduservice.service.EduCommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author lswstudy
 * @since 2022-02-27
 */
@RestController
@RequestMapping("/eduservice/comment")
public class EduCommentController {
    @Autowired
    private EduCommentService commentService;
    @Autowired
    private UcenterClient ucenterClient;

    //根据课程id查询评论列表
    @ApiOperation(value = "评论分页列表")
    @GetMapping("{page}/{limit}")
    public ResultData index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    String courseId) {
        Page<EduComment> pageParam = new Page<>(page, limit);

        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);

        commentService.page(pageParam,wrapper);
        List<EduComment> commentList = pageParam.getRecords();

        Map<String, Object> map = new HashMap<>();
        map.put("items", commentList);
        map.put("current", pageParam.getCurrent());
        map.put("pages", pageParam.getPages());
        map.put("size", pageParam.getSize());
        map.put("total", pageParam.getTotal());
        map.put("hasNext", pageParam.hasNext());
        map.put("hasPrevious", pageParam.hasPrevious());
        return ResultData.ok().data(map);
    }

    @ApiOperation(value = "添加评论")
    @PostMapping("auth/save")
    public ResultData save(@RequestBody EduComment comment, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberId)) {
            return ResultData.error().code(28004).message("请登录");
        }
        comment.setMemberId(memberId);

        UcenterMemberVo ucenterInfo = ucenterClient.getInfo(memberId);

        comment.setNickname(ucenterInfo.getNickname());
        comment.setAvatar(ucenterInfo.getAvatar());

        commentService.save(comment);
        return ResultData.ok();
    }

}

