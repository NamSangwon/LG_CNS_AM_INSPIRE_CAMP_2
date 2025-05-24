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
    + `Filter` 구성 : 요청한 클라이언트의 IP 확인
      ```java
        @Slf4j
        // Filter 인터페이스를 implemets하여 필터 구성
        public class IPCheckFilter implements Filter {
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
  + **Controller에 들어오는 요청과 응답을 가로채는 기능**
  + 특징
    + Filter와 유사하지만 동작하는 시기가 다름 (DispatcherServlet으로 진입한 후 동작)
    + **request와 response 객체를 기본 제공하므로 AOP에 비해 웹과 관련된 기능을 쉽게 작성**
    + **Filter와 달리 *적용할 URL*과 *제외할 URL*을 따로 지정할 수 있음**
  + 주요 메소드
    + preHandler() : Controller의 메소드가 실행되기 전 (요청)
    + postHandler() : Controller의 메소드가 실행된 후 (응답)
    + afterCompletion() : View가 렌더링된 후
  + 사용 예시
    + `Interceptor` 구성 : 로그인 확인
      ```java
        @Component // Bean으로 등록
        @Slf4j
        // HandlerInterceptor 인터페이스를 implements하여 인터셉터 구성
        public class SignInCheckInterceptor implements HandlerInterceptor {
          // 컨트롤러 메소드 실행 전 (요청 시)
          // 세션을 통해 로그인 여부 확인 -> 비로그인 시, 로그인 페이지로 이동
          @Override
          public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
          ) throwsException {
            log.debug("preHandle");
            HttpSession session= request.getSession();
            User user= (User) session.getAttribute("user");
            if(user == null) {
              response.sendRedirect("/login");
              return false;
            }
            return true;
          }
          // Controller의 메소드가 실행된 후 (응답 시)
          // 로그 출력
          @Override
          public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
          ) throwsException {
            log.debug("postHandle");
          }
          // View가 렌더링된 후
          // 로그 출력
          @Override
          public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex
          ) throwsException {
            log.debug("afterCompletion");
          }
        }
      ```
    + `Configuration` : **인터셉터 등록 & 인터셉터의 동작 시점 지정 (메소드가 아닌 URL로 지정)**
      ```java
        @Configuration
        // WebMvcConfigurer 인터페이스를 상속하여 구현
        public class InterceptorConfig implements WebMvcConfigurer {
          @Autowired
          private SignInCheckInterceptor signInCheckInterceptor;
      
          @Override
          public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(signInCheckInterceptor) // 인터셉터 추가
                    .addPathPatterns("/main");  // 인터셉터 동작 시점 지정
                    // .excludePathPatterns("url"); 을 통해 특정 주소를 제외

            // 인터셉터 등록
            WebMvcConfigurer.super.addInterceptors(registry);
          }
        }
      ```
---
### AOP vs 서블릿 Filter vs 스프링 Filter vs Interceptor
| 구분                | AOP                           | Servlet Filter             | Spring Filter              | HandlerInterceptor                          |
|:-----------------:|:-----------------------------:|:--------------------------:|:--------------------------:|:-------------------------------------------:|
| 적용 대상           | 메서드 실행 전후 (Spring Bean) | 전체 HTTP 요청/응답       | 전체 HTTP 요청/응답       | 컨트롤러 진입 전후 (전체 HTTP 요청/응답)   |
| 실행 시점           | DispatcherServlet 이후, Bean 내부 | DispatcherServlet 이전     | DispatcherServlet 이전     | DispatcherServlet 이후, Controller 직전     |
| 주요 용도           | 트랜잭션, 로깅, 보안, 공통 비즈니스 로직 | 인코딩, 로그인 체크, 로깅 등 전처리 | 인증/인가 필터, CORS, JWT 필터 등 | 로그인 여부 확인, 권한 체크, 로깅 등        |

---
### 4. File Upload/Download
+ `Upload`
    + Backend 파일 수신
      ```java
        @PostMapping("/upload")
        @ResponseBody
        public String uploadPost(
            // 다중 파일 전송 시, MultipartFile 변수를 배열로 변경 후, 반복문 처리
            MultipartHttpServletRequest mRequest // 1번 방법
            @RequestParam("file") MultipartFile mFIle // 2번 방법
            @ModelAttribute FileInfo info // 3번 방법 (FileInfo 안에 MultipartFile 타입의 FileInfo 변수 존재 必)
        ) {
          // 단일 파일 처리
          String result= "";
          MultipartFile mFile = mRequest.getFile("file"); // (1번) input 태그 name과 매칭
          String oName= mFile.getOriginalFilename();
          result += oName+ "<br>"+ mFile.getSize();
          return result;

          // 다중 파일 처리 (input의 name이 정해지지 않은 경우)
          // name이 정해지지 않은 input 태그를 반복
          Iterator<String> fileNames = mRequest.getFileNames();
          while(fileNames.hasNext()) {
            // input 태그 내의 다중 파일 처리
            String fileName= fileNames.next();
            List<MultipartFile> mFiles= mRequest.getFiles(fileName);
            for(MultipartFile mFile:mFiles) {
              String oName= mFile.getOriginalFilename();
              long size= mFile.getSize();
              result+= oName+ " : "+ size+ "<br>";
            }
          }
          return result;
        }
      ```
    + Frontend 파일 송신
      ```html
        <form method="post" enctype="multipart/form-data">
          <!-- 다중 파일 전송 시, multiple 속성 추가 -->
          <input type="file" name="file"><br> <!-- input 태그 여러 개 전송 가능 -->
          <input type="submit" value="업로드">
        </form>
      ```
    
  + `Download`
    + 다운로드 되는 파일의 형식에 따라 브라우저가 반응할 수 있도록 **Response Header에 `Content-Type` 지정** 필요 (application/octet-stream)
    + `Content-Type` : HTTP 헤더에서 사용되며 클라이언트와 서버 간의 통신에서 데이터 유형 명시
    + Backend 파일 송신
      ```java
        @GetMapping("/download")
        public ResponseEntity<Resource> download() throws Exception {
          File file = new File("파일 경로");
      
          InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
      
          return ResponseEntity.ok()
                              .header("content-disposition",
                                "attachment; filename=\""+ URLEncoder.encode(file.getName(), "utf-8") +"\"").contentLength(file.length()
                              )
                              
                              .contentType(MediaType.APPLICATION_OCTET_STREAM)
                              .body(resource);
        }
      ```
  + **`UUID`**
    + 고유한 값을 만들기 위해 사용되는 128 bit 식별자
    + 충돌 없이 고유한 값을 보장해야 할 때 사용
    + 파일 중복 처리
      + UUID로 변경된 파일 명과 원본 파일명을 DB에 저장
      + 사용자가 파일을 다운로드 시, 변경된 파일명으로 파일을 찾고 원본 파일명으로 파일을 저장
---

### 5. JWT
  + JSON Web Token
  + [ 특징 ]
    + JSON 데이터를 Base64 인코딩을 통해 직렬화
    + 사용자를 인증하고 식별하기 위한 값
    + 서버에서 발급하고 클라이언트에 저장
    + 토큰에 사용자 정보를 포함하는 것이 가능
    + 데이터가 많아지면, 토큰의 길이가 길어질 수 있음
  + 형태
    ![image](https://github.com/user-attachments/assets/83b833cb-a7ae-4089-9fb1-27d8cc08b871)
    + `header` : 토큰 타입 및 암호화 알고리즘 정보
    + `payload`
      + `claim` : 토큰의 정보 중 한 조각 (key-value 쌍으로 구성)
      + `claim 상태`
        + `Registered Claim`
          + 토큰에 대한 정보를 담음
          + 정해진 key를 사용
          + ex) iss(발급자=서비스 도메인), aud(수신자=클라이언트 ID), sub(주제=사용자 고유 ID), exp(만료시간)
        + `Public Claim`
          + 다른 토큰과의 충돌 방지
          + URI 형식 사용
          + ex) { "http://ggoreb.com" : true }
        + `Private Claim`
          + 서버와 클라이언트 간의 데이터 전송을 위해 사용
          + ex) { "username" : "ggoreb", "level" : 1 }
    + `signature` : Header의 base64 인코딩 값과 Payload base64 인코딩 값을 합친 후, 서명키로 다시 해싱한 값
    + JWT의 서명 만으로 위조 방지는 가능하나 암호화는 아님
  + 사용 예시
    + 인코딩
      ```java
        void stringToBase64() {
          String text= "jwt test";
          String encodedString = Base64.getEncoder().encodeToString(text.getBytes());
        }
      ```
    + 디코딩
      ```java
        void base64ToString() {
          byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
          String decodedString = new String(decodedBytes);
        }
      ```
    + JWT 생성
      ```java
        voidcreateJwt() {
          SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
          byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqr");
          Keysigning Key= new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
          JwtBuilder builder= Jwts.builder()
                                    // Header
                                    .setHeaderParam("typ", "JWT")
                                    // Payload -Registered Claim
                                    .setSubject("제목").setIssuer("ggoreb.com").setAudience("service user")
                                    // Payload -Secret Claim
                                    .claim("username", "ggoreb").claim("password", 1234).claim("hasPermission", "ADMIN")
                                    // Signature
                                    .signWith(signingKey, signatureAlgorithm);
          long now= System.currentTimeMillis();
          builder.setExpiration(newDate(now+ 3600000)); // 1시간 뒤 토큰 유효기간 만료
          String token= builder.compact();
          log.info("jwt {}", token);
        }
      ```
    + JWT 확인
      ```java
        JwsHeader<?> header = jwtParser.parseClaimsJws(jwt).getHeader();
        String algorithm = header.getAlgorithm();
        String type= header.getType();
      
        Claims claims= jwtParser.parseClaimsJws(jwt).getBody();
        log.info("Subject {}", claims.getSubject());
        log.info("Issuer {}", claims.getIssuer());
        log.info("Audience {}", claims.getAudience());
        log.info("claim {}", claims.get("username"));
        log.info("claim {}", claims.get("password"));
        log.info("claim {}", claims.get("hasPermission"));
      ```
---

### 6. Spring Security
  + 웹 애플리케이션에 인증과 권한 기능을 쉽게 적용할 수 있도록 도와주는 보안 프레임워크
  + ex) 로그인, 로그아웃, 비밀번호 암호화, 권한별 페이지 제한, 자동 로그인, CSRF 공격 방어 등
  + `CORS` : WebMvcConfigurer를 통해 전역으로 설정하는 방법
    ```java
      @Configuration
      public class WebConfig {
        @Bean
        public WebMvcConfigurer corsConfigurer() {
          return new WebMvcConfigurer() {
            @SuppressWarnings("null")
            @Override
            public void addCorsMappings(CorsRegistry registry) {
              registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST")
                        .allowCredentials(true);
            }
          };
        }
      }
    ```
  + `CSRF`
    + Cross-Site Request Forgery (사이트 간 요청 위조)
    + 사용자의 의도와 무관하게 인증된 사용자의 권한으로 요청이 전송되게 만드는 공격
      + 공격자가 사용자의 인증 정보를 몰래 이용해 사용자가 원하지 않는 요청을 서버에 보내도록 유도하는 방식
      + ex) 사용자는 로그인된 상태인데 악성 사이트가 그 사용자의 권한으로 요청을 보냄
    + Spring Security는 기본적으로 CSRF 방어가 설정 (csrf 토큰 없이 요청 불가)
      + POST, PUT, DELETE 같은 변경 요청에는 CSRF 토큰 필요
        + HTML <form> 요청은 자동으로 토큰을 포함
        + fetch() 요청은 CSRF 토큰이 없으므로 403 발생
      + REST API + JWT 요청 방식에서는 일반적으로 CSRF를 사용하지 않기 때문에 비활성화
        + React(jwt)-> csrf 비활성화(실무)
        + csrf 토큰 전송 (복잡하지만, 확실한 보안) : GET 요청 후 POST 요청을 통해 토큰 전송
  + Spring Security Config 설정 (인증, CSRF, CORS, Login Form, Logout 전처리 등)
    ```java
      @Configuration
      public class SecurityConfig {
      
          /*
           * /admin 으로의 요청 시, 로그인 수행
           * 로그인 성공하면, /hello로 이동
           * (아래의 코드에서 UserDetailsService 인터페이스를 구현한
           * MemberDetailsService의 loadUserByUsername() 메소드를 사용하여 User 객체 정보 가져 옴)
           */
          @Bean
          public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtUtil jwtUtil,
                  UserDetailsService userDetailsService) throws Exception {
              http
                      // 요청 권한 처리 (인증, 역할에 따라 접속 허용 설정)
                      .authorizeHttpRequests(auth -> auth
                              .requestMatchers("/admin").authenticated() // 인증 여부
                              .requestMatchers("/my-page").authenticated() // 인증 여부
                              .requestMatchers("/admin-page").hasRole("ADMIN") // 권한
                              .requestMatchers("/user-page").hasRole("USER") // 권한
                              .requestMatchers("/jwt/login").permitAll() // 모두 허용
                              .requestMatchers("/jwt/protected").authenticated() 
                              .anyRequest().permitAll())
                      // 커스텀 필터 등록
                      // 스프링 시큐리티의 로그인 인증(UsernamePasswordAuthenticationFilter) 전에, JwtAuthFilter을 통해 JWT 로그인 검증
                      // 매 요청마다 먼저 JWT 토큰을 검사하고 인증을 시도
                      .addFilterBefore(
                              new JwtAuthFilter(jwtUtil, userDetailsService),
                              UsernamePasswordAuthenticationFilter.class)
                      // CSRF 설정
                      // REST API + JWT 방식에서는 일반적으로 CSRF를 사용하지 않기 때문에 비활성화
                      .csrf(csrf -> csrf
                              // CSRF 공격이 들어오더라도 통과 (CSRF 토큰 검사 X)
                              // 1. React(jwt)-> csrf 비활성화(실무)
                              // 2. csrf 토큰 전송 (복잡하지만, 확실한 보안)
                              .disable())
                      // Cross Origin 설정
                      .cors(cors -> cors
                              .configurationSource(request -> {
                                  CorsConfiguration config = new CorsConfiguration();
                                  config.setAllowedOrigins(List.of("http://localhost:5500", "http://127.0.0.1:5500"));
                                  config.setAllowedMethods(List.of("GET", "POST"));
                                  config.setAllowCredentials(true);
                                  config.setAllowedHeaders(List.of("*"));
                                  return config;
                              }))
                      // 로그인 처리
                      .formLogin(form -> form
                              .loginPage("/login") // 커스터마이징한 로그인 페이지 지정
                              .defaultSuccessUrl("/hello", true)
                              .permitAll())
                      // 로그아웃 처리
                      .logout(logout -> logout
                              .logoutUrl("/logout") // default: /logout (POST)
                              .logoutSuccessUrl("/login?logout=true")
                              .invalidateHttpSession(true) // 세션 제거
                              .deleteCookies("JSESSIONID") // 쿠키 제거
                      )
                      // 세션 유지 (자동 로그인 기능)
                      .rememberMe(r -> r
                        .key("remember-me-key") // 서버 비밀키
                        .rememberMeParameter("remember-me") // 체크박스 태그의 name과 매칭
                        .tokenValiditySeconds(60 * 60 * 24 * 7) // 유효 기간 : 7일
                      );
      
              return http.build();
          }
      
          // Spring Security에서 인증(Authentication) 프로세스를 처리하는 핵심 인터페이스
          // 해당 Bean을 DI 시킨 후, authenticate() 메소드를 통해 검증 처리 가능
          @Bean
          public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
              return http.getSharedObject(AuthenticationManagerBuilder.class).build();
          }
      
          // password는 BCrypt를 사용하여 암호화
          @Bean
          public PasswordEncoder passwordEncoder() {
              return new BCryptPasswordEncoder();
          }
      }
    ```
  + JWT 토큰 기반 로그인 인증 필터
    + Spring Security 인증 필터 2가지
      + `UsernamePasswordAuthenticationFilter`
        + 폼 로그인 인증을 처리하는 필터
        + 일반 로그인 인증 : /login POST 요청 등
      + `BasicAuthenticationFilter`
        + Basic 인증 처리
        + Header의 Authentication 인증 : 헤더에 `Authorization: Basic xxx` 있을 때 처리
      + 실행 순서 : `UsernamePasswordAuthenticationFilter` &rarr; `BasicAuthenticationFilter`
    + Spring Security 인증 필터 `UsernamePasswordAuthenticationFilter` 전에 수행하도록 함
    + 사용 예시
      ```java
        public class JwtAuthFilter extends OncePerRequestFilter {
        JwtUtil jwtUtil;
        public JwtAuthFilter(JwtUtil jwtUtil) {
          this.jwtUtil = jwtUtil;
        }
        @Override
        protected void doFilterInternal(
          HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
        ) throws ServletException, IOException {
          // Token이 담기는 위치 = headers : {Authorization : Bearer xxx}
          String authHeader = request.getHeader("Authorization");
          if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (jwtUtil.validate(token)) {
              String username = jwtUtil.extractUsername(token);
              UserDetails userDetails = userDetailsService.loadUserByUsername(username);

              // UserDetails 기반 UsernamePasswordAuthenticationToken 객체 생성
              UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, List.of());

              // authentication 객체가 Spring Security의 SecurityContext에 저장
              // SecurityContext는 현재 요청에 대한 보안 관련 정보를 담음
              // 요청 생명주기 동안 접근 가능
              // SecurityContext에 Authentication 객체가 설정되면, 컨트롤러에서 사용자 정보를 주입 가능
              SecurityContextHolder.getContext().setAuthentication(authentication);
            }
          }
          filterChain.doFilter(request, response);
        }
      }
      ```
---
