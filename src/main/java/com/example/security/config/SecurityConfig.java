package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/admin").authenticated()
                .anyRequest().permitAll())
                .formLogin(form -> form
                        // .loginPage("/login") // 아직 없지만 나중에 만들 예정
                        .defaultSuccessUrl("/hello", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/hello"));

        return http.build();
    }
}
