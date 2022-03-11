package com.lswstudy.aclservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lswstudy.aclservice.bean.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lswstudy
 * @since 2022-03-05
 */
public interface UserService extends IService<User> {

    // 从数据库中取出用户信息
    User selectByUsername(String username);
}
