package com.example.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.filter.IPCheckFilter;

import jakarta.servlet.Filter;

@Configuration
public class FIlterConfig {
    @Bean
    public FilterRegistrationBean<Filter> getFilterRegistrationBean() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>(new IPCheckFilter());

        // Filter 후 매핑 (Controller or Other Filter)
        bean.addUrlPatterns("/visitor");

        return bean;
    }
}
