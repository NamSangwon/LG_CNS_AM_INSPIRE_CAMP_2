package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}