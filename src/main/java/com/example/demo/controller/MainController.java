package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController // JSON
public class MainController {
    @GetMapping("/board")
    // @RequestParam의 매개변수 이름 = GET으로 전달되는 키 이름
    public String board(@RequestParam String numParam) {
        return "게시물 번호 = " + numParam;
    }
    
}
