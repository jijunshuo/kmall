package com.kgc.kmall.manage.service;

import com.kgc.kmall.bean.PmsBaseAttrInfo;
import com.kgc.kmall.bean.PmsBaseAttrInfoExample;
import com.kgc.kmall.bean.PmsBaseAttrValue;
import com.kgc.kmall.bean.PmsBaseAttrValueExample;
import com.kgc.kmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.kgc.kmall.manage.mapper.PmsBaseAttrValueMapper;
import com.kgc.kmall.service.AttrService;
import net.bytebuddy.asm.Advice;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Service
public class AttrServiceImpl implements AttrService {
    @Resource
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;
    @Resource
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;

    @Override
    public List<PmsBaseAttrInfo> select(Long catalog3Id) {
        PmsBaseAttrInfoExample example = new PmsBaseAttrInfoExample();
        if (catalog3Id != 0 && catalog3Id != null) {
            example.createCriteria().andCatalog3IdEqualTo(catalog3Id);
        }
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = pmsBaseAttrInfoMapper.selectByExample(example);
        for (PmsBaseAttrInfo pmsBaseAttrInfo : pmsBaseAttrInfos) {
            PmsBaseAttrValueExample example1 = new PmsBaseAttrValueExample();
            PmsBaseAttrValueExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andAttrIdEqualTo(pmsBaseAttrInfo.getId());
            List<PmsBaseAttrValue> pmsBaseAttrValues = pmsBaseAttrValueMapper.selectByExample(example1);
            pmsBaseAttrInfo.setAttrValueList(pmsBaseAttrValues);
        }
        return pmsBaseAttrInfos;

    }

    @Override
    public Integer add(PmsBaseAttrInfo attrInfo) {
        try {
            if (attrInfo.getId() == null) {
                //添加
                pmsBaseAttrInfoMapper.insert(attrInfo);
            } else {
                //修改  并删除
                pmsBaseAttrInfoMapper.updateByPrimaryKeySelective(attrInfo);
                PmsBaseAttrValueExample example = new PmsBaseAttrValueExample();
                example.createCriteria().andAttrIdEqualTo(attrInfo.getId());
                pmsBaseAttrValueMapper.deleteByExample(example);
            }

            pmsBaseAttrValueMapper.insertBatch(attrInfo.getId(), attrInfo.getAttrValueList());
            return 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }

    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueList(Long attrId) {
        PmsBaseAttrValueExample example = new PmsBaseAttrValueExample();
        example.createCriteria().andAttrIdEqualTo(attrId);
        return pmsBaseAttrValueMapper.selectByExample(example);
    }

}
