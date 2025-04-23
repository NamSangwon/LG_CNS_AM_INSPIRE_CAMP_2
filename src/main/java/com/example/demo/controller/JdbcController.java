package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.example.demo.dao.DemoDAO;
import com.example.demo.model.Demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JdbcController {
    @Autowired
    DemoDAO demoDAO;

    // [SELECT]
    // Map 구조로 반환
    @GetMapping("/jdbc/demo1")
    public List<Map<String, Object>> demo1() {
        List<Map<String, Object>> list = demoDAO.select1();
        return list;
    }

    // [SELECT]
    // Demo Model 구조로 반환
    @GetMapping("/jdbc/demo2")
    public List<Demo> demo2() {
        List<Demo> list = demoDAO.select2();
        return list;
    }

    // [INSERT]
    @GetMapping("/jdbc/add")
    @ResponseBody
    public String add(@ModelAttribute Demo demo) {
        int result = 0;
        String msg = null;
        try {
            result = demoDAO.insert(demo);
        } catch (DataAccessException e) {
            result = 0;
            msg = e.getMessage();
        }
        if (result == 0) {
            return "실패: " + msg;
        }
        return "성공";
    }

    // [DELETE]
    @GetMapping("/jdbc/delete")
    public String delete(@ModelAttribute Demo demo) {
        int result = 0;
        String msg = null;
        try {
            result = demoDAO.delete(demo);
        } catch (DataAccessException e) {
            result = 0;
            msg = e.getMessage();
        }

        if (result == 0)
            return "실패 : " + msg;
            
        return "성공";
    }
    
}
