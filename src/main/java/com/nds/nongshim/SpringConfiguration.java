package com.nds.nongshim;

import com.nds.nongshim.repository.JDBCMemberRepository;
import com.nds.nongshim.repository.MemberRepository;
import com.nds.nongshim.repository.MemoryMemberRepository;
import com.nds.nongshim.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfiguration {

    private final DataSource dataSource;

    public SpringConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JDBCMemberRepository(dataSource);
    }
}
