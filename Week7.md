# 7주차 학습 내용 
#### &rarr; `웹 요청/응답 흐름`, `파일 업로드/다운로드`, `보안`
---
### 0. 스프링부트 웹 요청/응답 흐름
![image](https://t1.daumcdn.net/cfile/tistory/9983FB455BB4E5D30C)

### 1. AOP
  + Aspect Oriented Programming
  + **공통적으로 적용될 모듈을 만든 후 적용하고자 하는 부분의 코드 밖에서 삽입하는 방법**
    ![image](https://github.com/user-attachments/assets/465e6674-37d8-4c51-85ce-5066490b9de8)
  + 사용 예시
    + 메소드의 성능 테스트 (시간 측정)
    + 트랜잭션 처리
    + 예외 반환 (ControllerAdvice)
    + 로깅, 인증, 권한 처리 등
  + AOP 적용 후 Controller-Service-Repository 의존 관계
    ![image](https://github.com/user-attachments/assets/7cb40231-b6e5-49ee-8ead-55acd6e36bdb)
  + 용어
    + `Advice` : 부가 기능을 담은 모듈 (메소드)
      + `execution()` : 적용할 메소드를 세부적으로 명시
      + 사용 방법 : execution(`리턴 타입` `메소드명` `파라미터`)
      + ex. `execution(* com.spring.aop.*.*(..))`
        + `리턴 타입` : ALL, `메소드명` : com.spring.aop 패키지 내의 모든 메소드, `파라미터` : 0개 이상
    + `Pointcut` : Joinpoint 중 실제 Advice가 적용되는 지점
      + `before` : Pointcut으로 지정된 *메소드 호출 전*에 동작
      + `after` : Pointcut으로 지정된 *메소드 호출 후*에 동작
      + `around` : `before`와 `after` 기능을 한 번에 적용
    + `Aspect` : `Advice` + `Pointcut`
      + 사용 방법
        ```java
          @Slf4j
          @Aspect // AOP 사용 명시
          @Component // Bean으로 등록
          public class ControllerAspect{
            // (@Around = Pointcut & execution() = Advice) = Aspect
            @Around("execution(* com.example.basic.controller.*.*(..))")
            public Object onAroundHandler(ProceedingJoinPoint joinPoint) throws Throwable{
              // Before
              log.debug("@Before run");
        
              Object result=null;
              try{
                result = joinPoint.proceed(); // 실제 메서드 실행
        
                // AfterReturning
                if(result!=null) { 
                  log.debug(result.toString());
                }
                log.debug("@AfterReturning run");
        
                return result;
              } finally{
                // After
                log.debug("@After run");
              }
            }
          }
        ```
  + `ControllerAdivce`
    + `AOP`로 구현한 Controller에서 발생되는 오류를 감지하고 처리해주는 기능
    + 사용 이유
      + 예외처리를 한 곳에 묶어서 편하게 관리
      + 처리가 제대로 되지 못한 부분에 예외가 발생되는 경우 브라우저에 Exception Message가 노출되어 버리는데 모든 예측하지 못한 예외도 한꺼번에 처리 가능
    + 기본 구조
      ```java
        @ControllerAdvice
        public class MyControllerAdvice{
          @ExceptionHandler
          [@ResponseStatus]
          [@ResponseBody]
          public String handle(RuntimeException e, WebRequest request) {
            return[view];
          }
        }
      ```
      + `@ControllerAdvice` 옵션 : 특정 패키지 및 클래스만 지정 가능
        + 기본 값 : 프로젝트 기본 패키지 내의 모든 컨트롤러
      + `@ExceptionHandler` 옵션 : 특정 예외에 대해서만 동작
        + 기본 값 : 모든 예외
      + `@ResponseStatus` 옵션 : 응답 코드 지정
        + 기본 값 : HttpStatus.INTERNAL_SERVER_ERROR (500)
      + 반환 값 : 컨트롤러와 동일하게 사용 (ex. HTML 파일, JSON 값 등)
    + 사용 예시
      ```java
        // ExceptionController 내의 모든 컨트롤러에 대해서
        // 모든 Exception에 대해 에러 발생 시,
        // INTERNAL_SERVER_ERROR (응답 코드 500)로
        // @ResponseBody를 통한 "<h1>{에러 메시지}</h1>" 전송 
        @Slf4j
        @ControllerAdvice(assignableTypes= { ExceptionController.class })
        public class MyControllerAdvice{
          @ExceptionHandler
          @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
          @ResponseBody
          public String handle(Exception e, WebRequest request) {
            String message= e.getMessage();
            log.debug(message);
            return"<h1>"+ message+ "</h1>";
          }
        }
      ```
    + Custom Exception
      ```java
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public class CustomException extends RuntimeException {
          private String message;
        }
      ```
---

### 2. Filter
  ![image](https://github.com/user-attachments/assets/ac9614e8-d146-4820-9cbd-0bddd2ffa983)
  + **HTTP 요청과 응답을 변경할 수 있는 기능**
  + 특징
    + 서블릿 기반의 기술로 HTTP 요청과 응답을 가로챔
    + 스프링의 DispatcherServlet 보다 먼저 실행
    + AOP 보다 request와 response 객체에 쉽게 접근할 수 있음
    + FilterRegistrationBean을 통해 특정 URL에만 적용하거나 실행 순서를 제어할 수 있음
    + **필터를 동작 시점 지정 시, 메소드가 아닌 URL로 지정** 

  + 사용 예시
    + XSS (Cross Site Scripting) 방지
    + Logging
    + Encoding
    + IP 검사 등
      
  + 사용 예시
    + `Filter` : 요청한 클라이언트의 IP 확인
      ```java
        @Slf4j
        public class IPCheckFilter implements Filter{
          @Override
          public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
          ) throwsIOException, ServletException {
            // 실행 전
            log.debug("filter begin");
            HttpServletRequestreq= (HttpServletRequest) request;
            Stringip= request.getRemoteAddr();
            log.debug("ip : "+ ip);

            // 실행
            chain.doFilter(req, response);

            // 실행 후
            log.debug("filter end");
          }
        }
      ```
    + `Configuration` : **필터 등록 & 필터의 동작 시점 지정 (메소드가 아닌 URL로 지정)**
      ```java
        @Configuration
        public class FilterConfig {
          @Bean
          public FilterRegistrationBean<Filter> getFilterRegistrationBean() {
            // 필터 등록
            FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>(newIPCheckFilter());

            // 등록한 필터의 동작 시점 지정
            bean.addUrlPatterns("/visitor");
      
            return bean;
          }
        }
      ```
---

### 3. Interceptor
  + ㅁㄴㅇ
  + ㅁ
---

### 4. File Upload/Download
  + ㅁㄴㅇ
  + ㅁ
---

### 5. UUID
  + ㅁㄴㅇ
  + ㅁ
---

### 6. JWT
  + ㅁㄴㅇ
  + ㅁ
---

### 7. Spring Security
  + ㅁㄴㅇ
  + ㅁ
---
