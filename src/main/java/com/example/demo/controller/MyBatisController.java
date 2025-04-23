package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mapper.DemoMapper;
import com.example.demo.model.Demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MyBatisController {
    @Autowired DemoMapper demoMapper;

    @GetMapping("/mybatis/demo1")
    public List<Map<String, Object>> demo1() {
        List<Map<String, Object>> list = demoMapper.select1();

        return list;
    }

    @GetMapping("/mybatis/demo2")
    public List<Demo> demo2() {
        List<Demo> list = demoMapper.select2();

        return list;
    }
 
    @GetMapping("/mybatis/insert1")
    public String insert1(
        @RequestParam int seq,
        @RequestParam String user
    ){
        try {
            demoMapper.insert1(seq, user);
        } catch (Exception e) {
            return "실패 : " + e.getMessage();
        }

        return "성공";
    }
    @GetMapping("/mybatis/insert2")
    public String insert2(@ModelAttribute Demo demo){
        try {
            demoMapper.insert2(demo);
        } catch (Exception e) {
            return "실패 : " + e.getMessage();
        }

        return "성공";
    }
 
    @GetMapping("/mybatis/delete1")
    public String delete1(
        @RequestParam int seq
    ){
        try {
            demoMapper.delete1(seq);
        } catch (Exception e) {
            return "실패 : " + e.getMessage();
        }

        return "성공";
    }

    @GetMapping("/mybatis/delete2")
    public String delete2(@ModelAttribute Demo demo){
        try {
            demoMapper.delete2(demo);
        } catch (Exception e) {
            return "실패 : " + e.getMessage();
        }

        return "성공";
    }

}
