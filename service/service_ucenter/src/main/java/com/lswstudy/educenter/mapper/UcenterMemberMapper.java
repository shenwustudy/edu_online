package com.lswstudy.educenter.mapper;

import com.lswstudy.educenter.bean.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author lswstudy
 * @since 2022-02-25
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer selectRegisterCount(String date);
}
