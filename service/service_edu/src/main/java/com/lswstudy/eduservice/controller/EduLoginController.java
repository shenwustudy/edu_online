package com.lswstudy.eduservice.controller;

import com.lswstudy.commonutils.ResultCode;
import com.lswstudy.commonutils.ResultData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author lswstudy
 * @create 2021-11-11-12:03
 */
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {

    //login()
    @PostMapping("login")
    public ResultData login(){
        return ResultData.ok().data("token","admin");
    }

    //getInfo()
    @GetMapping("getInfo")
    public ResultData getInfo(){
        return ResultData.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
