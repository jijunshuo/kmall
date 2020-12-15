package com.kgc.kmall.user;

import com.kgc.kmall.bean.Member;
import com.kgc.kmall.service.MemberService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class KmallUserServiceApplicationTests {
    @Reference
    MemberService memberService;

    @Test
    void contextLoads() {
        List<Member> members = memberService.selectAll();
        for (Member member : members) {
            System.out.println(member);
        }
    }

}
