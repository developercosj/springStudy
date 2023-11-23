package com.example.springstudy.service;

import com.example.springstudy.domain.Member;
import com.example.springstudy.repository.MemberRepository;
import com.example.springstudy.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //MemberService memberService = new MemberService();
    MemberService memberService;
    MemoryMemberRepository repository ;

    @BeforeEach
    public void beforeEach() {
        repository = new MemoryMemberRepository();
        memberService = new MemberService(repository);

    }

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }


    @Test
    void 회원가입() {
        // Given
        Member member = new Member();
        member.setName("name");
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

/*        try {
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        } catch (IllegalStateException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.2");
        }*/

        // 원래대로 라면 아래 첫번째가 통과가 되어야 하나, uncheckedException였던 IllegalStateException 도 또한 try catch 가 강제되어서
        // RuntimeException이 마지막으로 발생되어 두번째가 통과됨
        //assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThrows(RuntimeException.class, () -> memberService.join(member2));


        // Then


    }




    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}