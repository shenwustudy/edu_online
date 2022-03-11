package com.lswstudy.educms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lswstudy.educms.bean.CrmBanner;
import com.lswstudy.educms.mapper.CrmBannerMapper;
import com.lswstudy.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author lswstudy
 * @since 2022-02-23
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public CrmBanner getBannerById(String id) {
        CrmBanner crmBanner = baseMapper.selectById(id);
        return crmBanner;
    }

    @Override
    public void saveBanner(CrmBanner banner) {
        baseMapper.insert(banner);
    }

    @Override
    public void updateBannerById(CrmBanner banner) {
        baseMapper.updateById(banner);
    }

    @Override
    public void removeBannerById(String id) {
        baseMapper.deleteById(id);
    }

    @Cacheable(key = "'selectIndexList'",value = "banner")
    @Override
    public List<CrmBanner> selectIndexList() {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 2");
        List<CrmBanner> list = baseMapper.selectList(wrapper);
        return list;
    }
}
