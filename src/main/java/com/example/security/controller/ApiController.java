package com.example.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api")
public class ApiController {

    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    @GetMapping("/message")
    public String getMessage() {
        return "[GET] 서버에서 보낸 메시지 입니다.";
    }
    
    @PostMapping("/message")
    public String postMessage() {
        return "[POST] 서버에서 보낸 메시지 입니다.";
    }
    
}
