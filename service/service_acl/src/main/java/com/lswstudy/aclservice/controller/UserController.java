package com.lswstudy.aclservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lswstudy.aclservice.bean.User;
import com.lswstudy.aclservice.service.RoleService;
import com.lswstudy.aclservice.service.UserService;
import com.lswstudy.commonutils.MD5;
import com.lswstudy.commonutils.ResultData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author lswstudy
 * @create 2022-03-05-14:43
 */
@RestController
@RequestMapping("/admin/acl/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获取管理用户分页列表")
    @GetMapping("{page}/{limit}")
    public ResultData index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    User userQueryVo) {
        Page<User> pageParam = new Page<>(page, limit);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(userQueryVo.getUsername())) {
            wrapper.like("username",userQueryVo.getUsername());
        }

        IPage<User> pageModel = userService.page(pageParam, wrapper);
        return ResultData.ok().data("items", pageModel.getRecords()).data("total", pageModel.getTotal());
    }

    @ApiOperation(value = "新增管理用户")
    @PostMapping("save")
    public ResultData save(@RequestBody User user) {
        user.setPassword(MD5.encrypt(user.getPassword()));
        userService.save(user);
        return ResultData.ok();
    }

    @ApiOperation(value = "修改管理用户")
    @PutMapping("update")
    public ResultData updateById(@RequestBody User user) {
        userService.updateById(user);
        return ResultData.ok();
    }

    @ApiOperation(value = "删除管理用户")
    @DeleteMapping("remove/{id}")
    public ResultData remove(@PathVariable String id) {
        userService.removeById(id);
        return ResultData.ok();
    }

    @ApiOperation(value = "根据id列表删除管理用户")
    @DeleteMapping("batchRemove")
    public ResultData batchRemove(@RequestBody List<String> idList) {
        userService.removeByIds(idList);
        return ResultData.ok();
    }

    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public ResultData toAssign(@PathVariable String userId) {
        Map<String, Object> roleMap = roleService.findRoleByUserId(userId);
        return ResultData.ok().data(roleMap);
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public ResultData doAssign(@RequestParam String userId,@RequestParam String[] roleId) {
        roleService.saveUserRoleRealtionShip(userId,roleId);
        return ResultData.ok();
    }

    @ApiOperation(value = "根据用户id查询用户信息")
    @GetMapping("get/{id}")
    public ResultData getInfo(@PathVariable String id){
        User user = userService.getById(id);
        if (StringUtils.isEmpty(user.getUsername())){
            user.setUsername("该用户暂未设置用户名!");
        }
        return ResultData.ok().data("items",user);
    }

}
