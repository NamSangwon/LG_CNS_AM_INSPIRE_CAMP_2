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
---
### 2. REST API
  + 
---
### 3. Web Socket
  + 
---
### 4. Scheduler
  + 
---
