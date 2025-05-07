package com.example.board.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.board.filter.LoginCheckFilter;

import jakarta.servlet.Filter;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<Filter> getFilterRegistrationBean() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>(new LoginCheckFilter());

        bean.addUrlPatterns("/signin", "/board/write");

        return bean;
    }
}
