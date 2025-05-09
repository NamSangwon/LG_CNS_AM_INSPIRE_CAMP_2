package com.example.security.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "안녕하세요.";
    }
 
    @GetMapping("/admin")
    public String admin() {
        return "관리자 페이지 입니다.";
    }
    
}
