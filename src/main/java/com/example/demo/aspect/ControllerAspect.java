package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class ControllerAspect {
    @Around("execution(* com.example.demo.controller.*.*(..))")
    public Object onAroundHandler(ProceedingJoinPoint joinPoint)
            throws Throwable {
        log.debug("@Before run");// Before
        Object result = null;
        try {
            result = joinPoint.proceed(); // 실제 메서드 실행
            if (result != null) { // AfterReturning
                log.debug(result.toString());
            }
            log.debug("@AfterReturning run");
            return result;
        } finally {
            log.debug("@After run");// After
        }
    }
}
