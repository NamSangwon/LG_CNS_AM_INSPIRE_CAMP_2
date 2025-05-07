package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ExceptionController {
    @GetMapping("exception1")
    public String exception1() throws Exception {
        boolean isError = true;
        if (isError)
            throw new Exception("exception!");
        return "exception1";
    }

    @GetMapping("exception2")
    public String exception2() throws Exception {
        boolean isError = true;
        if (isError)
            throw new Exception("runtime exception!");
        return "exception2";
    }

    @PostMapping("/post/create")
    public String postCreate(@RequestParam(required = false) String title) {

        // 코드 작성
        // title 파라미터가 없으면 오류 처리
        if (title == null) throw new NullPointerException("제목이 없습니다.");

        // title 파라미터가 3글자 이하라면 오류 처리
        if (title.length() <= 3) throw new IllegalArgumentException("제목이 짧습니다.");

        return "게시글 등록 완료";
    }
    
}