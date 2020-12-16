package com.kgc.kmall.manage.service;

import com.kgc.kmall.bean.*;
import com.kgc.kmall.manage.mapper.PmsBaseCatalog1Mapper;
import com.kgc.kmall.manage.mapper.PmsBaseCatalog2Mapper;
import com.kgc.kmall.manage.mapper.PmsBaseCatalog3Mapper;
import com.kgc.kmall.service.CatalogService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Service
public class CatalogServiceImpl implements CatalogService {
    @Resource
    PmsBaseCatalog1Mapper pmsBaseCatalog1Mapper;
    @Resource
    PmsBaseCatalog2Mapper pmsBaseCatalog2Mapper;
    @Resource
    PmsBaseCatalog3Mapper pmsBaseCatalog3Mapper;

    @Override
    public List<PmsBaseCatalog1> getCatalog1() {
        return pmsBaseCatalog1Mapper.selectByExample(null);
    }

    @Override
    public List<PmsBaseCatalog2> getCatalog2(Integer catalog1Id) {
        PmsBaseCatalog2Example example = new PmsBaseCatalog2Example();
        if (catalog1Id != 0 && catalog1Id != null) {
            example.createCriteria().andCatalog1IdEqualTo(catalog1Id);
        }

        return pmsBaseCatalog2Mapper.selectByExample(example);
    }

    @Override
    public List<PmsBaseCatalog3> getCatalog3(Long catalog2Id) {
        PmsBaseCatalog3Example example = new PmsBaseCatalog3Example();
        if (catalog2Id != 0 && catalog2Id != null) {
            example.createCriteria().andCatalog2IdEqualTo(catalog2Id);
        }
        return pmsBaseCatalog3Mapper.selectByExample(example);
    }

}
