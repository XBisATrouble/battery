package com.bupt.battery.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(path = "/api/drivingLog",method = RequestMethod.POST)
public class DrivingLogController {
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public void uploadMultipart(@RequestParam("file") MultipartFile file,@RequestParam("id") Long id) {
        System.out.println(id);
        try {
            // 获取文件并保存到指定文件夹中
            byte[] bytes = file.getBytes();
            String filename = file.getOriginalFilename();
            Path path = Paths.get(filename);
            Files.write(path, bytes);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
