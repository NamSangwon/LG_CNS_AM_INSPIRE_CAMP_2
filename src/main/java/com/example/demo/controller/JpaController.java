package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.repository.TableExam1Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.TableExam1;


@Controller
public class JpaController {
    @Autowired TableExam1Repository tableExam1Repository;

    @GetMapping("/jpa/select-all")
    @ResponseBody
    public List<TableExam1> selectAll() {
        List<TableExam1> list = tableExam1Repository.findAll();
        return list;
    }

    @GetMapping("/jpa/insert")
    @ResponseBody
    public TableExam1 insert(@ModelAttribute TableExam1 tableExam1) {
       // 기본 키가 중복일 시, UPDATE
       // 기본 키가 DB에 존재하지 않을 시, INSERT
        TableExam1 result = tableExam1Repository.save(tableExam1);

        return result;
    }
    
    @GetMapping("/jpa/delete")
    @ResponseBody
    public List<TableExam1> delete(@ModelAttribute TableExam1 tableExam1) {
        tableExam1Repository.delete(tableExam1);

        return selectAll();
    }
    
}
