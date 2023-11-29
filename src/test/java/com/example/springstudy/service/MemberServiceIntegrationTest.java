package com.example.springstudy.service;

import com.example.springstudy.domain.Member;
import com.example.springstudy.repository.MemberRepository;
import com.example.springstudy.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
//@Transactional
class MemberServiceIntegrationTest {

    // Test 시는 생성자 Injection 보다는 필드 Injection을 추천 한다. (변경사항 많지 않음)
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository ;


    @Test
    void 회원가입() {
        // Given
        Member member = new Member();
        member.setName("springJoin");
        // When
        Long saveId = memberService.join(member);

        // Then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // Given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        // When
        memberService.join(member1);
        assertThrows(RuntimeException.class, () -> memberService.join(member2));
        // Then


    }




}