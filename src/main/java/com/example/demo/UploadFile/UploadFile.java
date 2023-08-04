package com.example.demo.UploadFile;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
@Component
public class UploadFile {
    @Autowired
    private  ResourceLoader resourceLoader;
    public  String uploadFile(MultipartFile file,String path) {
        try {
            String uploadPath = resourceLoader.getResource("classpath:/static/"+path).getFile().getAbsolutePath();
            String fileName = file.getOriginalFilename();
            System.out.println(File.separator + fileName);
            File destinationFile = new File(uploadPath + File.separator + fileName);
            file.transferTo(destinationFile);
            return "\\"+path+File.separator + fileName;
        } catch (IOException e) {
            System.out.println(e);
            return "Lỗi khi lưu tệp tải lên: " + e.getMessage();
        }
    }
}
