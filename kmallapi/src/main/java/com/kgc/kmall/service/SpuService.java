package com.kgc.kmall.service;

import com.kgc.kmall.bean.*;

import java.util.List;

public interface SpuService {
    List<PmsProductInfo> spuList(Long catalog3Id);

    List<PmsBaseSaleAttr> baseSaleAttrList();


    Integer saveSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductSaleAttr> spuSaleAttrList(Long spuId);

    List<PmsProductImage> spuImageList(Long spuId);


    List<PmsProductSaleAttr> spuSaleAttrListIsCheck(Long spuId, Long skuId);


}
