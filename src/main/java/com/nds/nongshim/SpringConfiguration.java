package com.nds.nongshim;

import com.nds.nongshim.repository.JpaMemberRepository;
import com.nds.nongshim.repository.MemberRepository;
import com.nds.nongshim.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    /*private final DataSource dataSource;
    private final EntityManager em;

    public SpringConfiguration(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }*/

    private final MemberRepository memberRepository;

    public SpringConfiguration(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

  /*  @Bean
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return new JDBCMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }*/
}