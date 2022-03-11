package com.lswstudy.educms.service;

import com.lswstudy.educms.bean.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author lswstudy
 * @since 2022-02-23
 */
public interface CrmBannerService extends IService<CrmBanner> {

    CrmBanner getBannerById(String id);

    void saveBanner(CrmBanner banner);

    void updateBannerById(CrmBanner banner);

    void removeBannerById(String id);

    List<CrmBanner> selectIndexList();
}
