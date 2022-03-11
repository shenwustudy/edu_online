package com.lswstudy.aclservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.lswstudy.aclservice.service.IndexService;
import com.lswstudy.commonutils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author lswstudy
 * @create 2022-03-05-14:28
 */
@RestController
@RequestMapping("/admin/acl/index")
public class IndexController {
    @Autowired
    private IndexService indexService;

    /**
     * 根据token获取用户信息
     */
    @GetMapping("info")
    public ResultData info(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return ResultData.ok().data(userInfo);
    }

    /**
     * 获取菜单
     * @return
     */
    @GetMapping("menu")
    public ResultData getMenu(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return ResultData.ok().data("permissionList", permissionList);
    }

    @PostMapping("logout")
    public ResultData logout(){
        return ResultData.ok();
    }
}
