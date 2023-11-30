package com.example.springstudy.repository;

import com.example.springstudy.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

// 인터페이스끼리는 extends
// 인터페이스는 다중 상속이 됨
// JpaRepository를 상속하면 스프링이 구현체를 직접 만들어서 스프링빈에 등록해줌

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
