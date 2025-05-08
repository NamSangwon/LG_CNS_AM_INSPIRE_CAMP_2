package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.model.FileInfo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;




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
    public String postUpload3(@ModelAttribute FileInfo info) throws IllegalStateException, IOException {
       String result = "";

       String oName = info.getFile().getOriginalFilename();
       
       result += oName + "<br>" + info.getFile().getSize();

       MultipartFile file = info.getFile();

       // 저장시킬 폴더 존재 여부 확인
       File dir = new File("c:/upload");
       if (!dir.isDirectory()) {
            dir.mkdir();
       }

       // 파일명 중복 피하기
       UUID uuid = UUID.randomUUID();

       int ext_idx = oName.lastIndexOf(".");
       String ext = oName.substring(ext_idx); // .zip, .jpg, ...

       // 업로드된 파일 저장 (원본 파일명은 DB에 저장)
       File f = new File("c:/upload/" + uuid + ext);
       file.transferTo(f);

       return result;
    }
    
    @GetMapping("/multiple-upload")
    public String getMultipleUpload() {
        return "multiple_upload";
    }

    @PostMapping("/multiple-upload")
    @ResponseBody
    public String postMethodName(MultipartHttpServletRequest mRequest) throws IllegalStateException, IOException {
        String result = "";

        Iterator<String> fileNames = mRequest.getFileNames();

        File dir = new File("c:/upload");
        if (!dir.isDirectory()) {
            dir.mkdir();
        }

        while(fileNames.hasNext()) {
            String fileName = fileNames.next();

            List<MultipartFile> files = mRequest.getFiles(fileName);
            for (MultipartFile file : files) {
                String oName = file.getOriginalFilename();
                Long size = file.getSize();
                result += oName + " : " + size + "<br>";

                // 파일 저장
                File f = new File("c:/upload/" + oName);
                file.transferTo(f);
            }
        }

        return result;
    }
    
}
