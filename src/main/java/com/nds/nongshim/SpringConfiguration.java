package com.nds.nongshim;

import com.nds.nongshim.repository.MemberRepository;
import com.nds.nongshim.repository.MemoryMemberRepository;
import com.nds.nongshim.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
