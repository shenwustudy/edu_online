package com.lswstudy.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lswstudy.commonutils.ResultData;
import com.lswstudy.educms.bean.CrmBanner;
import com.lswstudy.educms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author lswstudy
 * @since 2022-02-23
 */
@RestController
@RequestMapping("/educms/banneradmin")
public class CrmBannerAdminController {

    @Autowired
    private CrmBannerService crmBannerService;

    @ApiOperation(value = "分页查询banner")
    @GetMapping("pageBanner/{page}/{limit}")
    public ResultData pageBanner(@PathVariable long page,@PathVariable long limit){
        Page<CrmBanner> bannerPage = new Page<>();
        crmBannerService.page(bannerPage,null);
        return ResultData.ok().data("items",bannerPage.getRecords()).data("total",bannerPage.getTotal());
    }
    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public ResultData get(@PathVariable String id) {
        CrmBanner banner = crmBannerService.getBannerById(id);
        return ResultData.ok().data("item", banner);
    }

    @ApiOperation(value = "新增Banner")
    @PostMapping("save")
    public ResultData save(@RequestBody CrmBanner banner) {
        crmBannerService.saveBanner(banner);
        return ResultData.ok();
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public ResultData updateById(@RequestBody CrmBanner banner) {
        crmBannerService.updateBannerById(banner);
        return ResultData.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public ResultData remove(@PathVariable String id) {
        crmBannerService.removeBannerById(id);
        return ResultData.ok();
    }

}

