package com.kgc.kmall.user.service;

import com.kgc.kmall.bean.Member;
import com.kgc.kmall.service.MemberService;
import com.kgc.kmall.user.mapper.MemberMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class MemberServiceImpl implements MemberService {
    @Resource
    MemberMapper memberMapper;
    @Override
    public List<Member> selectAll() {
        List<Member> members = memberMapper.selectByExample(null);
        return members;
    }
}
