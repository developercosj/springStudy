package com.example.springstudy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;

// @Aspect 붙여야함
@Aspect
@Component
public class TimeTraceAop {

    @Around("execution(* com.example.springstudy..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            // joinPoint.proceed(); : 다음메서드로 진행
            return joinPoint.proceed();
        }  finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");

        }

    }
}
