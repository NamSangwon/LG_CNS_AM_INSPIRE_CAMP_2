package com.example.book.aspect;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class RepositoryAspect {

  // 모든 Repository 클래스의 메소드가 실행될 때 메소드명과 파라미터 출력
  @Before("execution(* com.example.book.repository.*.*(..))")
  public void before(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().toString();
    String parameter = Arrays.toString(joinPoint.getArgs());
    log.info("{} - {}", methodName, parameter);
  }

  // 모든 Repository 클래스의 메소드가 종료될 때 출력
  @After("execution(* com.example.book.repository.*.*(..))")
  public void after(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().toString();
    log.info("{} 종료", methodName);
  }

  // 모든 Repository 클래스의 메소드가 정상적으로 반환된 경우 반환값 출력
  @AfterReturning(value = "execution(* com.example.book.repository.*.*(..))", returning = "result")
  public void afterReturning(JoinPoint joinPoint, Object result) {
    String methodName = joinPoint.getSignature().toString();
    log.info("{} / 반환값: {}", methodName, result);
  }

  // 모든 Repository 클래스의 메소드가 종료될 때 종료 로그 출력
  @Around("execution(* com.example.book.repository.*.*(..))")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.currentTimeMillis();
    Object proceed = joinPoint.proceed();
    long end = System.currentTimeMillis();
    String methodName = joinPoint.getSignature().toString();
    log.info("{} / {}ms", methodName, end - start);
    return proceed;
  }
}