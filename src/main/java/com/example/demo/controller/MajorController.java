package com.example.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Major;
import com.example.demo.repository.MajorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/major")
public class MajorController {
    @Autowired MajorRepository majorRepository;

    @GetMapping("/add")
    public Major add(@ModelAttribute Major major) {
        // Append Date
        major.setEbtbDate(new Date());

        Major item = majorRepository.save(major);

        return item;
    }

    @GetMapping("/list")
    public List<Major> list() {
        List<Major> result = majorRepository.findAll();

        return result;
    }
    
    @GetMapping("/find")
    public Major find(@RequestParam(defaultValue = "1") Integer id) {
        Major res = new Major();

        Optional<Major> opt = majorRepository.findById(id);
        if (opt.isPresent())
        {
            res = opt.get();
        }

        return res;
    }
    
    
}
