package com.lswstudy.eduorder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lswstudy.commonutils.CourseWebVoOrder;
import com.lswstudy.commonutils.OrderNoUtil;
import com.lswstudy.commonutils.UcenterMemberOrder;
import com.lswstudy.eduorder.bean.Order;
import com.lswstudy.eduorder.client.EduClient;
import com.lswstudy.eduorder.client.UcenterClient;
import com.lswstudy.eduorder.mapper.OrderMapper;
import com.lswstudy.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author lswstudy
 * @since 2022-02-28
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String createOrder(String courseId, String id) {
        CourseWebVoOrder courseInfoById = eduClient.getCourseInfoById(courseId);
        UcenterMemberOrder memberInfoById = ucenterClient.getMemberInfoById(id);

        Order order = new Order();
        String orderNo = OrderNoUtil.getOrderNo();
        order.setOrderNo(orderNo);
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoById.getTitle());
        order.setCourseCover(courseInfoById.getCover());
        order.setTeacherName(courseInfoById.getTeacherName());
        order.setTotalFee(courseInfoById.getPrice());
        order.setMemberId(id);
        order.setMobile(memberInfoById.getMobile());
        order.setNickname(memberInfoById.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);

        return orderNo;
    }

    @Override
    public int getCount(String memberId, String id, int i) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("member_id",memberId);
        wrapper.eq("course_id",id);
        wrapper.eq("status",1);
        return baseMapper.selectCount(wrapper);
    }

}
