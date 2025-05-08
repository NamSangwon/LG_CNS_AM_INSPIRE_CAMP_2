package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.model.FileInfo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class UploadController {
    @GetMapping("/upload1")
    public String getUpload1() {
        return "upload1";
    }

    @PostMapping("/upload1")
    @ResponseBody
    public String postUpload1(MultipartHttpServletRequest mRequest) {
       String result = "";

       MultipartFile mFile = mRequest.getFile("file"); // param name
       String oName = mFile.getOriginalFilename();
       
       result += oName + "<br>" + mFile.getSize();

       return result;
    }

    @GetMapping("/upload2")
    public String getUpload2() {
        return "upload1";
    }

    @PostMapping("/upload2")
    @ResponseBody
    public String postUpload2(@RequestParam("file") MultipartFile mFile) {
       String result = "";

       String oName = mFile.getOriginalFilename();
       
       result += oName + "<br>" + mFile.getSize();

       return result;
    }
    
    @GetMapping("/upload3")
    public String getUpload3() {
        return "upload1";
    }

    @PostMapping("/upload3")
    @ResponseBody
    public String postUpload3(@ModelAttribute FileInfo info) {
       String result = "";

       String oName = info.getFile().getOriginalFilename();
       
       result += oName + "<br>" + info.getFile().getSize();

       return result;
    }
    
}
