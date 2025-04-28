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

    @GetMapping("userList")
    public String userList(Model model) {
        // User List 생성
        List<Map<String, Object>> userList = new ArrayList<>();
        
        // User 1 생성
        Map<String, Object> user = new HashMap<>();
        user.put("userId", "a");
        user.put("userName", "apple");
        user.put("userAge", 21);
        userList.add(user);

        // User 2 생성
        user = new HashMap<>();
        user.put("userId", "b");
        user.put("userName", "banana");
        user.put("userAge", 22);
        userList.add(user);

        // User 3 생성
        user = new HashMap<>();
        user.put("userId", "c");
        user.put("userName", "carrot");
        user.put("userAge", 23);
        userList.add(user);

        // Model에 데이터 추가
        model.addAttribute("userList", userList);
        
        return "user_list";
    }
}
