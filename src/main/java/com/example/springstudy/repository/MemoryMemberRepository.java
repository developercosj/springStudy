package com.example.springstudy.repository;


import com.example.springstudy.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberRepository implements MemberRepository {

    // 동시성 문제를 해결하기 위해서는 ConcurrentHashMap 사용 권장
    private static Map<Long, Member> store = new HashMap<>();
    // 동시성 문제 Autum long ?
    private static long sequence = 0L;


    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 널일때도 return 가능하도록 Optional로 감싸주기
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
