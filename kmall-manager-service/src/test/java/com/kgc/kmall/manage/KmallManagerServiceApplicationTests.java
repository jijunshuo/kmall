package com.kgc.kmall.manage;

import com.kgc.kmall.bean.PmsBaseCatalog1;
import com.kgc.kmall.service.CatalogService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class KmallManagerServiceApplicationTests {
    @Reference
    CatalogService catalogService;

    @Test
    void contextLoads() {
        List<PmsBaseCatalog1> catalog1 = catalogService.getCatalog1();
        for (PmsBaseCatalog1 pmsBaseCatalog1 : catalog1) {
            System.out.println(pmsBaseCatalog1);
        }
    }

}
