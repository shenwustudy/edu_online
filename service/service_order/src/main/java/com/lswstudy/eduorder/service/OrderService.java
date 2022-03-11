package com.lswstudy.eduorder.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lswstudy.eduorder.bean.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author lswstudy
 * @since 2022-02-28
 */
public interface OrderService extends IService<Order> {

    String createOrder(String courdeId, String memberId);

    int getCount(String memberId, String id, int i);
}
