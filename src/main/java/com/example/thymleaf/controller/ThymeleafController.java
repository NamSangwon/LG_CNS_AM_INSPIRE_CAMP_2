package com.example.thymleaf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {
    @GetMapping("user")
    public String user(Model model) {
        // Model을 통해 html 파일에 데이터 전송
        Map<String, Object> user = new HashMap<>();
        user.put("userId", "z");
        user.put("userName", "zoo");
        user.put("userAge", 25);
        model.addAttribute("user", user);

        // main.resources/template/*.html 파일에 매핑
        return "user";
    }
}
