package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



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
    public String getUserPage() {
        return "my-page";
    }
    
    @GetMapping("/admin-page")
    public String getAdminPage() {
        return "my-page";
    }
    
}
