package com.example.springstudy;

import com.example.springstudy.repository.MemberRepository;
import com.example.springstudy.repository.MemoryMemberRepository;
import com.example.springstudy.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {


    // 스프링이 올라올때 memberService와 memberRepository를 스프링 빈에 등록해준다.
    // Controller는 @Bean으로 직접 등록하는 대신에 스프링이 제공하는 컴포넌트 스캔이라는 기능을 통해 자동으로 스프링 빈으로 등록
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
