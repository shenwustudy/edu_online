package com.lswstudy.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lswstudy.commonutils.JwtUtils;
import com.lswstudy.commonutils.ResultData;
import com.lswstudy.eduorder.bean.Order;
import com.lswstudy.eduorder.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author lswstudy
 * @since 2022-02-28
 */
@RestController
@RequestMapping("/eduorder/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "根据课程id以及从header中获取的用户id生成订单信息，并返回订单号")
    @PostMapping("createOrder/{courdeId}")
    public ResultData createOrder(@PathVariable String courdeId, HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        String orderNo = orderService.createOrder(courdeId,memberId);
        return ResultData.ok().data("orderId",orderNo);
    }

    @ApiOperation(value = "根据订单号查询订单信息")
    @GetMapping("getOrderInfoById/{orderNo}")
    public ResultData getOrderInfoById(@PathVariable String orderNo){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        Order order = orderService.getOne(wrapper);
        return ResultData.ok().data("order",order);
    }

    @PostMapping("isBuyCourse/{id}/{memberId}")
    public boolean isBuyCourse(@PathVariable String id,@PathVariable String memberId) {
        System.out.println("这个方法被成功调用");
        //订单状态是1表示支付成功
        int count = orderService.getCount(memberId,id,1);
        if(count>0) {
            return true;
        } else {
            return false;
        }
    }


}

