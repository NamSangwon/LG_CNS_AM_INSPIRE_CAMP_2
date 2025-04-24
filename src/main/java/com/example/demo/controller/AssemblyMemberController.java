package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.AssemblyMemberRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.AssemblyMember;

@RestController
public class AssemblyMemberController {
    @Autowired AssemblyMemberRepository assemblyMemberRepository;

    @GetMapping("/assembly-member")
    public List<AssemblyMember> assembly_member(
        @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "10") Integer count
    ) {
        // Pageable 인터페이스를 활용한 페이징
        Pageable pageable = PageRequest.of(page, count);
        Page<AssemblyMember> list = assemblyMemberRepository.findAll(pageable);

        return list.getContent();
    }
    
}
