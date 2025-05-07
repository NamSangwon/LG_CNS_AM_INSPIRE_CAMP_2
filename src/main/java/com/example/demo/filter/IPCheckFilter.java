package com.example.demo.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IPCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
              
        // Controller or Next Filter에 들어가기 전
        HttpServletRequest req = (HttpServletRequest) request;
        String ip = req.getRemoteAddr();
        log.error(ip);

        // Controller or Next Filter에 들어감
        chain.doFilter(request, response);


        // Controller or Next Filter에 들어간 후

    }
    
}
