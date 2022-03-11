package com.lswstudy.educenter.service;

import com.lswstudy.commonutils.ResultData;
import com.lswstudy.educenter.bean.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lswstudy.educenter.bean.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author lswstudy
 * @since 2022-02-25
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);

    UcenterMember getByOpenid(String openid);

    Integer countRegisterByDate(String date);
}
