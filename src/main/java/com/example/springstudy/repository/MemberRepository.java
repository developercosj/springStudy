package com.example.springstudy.repository;

import com.example.springstudy.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    // Null 처리방식에 Optional로 감싸서 반환하는 것을 선호
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();


}
