package com.lswstudy.eduorder.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.lswstudy.commonutils.ResultData;
import com.lswstudy.eduorder.service.PayLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author lswstudy
 * @since 2022-02-28
 */
@RestController
@RequestMapping("/eduorder/paylog")
public class PayLogController {
    @Autowired
    private PayLogService payLogService;

    @ApiOperation("生成微信支付二维码")
    @GetMapping("createQRCode/{orderNo}")
    public ResultData createQRCode(@PathVariable String orderNo){
        Map map = payLogService.createQRCode(orderNo);
        return ResultData.ok().data(map);
    }

    @ApiOperation("根据订单号查询订单状态")
    @GetMapping("queryPayStatus/{orderNo}")
    public ResultData queryPayStatus(@PathVariable String orderNo) {
        //调用查询接口
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        if (map == null) {//出错
            return ResultData.error().message("支付出错");
        }
        if (map.get("trade_state").equals("SUCCESS")) {//如果成功
            //更改订单状态
            payLogService.updateOrderStatus(map);
            return ResultData.ok().message("支付成功");
        }

        return ResultData.ok().code(25000).message("支付中");
    }
}

