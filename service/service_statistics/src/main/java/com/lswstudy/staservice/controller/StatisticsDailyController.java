package com.lswstudy.staservice.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.lswstudy.commonutils.ResultData;
import com.lswstudy.staservice.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author lswstudy
 * @since 2022-03-03
 */
@RestController
@RequestMapping("/staservice/statisticsdaily")
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService dailyService;

    @PostMapping("{date}")
    public ResultData createStatisticsByDate(@PathVariable String date) {
        dailyService.createStatisticsByDate(date);
        return ResultData.ok();
    }

    @GetMapping("showTable/{begin}/{end}/{type}")
    public ResultData showTable(@PathVariable String begin,@PathVariable String end,@PathVariable String type){
        Map<String, Object> map = dailyService.getChartData(begin, end, type);
        return ResultData.ok().data(map);
    }

}

