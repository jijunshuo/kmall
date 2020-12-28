package com.kgc.kmall.manage.service;

import com.kgc.kmall.bean.*;
import com.kgc.kmall.manage.mapper.*;
import com.kgc.kmall.service.SpuService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Service
public class SpuServiceImpl implements SpuService {
    @Resource
    PmsProductInfoMapper pmsProductInfoMapper;
    @Resource
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;
    @Resource
    PmsProductImageMapper pmsProductImageMapper;
    @Resource
    PmsProductSaleAttrMapper pmsProductSaleAttrMapper;
    @Resource
    PmsProductSaleAttrValueMapper pmsProductSaleAttrValueMapper;

    @Override
    public List<PmsProductInfo> spuList(Long catalog3Id) {
        PmsProductInfoExample example = new PmsProductInfoExample();
        example.createCriteria().andCatalog3IdEqualTo(catalog3Id);
        return pmsProductInfoMapper.selectByExample(example);
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {

        return pmsBaseSaleAttrMapper.selectByExample(null);
    }

    @Override
    public Integer saveSpuInfo(PmsProductInfo pmsProductInfo) {

        try {
            //添加商品
            pmsProductInfoMapper.insert(pmsProductInfo);
            Long productId = pmsProductInfo.getId();
            System.out.println(productId);
            //添加商品图片
            List<PmsProductImage> spuImageList = pmsProductInfo.getSpuImageList();
            if (spuImageList != null && spuImageList.size() > 0) {
                int i = pmsProductImageMapper.eachInsert(spuImageList, productId);
                System.out.println(i);
            }
            //添加商品属性
            //添加商品属性值
            List<PmsProductSaleAttr> spuSaleAttrList = pmsProductInfo.getSpuSaleAttrList();
            if (spuSaleAttrList != null && spuSaleAttrList.size() > 0) {
                for (PmsProductSaleAttr pmsProductSaleAttr : spuSaleAttrList) {
                    pmsProductSaleAttr.setProductId(pmsProductInfo.getId());
                    List<PmsProductSaleAttrValue> spuSaleAttrValueList = pmsProductSaleAttr.getSpuSaleAttrValueList();


                    int insert = pmsProductSaleAttrMapper.insert(pmsProductSaleAttr);
                    int i = pmsProductSaleAttrValueMapper.eachInsertByspuSaleAttrValueList(spuSaleAttrValueList, productId);
                    System.out.println(insert);
                    System.out.println(i);
                }
            }
            return 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }


    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrList(Long spuId) {
        PmsProductSaleAttrExample example = new PmsProductSaleAttrExample();
        PmsProductSaleAttrExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(spuId);
        List<PmsProductSaleAttr> pmsProductSaleAttrList = pmsProductSaleAttrMapper.selectByExample(example);
        for (PmsProductSaleAttr pmsProductSaleAttr : pmsProductSaleAttrList) {
            PmsProductSaleAttrValueExample example1 = new PmsProductSaleAttrValueExample();
            PmsProductSaleAttrValueExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andSaleAttrIdEqualTo(pmsProductSaleAttr.getSaleAttrId());
            criteria1.andProductIdEqualTo(spuId);
            List<PmsProductSaleAttrValue> pmsProductSaleAttrValueList = pmsProductSaleAttrValueMapper.selectByExample(example1);
            pmsProductSaleAttr.setSpuSaleAttrValueList(pmsProductSaleAttrValueList);
        }
        return pmsProductSaleAttrList;
    }

    @Override
    public List<PmsProductImage> spuImageList(Long spuId) {
        PmsProductImageExample example = new PmsProductImageExample();
        PmsProductImageExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(spuId);
        return pmsProductImageMapper.selectByExample(example);
    }
}
