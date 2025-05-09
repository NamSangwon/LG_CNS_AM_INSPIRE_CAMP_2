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
                .requestMatchers("/admin").authenticated()
                .requestMatchers("/my-page").authenticated()
                .anyRequest().permitAll())
                .formLogin(form -> form
                        .loginPage("/login") // 커스터마이징한 로그인 페이지 지정
                        .defaultSuccessUrl("/hello", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/hello"));

        return http.build();
    }

    // password는 BCrypt를 사용하여 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
