package com.example.springstudy.controller;

import com.example.springstudy.domain.Member;
import com.example.springstudy.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    //private final MemberService memberService = new MemberService();

    // DI 1) 필드주입
    // 중간에 수정이 어려움
    // @Autowired private MemberService memberService;

    // DI 2) 생성자주입
    // @Autowired를 사용하여 스프링 컨테이너에 있는 객체를 가져옴 (DI)
    // 의존관계가 실행 중에 동적으로 변하는 경우는 거의 없기 때문에 생성자 주입을 권장
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // DI 3) Setter 주입 (Setter Injection)
    // public 으로 노출되어 있어야하고 어디서나 변경이 가능한 단점
    //     private MemberService memberService;
    //     @Autowired
    //     public void setMemberService(MemberService memberService) {
    //        this.memberService = memberService;
    //     }










}
