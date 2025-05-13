package com.example.security.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public String getMyPage(
        // 세션에 저장되어 있는 로그인 정보 확인 방법 4가지 (HttpSession 대체)
        Principal principal,
        Authentication authentication,
        @AuthenticationPrincipal UserDetails userDetails // 권장되는 방법
    ) {
        System.out.println("principal: " + principal.getName());
        System.out.println("authentication: " + authentication.getName());
        System.out.println("@AuthenticationPrincipal: " + userDetails.getUsername());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("SecurityContextHolder: " + auth.getName());

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
