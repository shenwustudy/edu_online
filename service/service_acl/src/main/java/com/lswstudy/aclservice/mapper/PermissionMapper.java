package com.lswstudy.aclservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lswstudy.aclservice.bean.Permission;

import java.util.List;

/**
 * @author lswstudy
 * @create 2022-03-05-14:48
 */
public interface PermissionMapper extends BaseMapper<Permission> {


    List<String> selectPermissionValueByUserId(String id);

    List<String> selectAllPermissionValue();

    List<Permission> selectPermissionByUserId(String userId);
}

