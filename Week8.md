# 8주차 학습 내용

### 1. Spring Security
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
    + **사용자의 의도와 무관하게 인증된 사용자의 권한으로 요청이 전송되게 만드는 공격**
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
  + **JWT 토큰 기반 로그인 인증 필터**
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

              // ★ authentication 객체가 Spring Security의 SecurityContext에 저장
              // SecurityContext는 현재 요청에 대한 보안 관련 정보를 담음
              // 요청 생명주기 동안 접근 가능
              // SecurityContext에 Authentication 객체가 설정되어야만, 컨트롤러에서 사용자 정보를 주입 가능
              SecurityContextHolder.getContext().setAuthentication(authentication);
            }
          }
          filterChain.doFilter(request, response);
        }
      }
      ```
    + **클라이언트의 JWT 저장 위치에 따른 전송 방법**
      1. 쿠키
         + 서버로 자동 전송 &rarr; CSRF 공격 대비 필요 O
         + `credential : include header`를 통해 백엔드에서 발행한 토큰을 쿠키에 저장 가능
      2. 세션/로컬 스토리지
         + 서버로 자동 전송 X &rarr; CSRF 공격 대비 필요 X
         + headers: {Authorization : Bearer (token)`을 패킷에 작성
      
  + Spring Security 인증 정보 확인 방법
    1. 컨트롤러 메소드 파라미터에 `Principal` 주입
    2. 컨트롤러 메소드 파라미터에 `Authentication` 주입
       + 스프링이 자동으로 현재 인증 객체를 주입
       + Authentication은 권한, Principal, Details 등 다양한 정보를 포함
    3. 컨트롤러 메소드 파라미터에 `@AuthenticationPrincipal [커스텀 유저 객체]` 주입
       + 자동으로 커스텀 유저 객체를 직접 주입받을 수 있음
    4. `SecurityContextHolder.getContext().getAuthentication()` 메소드 호출
       + 전통적 방식
       + 어디서든 static하게 접근 가능 (ex. 서비스, 필터, 유틸 등)
       + 보안 정보를 명시적으로 꺼낼 때 유용
    + 사용 예시
      ```java
        @GetMapping("/protected")
        public String protectedArea(
          Principal principal,
          Authentication authentication,
          @AuthenticationPrincipal UserDetails userDetails
        ) {
          Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      
          String s1 = principal.getName();
          String s2 = authentication.getName();
          String s3 = userDetails.getUsername();
          String s4 = auth.getName();
      
          return "JWT 인증된 사용자만 접근 가능한 영역입니다.\n" + s1 + s2 + s3 + s4;
        }
      ```
---
### 2. REST API
  + 웹 서비스를 설계하는 데 사용되는 아키텍처 스타일 중 하나
  + 웹에서 데이터를 주고받는 데 사용되는 규칙의 집합
  + [정의]
    + `REST API` : `REST` 원칙을 따르는 `API`
    + `REST` : 웹 자원을 URL로 표현하고 HTTP METHOD를 사용해서 요청
    + `API` : 소프트웨어 애플리케이션이 서로 통신하고 데이터를 주고받도록 하는 프로토콜
  + [핵심 개념]
    + **자원**
      + 이미지, 텍스트, 사용자 정보, 게시물 등 웹 서비스에서 제공되는 모든 종류의 데이터나 기능을 의미
    + **행위**
      + 자원에 대한 행위를 HTTP Method를 통해 표현
      + 메소드들을 자원에 대한 CRUD 작업에 매핑하여 사용
        + `GET` : 자원 조회 (Read)
        + `POST` : 자원 생성 (Create)
        + `PUT` : 자원 업데이트 or 생성 (Update/Create)
        + `DELETE` : 자원 삭제 (Delete)
        + `PATCH` : 자원 일부 업데이트 (Update)
    + **표현**
      + 클라이언트가 자원에 대한 특정 행위를 요청하면, 서버는 그 자원의 `상태`를 `표현`하여 응답
      + 표현은 **`JSON`** or `XML`과 같은 형태로 데이터를 구조화하여 전송
    + ex) GET /articles/1 <=> { articlesInfo : {...} }
      + 의미 : 1번 기사 조회 요청 후, JSON 형태 데이터 응답
      + 자원 : articles, 1
      + 행위 : GET
      + 표현 : JSON
  + **[RESTful] : REST 원칙을 잘지켜서 만든 API**
    1. URL로 자원을 표현 (ex. GET /users/1 → ID가 1인 사용자 정보 요청)
    2. 쿼리 스트링 : `?`를 통해 검색이나 필터링에 사용
       + ex. GET /products?category=shoes&sort=price
       + 신발 카테고리에서 가격순 정렬된 상품 목록 요청
    3. 자원은 명사로 표현 (ex. /products/123 (O) & /getUser (X))
    4. HTTP 메소드를 목적에 맞게 사용 (ex. GET = 조회)
    5. 계츨 구조 활용하여 URL 표현 (ex. /users/1/posts/comments)
    6. 상태를 URL에 포함 X (쿼리 파라미터나 본문에 포함)
    7. JSON 형식을 기본으로 사용
    8. 일관된 응답 구조 유지 (협업 용이)
    9. 정확한 HTTP 상태 코드 사용
        + 200 : 성공
        + 201 : 생성됨
        + 400 : 잘못된 요청
        + 401 : 인증 실패
        + 403 : 권한 없음
        + 404 : 자원 없음
        + 500 : 서버 오류
  + [REST API의 장점]
    + 단순성 및 범용성: HTTP 프로토콜 표준을 따르므로 별도의 인프라 구축 없이 웹의 기존 기술을 활용 가능
    + 기술 독립성: 특정 언어나 기술에 종속 X. 어떤 프로그래밍 언어로든 클라이언트와 서버를 구현 가능
    + 확장성: 무상태성 덕분에 서버 부담 ↓. 여러 대의 서버로 쉽게 확장 가능.
    + 쉬운 이해: HTTP 메서드를 통해 자원에 대한 의도를 명확하게 표현. API의 의도를 쉽게 파악 가능.
    + 캐싱 활용: HTTP 캐싱 메커니즘을 활용하여 성능을 향상 가능.
  + API 명세서
    + 프론트엔드와 백엔드가 서로 정확하게 통신하기 위해 API의 동작 방식을 문서화한 것
    + 장점
      + 원활한 협업 : 프론트엔드와 백엔드가 서로 헷갈리지 않고 기능 개발 가능
      + 오류 감소 : 잘못된 요청/응답으로 인한 에러 예방
      + 유지보수 : 새로운 팀원이 쉽게 이해 가능
      + 테스트 자동화 : Swagger와 같은 도구로 테스트
    + 명세서 내용
      + 요청 URL (ex. /api/users/1)
      + 요청 메소드 (ex. GET, POST, PUT, DELETE)
      + 요청 파라미터 (ex. 경로 변수, 쿼리스트링, 바디 등)
---
### 3. Web Socket
  + HTTP 통신의 한계를 극복하기 위한 통신 방법
    + 지속적인 양방형 통신
      + 최초 연결(핸드셰이크)은 HTTP를 통해 이루어짐
      + 이후, 웹소켓 프로토콜로 전환
      + 클라이언트와 서버 간에 항상 연결된 상태를 유지하며 양항향으로 자유롭게 데이터를 송수신
    + 낮은 오버헤드 : HTTP 요청/응답 헤더와 같은 불필요한 오버헤드 감소. 통신 효율 증가.
    + 실시간 서비스에 최적화  : 채팅, 게임, 스트리밍 등 실시간 데이터 전송이 필요한 애플리케이션에 매우 적합
  + 소켓 통신 속성 설정
    ```java
      // WebSocketConfigurer 인터페이스를 상속하여 속성 설정
      @Configuration
      @EnableWebSocket
      public class WebSocketConfig implements WebSocketConfigurer {

        // 웹 소켓 핸들러 DI
        @Autowired
        private SimpleTextWebSocketHandler handler;
    
        @Override
        public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
          registry.addHandler(handler, "/ws").setAllowedOrigins("웹소켓 연결 주소");
        }
      }
    ```
  + 웹 소켓 통신 핸들러
    ```java
      @Component
      public class SimpleTextWebSocketHandler extends TextWebSocketHandler {
        // 연결된 세션들을 저장할 집합 (중복 방지)
        private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());
        // 사용자 첫 연결 후 동작
        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
          sessions.add(session); // 새로 접속한 사용자 저장
          System.out.println("사용자 접속: " + session.getId());
        }
        // 사용자로부터 메시지가 수신되었을 때 동작
        @Override
        public void handleTextMessage(WebSocketSession session, TextMessage message) throws
        Exception {
          String msg = message.getPayload();
          System.out.println("수신 메시지: " + msg);

          // 모든 세션들로 제시지 전송 (브로드캐스트)
          for (WebSocketSession s : sessions) {
            if (s.isOpen()) {
              s.sendMessage(new TextMessage("[" + session.getId() + "] " + msg));
            }
          }
        }
        // 사용자 연결 종료 후 동작
        @Override
        public void afterConnectionClosed(
          WebSocketSession session,
          org.springframework.web.socket.CloseStatus status
        ) throws Exception {
          sessions.remove(session); // 연결 종료 시 세션 제거
          System.out.println("사용자 연결 종료: " + session.getId());
        }
      }
    ```
---
### 4. Scheduler
  + 애플리케이션 내에서 특정 작업을 정해진 시간에 자동으로 실행하거나, 주기적으로 반복 실행할 수 있도록 도와주는 기능
  + ex) 배치 처리, 데이터 동기화, 알림 발송 등 다양한 주기적인 작업에 활용
  + `@Scheduled` 어노테이션을 사용하여 스케줄링 기능을 제공
  + 스케줄러 속성 설정 (스케줄러 활성화)
    ```java
      @Configuration
      @EnableScheduling // 스프링 스케줄링 기능 활성화
      public class MySchedulerConfig {
      }
    ```
  + 스케줄 작업 클래스
    ```java
      @Component
      public class MySimpleTask {
        @Scheduled(fixedRate = 3000) // 3초마다 실행
        public void printHello() {
          System.out.println("안녕하세요! 3초마다 자동 실행 중입니다.");
        }
      }
    ```
  + 실행 간격 지정 방식
    + `fixedRate` : 이전 작업이 시작된 시점부터 다음 작업 시작까지의 지연 시간 지정 (polling)
    + `fixedDelay` : 이전 작업이 종료된 시점부터 다음 작업 시작까지의 지연 시간 지정 (long polling)
    + `cron` : Cron 표현식을 사용하여 정확한 시간과 요일을 지정
      + Cron 표현식은 "초 분 시 일 월 요일 [년도]" 순서로 구성
      + ex. cron = "0 0 10 * * ?" (매일 오전 10시 0분 0초에 실행)
      + |위치|의미|예시|
        |:--:|:--:|:--:|
        |1|초(0-59)|0/10 &rarr; 10초마다|
        |2|분(0-59)|0 &rarr; 정각 실행|
        |3|시(0-23)|9 &rarr; 오전 9시|
        |4|일(1-31)|* &rarr; 매일|
        |5|월(1-12)|* &rarr; 매월|
        |6|요일(0-6)|* &rarr; 매주|
 ---
