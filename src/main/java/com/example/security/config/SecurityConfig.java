package com.example.security.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.example.security.filter.JwtAuthFilter;
import com.example.security.util.JwtUtil;

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
                // 요청 권한 처리
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin").authenticated()
                        .requestMatchers("/my-page").authenticated()
                        .requestMatchers("/admin-page").hasRole("ADMIN")
                        .requestMatchers("/user-page").hasRole("USER")
                        .requestMatchers("/jwt/login").permitAll()
                        .requestMatchers("/jwt/protected").authenticated()
                        .anyRequest().permitAll())
                // 커스텀 필터 등록
                // 스프링 시큐리티의 로그인 인증(UsernamePasswordAuthenticationFilter) 전에, JWT 로그인 검증
                // 매 요청마다 먼저 JWT 토큰을 검사하고 인증을 시도
                .addFilterBefore(
                        new JwtAuthFilter(jwtUtil, userDetailsService),
                        UsernamePasswordAuthenticationFilter.class)
                // CSRF 설정
                // REST API + JWT 방식에서는 일반적으로 CSRF를 사용하지 않기 때문에 비활성화
                .csrf(csrf -> csrf
                        // CSRF 공격이 들어오더라도 통과 (CSRF 토큰 검사 X)
                        // cors-test와 같이 5500 다른 포트에서 요청
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
                );

        return http.build();
    }

    // 인증을 수행하는 핵심 인터페이스
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
