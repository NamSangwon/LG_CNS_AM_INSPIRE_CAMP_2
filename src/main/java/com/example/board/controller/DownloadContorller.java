package com.example.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.board.entity.Board;
import com.example.board.entity.FileAttach;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.FileAttachRepository;

@Controller
public class DownloadContorller {
    @Autowired
    BoardRepository boardRepository;

    @Autowired 
    FileAttachRepository fileAttachRepository;

    @Transactional(rollbackFor = IOException.class)
    @GetMapping("/download")
    public ResponseEntity<Resource> getMethodName(
        @RequestParam Long boardId
    ) throws FileNotFoundException, UnsupportedEncodingException {
    
        List<String> fileNames = new ArrayList<>();

        Optional<Board> optBoard = boardRepository.findById(boardId);
        if (optBoard.isPresent()) {
            Board board = optBoard.get();

            List<FileAttach> files = fileAttachRepository.findByBoard(board);
        
            for (FileAttach file : files) {
                fileNames.add(file.getChangedName());
            }
        }

        File file = new File("c:/upload/" + fileNames.get(0));

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header("content-disposition",
                        "attachment; filename=\"" +
                                URLEncoder.encode(file.getName(), "utf-8") + "\"")
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}
