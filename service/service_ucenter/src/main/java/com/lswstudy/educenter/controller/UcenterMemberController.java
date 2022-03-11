package com.lswstudy.educenter.controller;


import com.lswstudy.commonutils.JwtUtils;
import com.lswstudy.commonutils.ResultData;
import com.lswstudy.commonutils.UcenterMemberOrder;
import com.lswstudy.educenter.bean.UcenterMember;
import com.lswstudy.educenter.bean.vo.RegisterVo;
import com.lswstudy.educenter.service.UcenterMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author lswstudy
 * @since 2022-02-25
 */
@RestController
@RequestMapping("/educenter/member")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public ResultData login(@RequestBody UcenterMember member){
        String token = ucenterMemberService.login(member);
        return ResultData.ok().data("token",token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public ResultData register(@RequestBody RegisterVo registerVo){
        ucenterMemberService.register(registerVo);
        System.out.println("???");
        return ResultData.ok();
    }

    @ApiOperation(value = "根据token获取用户信息")
    @GetMapping("getMemberInfoByToken")
    public ResultData getMemberInfoByToken(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember ucenterMember = ucenterMemberService.getById(memberId);
        return ResultData.ok().data("memberInfo",ucenterMember);
    }

    @ApiOperation(value = "根据用户id获取用户信息")
    @GetMapping("getMemberInfoById/{id}")
    public UcenterMemberOrder getMemberInfoById(@PathVariable String id){
        UcenterMember member = ucenterMemberService.getById(id);
        UcenterMemberOrder memberInfo = new UcenterMemberOrder();
        BeanUtils.copyProperties(member,memberInfo);
        return memberInfo;
    }

    @ApiOperation("查询某一天注册的人数")
    @GetMapping("countRegister/{date}")
    public ResultData countRegister(@PathVariable String date){
        Integer count = ucenterMemberService.countRegisterByDate(date);
        return ResultData.ok().data("countRegister", count);
    }
}

