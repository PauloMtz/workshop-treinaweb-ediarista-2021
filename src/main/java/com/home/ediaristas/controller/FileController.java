package com.home.ediaristas.controller;

import java.io.IOException;
import java.net.URLConnection;

import com.home.ediaristas.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/diaristas")
public class FileController {
  
    @Autowired
    private FileService fileService;

    @GetMapping("/upload")
    public ResponseEntity<Object> file(@RequestParam String filename) throws IOException {
        var file = fileService.carregar(filename);
        var contentType = URLConnection.guessContentTypeFromName(file.getFilename());

        return ResponseEntity.status(HttpStatus.OK)
            .header("Content-Type", contentType)
            .body(file.getInputStream().readAllBytes());
    }

    // http://localhost:8080/admin/diaristas/upload?filename=f8a6b1f2-9d43-4ccf-95ff-ff03722f0bc8.jpeg
}
