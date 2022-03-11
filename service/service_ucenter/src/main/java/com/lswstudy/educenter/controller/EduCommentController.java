package com.lswstudy.educenter.controller;

import com.lswstudy.commonutils.UcenterMemberVo;
import com.lswstudy.educenter.bean.UcenterMember;
import com.lswstudy.educenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lswstudy
 * @create 2022-02-27-13:46
 */
@RestController
@RequestMapping("/educenter/comment")
public class EduCommentController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    //根据member_id获取会员信息
    @PostMapping("getUcenterInfo/{id}")
    public UcenterMemberVo getInfo(@PathVariable String id) {
        //根据用户id获取用户信息
        UcenterMember ucenterMember = ucenterMemberService.getById(id);
        UcenterMemberVo memeber = new UcenterMemberVo();
        BeanUtils.copyProperties(ucenterMember,memeber);
        return memeber;
    }
}
