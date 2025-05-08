package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DownloadController {
    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> getMethodName() throws FileNotFoundException, UnsupportedEncodingException {
        File file = new File("C:/Users/user/Desktop/Backend/workspace/demo/.gitignore");

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                                .header("content-disposition",
                                "attachment; filename=\""+ URLEncoder.encode(file.getName(), "utf-8") +"\"")
                                .contentLength(file.length())
                                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                                .body(resource);
    }
    
}
