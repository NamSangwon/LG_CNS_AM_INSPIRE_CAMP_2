package com.example.book.aspect;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

  // 1) 모든 컨트롤러 클래스의 메소드가 실행될 때 메소드명과 파라미터를 출력
  public void logBeforeController(JoinPoint joinPoint) {

  }

  // 2) 모든 서비스 클래스 내의 메소드 실행시간을 측정해서 출력
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

    return null;
  }

  // 3) 모든 서비스 클래스 내의 메소드가 정상적으로 반환된 경우 반환값 출력
  public void logReturnValue(JoinPoint joinPoint, Object result) {

  }

  // 4) 모든 컨트롤러 클래스의 메소드가 종료될 때 메소드명 출력
  public void logAfterController(JoinPoint joinPoint) {

  }

  // 5) 서비스 클래스의 메소드 중 create로 시작하는 모든 메소드가 실행될 때 메소드명과 "데이터 생성 시작" 로그 출력
  public void logCreateStart(JoinPoint joinPoint) {

  }
}