package com.lswstudy.eduorder.service;

import com.lswstudy.eduorder.bean.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author lswstudy
 * @since 2022-02-28
 */
public interface PayLogService extends IService<PayLog> {

    Map createQRCode(String orderNo);

    Map<String, String> queryPayStatus(String orderNo);

    void updateOrderStatus(Map<String, String> map);
}
