package com.kgc.kmall.service;

import com.kgc.kmall.bean.PmsSkuInfo;

import java.util.List;

public interface SkuService {
    String saveSkuInfo(PmsSkuInfo skuInfo);

    PmsSkuInfo selectById(Long skuId);

    List<PmsSkuInfo> selectBySpuId(Long spuId);
}
