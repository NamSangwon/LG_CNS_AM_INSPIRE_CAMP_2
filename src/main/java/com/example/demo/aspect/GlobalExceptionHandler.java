package com.example.demo.aspect;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.controller.ExceptionController;

@ControllerAdvice(assignableTypes = {ExceptionController.class})
public class GlobalExceptionHandler {
    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class}) // 다음 두 개의 예외만 처리
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, Object> exceptionHandle(Exception e, WebRequest webRequest) {
        Map<String, Object> error = new HashMap<>();

        error.put("message", e.getMessage());
        error.put("status", "fail");

        return error;
    }
}
