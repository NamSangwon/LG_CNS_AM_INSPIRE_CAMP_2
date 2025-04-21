package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.component.Game;

// Bean 생성 방법 1 
// @Configuration + @Bean 메소드로 객체 반환
@Configuration
public class BeanConfig {
    @Bean
    public String bean1() {
        return new String("Bean1");
    }

    @Bean
    public Game game() {
        return new Game();
    }
}
