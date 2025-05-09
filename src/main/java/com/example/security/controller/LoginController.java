package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class LoginController {
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
    
    @GetMapping("/my-page")
    public String getMyPage() {
        return "my-page";
    }
    
    @GetMapping("/user-page")
    @ResponseBody
    public String getUserPage() {
        return "일반 사용자 페이지입니다.";
    }
    
    @GetMapping("/admin-page")
    @ResponseBody
    public String getAdminPage() {
        return "관리자 사용자 페이지입니다.";
    }
    
}
