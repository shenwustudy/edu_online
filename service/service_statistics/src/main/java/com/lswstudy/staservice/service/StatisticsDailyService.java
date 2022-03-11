package com.lswstudy.staservice.service;

import com.lswstudy.staservice.bean.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author lswstudy
 * @since 2022-03-03
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void createStatisticsByDate(String date);

    Map<String, Object> getChartData(String begin, String end, String type);
}
