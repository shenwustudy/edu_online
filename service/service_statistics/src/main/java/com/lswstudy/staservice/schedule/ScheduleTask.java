package com.lswstudy.staservice.schedule;

import com.lswstudy.staservice.service.StatisticsDailyService;
import com.lswstudy.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lswstudy
 * @create 2022-03-03-15:27
 */
@Component
public class ScheduleTask {
    @Autowired
    private StatisticsDailyService dailyService;

    /**
     * 每天凌晨1点执行定时
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void task2() {
        //获取上一天的日期
        String date = DateUtil.formatDate(DateUtil.addDays(new Date(), -1));
        dailyService.createStatisticsByDate(date);

    }
}
