package com.example.session.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.session.model.User;

import jakarta.servlet.http.HttpSession;


@Controller
public class SessionController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 실질적으로 로그인 성공 시, 세션 생성 (서버 부하 피하기)
    @PostMapping("/login")
    public String loginPost(User user, HttpSession session) {
        session.setAttribute("user", user);

        Date date = new Date();
        session.setAttribute("date", date);

        return "redirect:/main";
    }

    // 로그아웃 (세션 제거)
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        // // 제거 방법 1 (세션 내의 user 값 제거)
        // // 안사용하는 데이터를 굳이 남길 필요는 없으므로, 권장 X
        // session.removeAttribute("user");

        // 제거 방법 2 (세션 제거)
        session.invalidate();

        return "redirect:/login";
    }
    

    @GetMapping("/main")
    public String main() {
        return "main";
    }
}
