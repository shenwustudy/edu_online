package com.lswstudy.educms.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.lswstudy.commonutils.ResultData;
import com.lswstudy.educms.bean.CrmBanner;
import com.lswstudy.educms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lswstudy
 * @create 2022-02-23-13:18
 */
@RestController
@RequestMapping("/educms/bannerfront")
public class CrmBannerFrontController {
    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "获取首页banner")
    @GetMapping("getAllBanner")
    public ResultData index() {
        List<CrmBanner> list = bannerService.selectIndexList();
        return ResultData.ok().data("bannerList", list);
    }

}
