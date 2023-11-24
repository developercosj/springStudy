package com.example.springstudy.service;

import com.example.springstudy.domain.Member;
import com.example.springstudy.repository.MemberRepository;
import com.example.springstudy.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// service 클래스 작성 시 비즈니스에 가까운 용어를 사용
public class MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // Dependency Injection
    // 구현체(MemoryMemberRepository) 주입
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     *
     */
    public Long join(Member member) {

        // ifPresent는 result 가 값이 null 이 아니라 값이 있으면 ~~
        // Optional로 받으면 Optional 안의 여러 method 를 사용할 수 있다.
        // result.orElseGet(); 도 많이 씀
        // 아래와 같이 옵셔널을 바로 반환하는 것 권장하진 않음
            // Optional<Member> result = memberRepository.findByName(member.getName());
            // result.ifPresent(m -> {
            //    throw new IllegalStateException("이미 존재하는 회원입니다.");
            // });
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member)  {

        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    try {
                        throw new IllegalAccessException("이미 존재하는 회원입니다.1");
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("이미 존재하는 회원입니다.2");
                    }
                });

    }


    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
