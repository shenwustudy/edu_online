package com.lswstudy.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lswstudy.commonutils.JwtUtils;
import com.lswstudy.commonutils.MD5;
import com.lswstudy.educenter.bean.UcenterMember;
import com.lswstudy.educenter.bean.vo.RegisterVo;
import com.lswstudy.educenter.mapper.UcenterMemberMapper;
import com.lswstudy.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lswstudy.servicebase.exceptionhandler.EduException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author lswstudy
 * @since 2022-02-25
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(UcenterMember member) {
        String phoneId = member.getMobile();
        String password = member.getPassword();
        if (StringUtils.isEmpty(phoneId) || StringUtils.isEmpty(password)) throw new EduException(20001,"登录失败");

        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",phoneId);
        UcenterMember loginMember = baseMapper.selectOne(wrapper);
        if (loginMember == null) throw new EduException(20001,"手机号不存在");

        if (!MD5.encrypt(password).equals(loginMember.getPassword())) throw new EduException(20001,"密码错误");

        if (loginMember.getIsDisabled()) throw new EduException(20001,"该用户已被禁用!");

        String jwtToken = JwtUtils.getJwtToken(loginMember.getId(), loginMember.getNickname());
        return jwtToken;
    }

    @Override
    public void register(RegisterVo registerVo) {
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();

        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname)){
            throw new EduException(20001,"注册信息不完整");
        }

        //判断手机号是否已经注册
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        if (baseMapper.selectCount(wrapper) != 0) throw new EduException(20001,"该手机号已被使用");

        //判断验证码是否正确
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if (StringUtils.isEmpty(redisCode)) throw new EduException(20001,"验证码已过期");
        if (!code.equals(redisCode)) throw new EduException(20001,"验证码错误!");

        //将注册信息保存到数据库中
        UcenterMember registerObject = new UcenterMember();
        registerObject.setMobile(mobile);
        registerObject.setNickname(nickname);
        registerObject.setPassword(MD5.encrypt(password));
        registerObject.setAvatar("https://guli-file-190513.oss-cn-beijing.aliyuncs.com/avatar/default.jpg");
        baseMapper.insert(registerObject);
    }

    @Override
    public UcenterMember getByOpenid(String openid) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid);

        UcenterMember member = baseMapper.selectOne(queryWrapper);
        return member;
    }

    @Override
    public Integer countRegisterByDate(String date) {
        return baseMapper.selectRegisterCount(date);
    }

}
