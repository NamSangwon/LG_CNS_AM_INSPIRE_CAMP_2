package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.component.Bean3;
import com.example.demo.component.Game;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그 관련 도구
@Controller
public class HomeController {
    // Bean 가져오기
    // 1. @Configuration + @Bean
    @Autowired String bean1;
    @Autowired Game game;
    // 2. @Component (권장되는 방식)
    @Autowired Bean3 bean3; 

    // JDBC Bean DI
    @Autowired JdbcTemplate jdbcTemplate;

    // home.html 반환
    @GetMapping("/home")
    public String home(){
        // Bean 사용
        System.out.println(bean1);
        System.out.println(game.play());
        System.out.println(bean3.run());

        // log level -> trace > debug > info > warn > error
        // debug는 log level이 info 위라서 출력 X
        // log level을 수정 必 (-> 설정 파일에 logging.level.(ProjectFolder)=trace 추가) 
        log.debug("debug!!"); 
        log.info("info!!");
        log.error("error!!");

        return "home";
    }
    
    // home 텍스트 반환
    @GetMapping("/home2")
    @ResponseBody
    public List<Map<String, Object>> home2() {
        String sql = "select * from emp";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
    
}
