package com.kgc.kmall.manager.controller;

import com.kgc.kmall.bean.PmsBaseCatalog1;
import com.kgc.kmall.bean.PmsBaseCatalog2;
import com.kgc.kmall.bean.PmsBaseCatalog3;
import com.kgc.kmall.service.CatalogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Api(tags = "商品分类接口", description = "商品分类相关的 Rest API")
public class ManageController {
    @Reference
    CatalogService catalogService;

    @ApiOperation("查询一级分类接口")

    @PostMapping("/getCatalog1")
    public List<PmsBaseCatalog1> getCatalog1() {
        return catalogService.getCatalog1();
    }

    @ApiOperation("根据一级分类编号查询二级分类")
    @PostMapping("/getCatalog2")
    @ApiImplicitParam(name = "catalog1Id", value = "一级分类编号", required = false)
    public List<PmsBaseCatalog2> getCatalog2(Integer catalog1Id) {
        return catalogService.getCatalog2(catalog1Id);
    }

    @ApiOperation("根据二级分类编号查询三级分类")
    @ApiImplicitParam(name = "catalog2Id", value = "二级分类编号", required = false)
    @PostMapping("/getCatalog3")
    public List<PmsBaseCatalog3> getCatalog3(Long catalog2Id) {
        return catalogService.getCatalog3(catalog2Id);
    }

}
