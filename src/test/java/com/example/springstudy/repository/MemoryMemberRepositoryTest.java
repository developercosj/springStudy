package com.example.springstudy.repository;

import com.example.springstudy.domain.Member;
import com.example.springstudy.repository.MemoryMemberRepository;
// static import 사용
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {

    //**중요 : 테스트는 순서없이 의존관계 없이 생성해야 한다.
    // 그렇게 하기 위해서는 테스트메서드가 끝날때마다 저장소나 공용데이터들을 비워줘야 한다.
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 메서드가 끝날때마다 동작하는 콜백메서드 역할
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        //Assertions.assertEquals(member, result);
        //Assertions.assertThat(member).isEqualTo(result);
        // static import 사용해서 간편하게 사용
        assertThat(member).isEqualTo(result);

    }



    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);


        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }


    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }

}
