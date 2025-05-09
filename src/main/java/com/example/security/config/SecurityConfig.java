package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /*
     * /admin 으로의 요청 시, 로그인 수행
     * 로그인 성공하면, /hello로 이동
     * (아래의 코드에서 UserDetailsService 인터페이스를 구현한 
     * MemberDetailsService의 loadUserByUsername() 메소드를 사용하여 User 객체 정보 가져 옴)
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> auth
                // 요청 권한 처리
                    // authenticated() == 로그인 여부 확인
                    .requestMatchers("/admin").authenticated()
                    .requestMatchers("/my-page").authenticated()
                    // hasRole() == 역할 여부 확인
                    .requestMatchers("/admin-page").hasRole("ADMIN")
                    .requestMatchers("/user-page").hasRole("USER")
                    // 위의 요청을 제외한 나머지는 모두 허용
                    .anyRequest().permitAll()
                )
                // 로그인 처리
                .formLogin(form -> form
                    .loginPage("/login") // 커스터마이징한 로그인 페이지 지정
                    .defaultSuccessUrl("/hello", true)
                    .permitAll()
                )
                // 로그아웃 처리
                .logout(logout -> logout
                    .logoutUrl("/logout") // default: /logout (POST)
                    .logoutSuccessUrl("/login?logout=true")
                    .invalidateHttpSession(true) // 세션 제거
                    .deleteCookies("JSESSIONID") // 쿠키 제거
                );

        return http.build();
    }

    // password는 BCrypt를 사용하여 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
