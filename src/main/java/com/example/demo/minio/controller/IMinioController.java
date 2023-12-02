package com.example.demo.minio.controller;


import com.example.demo.minio.entity.FileInfo;
import com.example.demo.minio.entity.UploadByFile;
import com.example.demo.minio.entity.UploadByLink;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1/minio/")
public interface IMinioController {

    @GetMapping("list")
    ResponseEntity<List<FileInfo>> getList();

    @PostMapping(value = "upload-by-link")
    ResponseEntity<FileInfo> uploadByLink(@RequestBody @Valid UploadByLink request);

    @PostMapping(value = "upload-by-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<FileInfo> uploadByFile(@ModelAttribute @Valid UploadByFile request);
}