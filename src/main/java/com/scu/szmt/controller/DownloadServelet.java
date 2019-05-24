package com.scu.szmt.controller;

import com.scu.szmt.dao.MyFile;
import com.scu.szmt.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Paths;
import java.util.Map;

@RestController
public class DownloadServelet  {
    @Autowired
    FileService fileService;

    @PostMapping("/downloadFile")
    public void downloadFile(@RequestBody Map<String,Object> reqMap , HttpServletRequest request, HttpServletResponse response) throws IOException {
//根据主键ID从数据库中查询文件信息
        int id =Integer.valueOf(reqMap.get("id").toString());
        System.out.println("下载id"+id);
        MyFile myFile =fileService.findFileById(id);
//获取文件的名称(包含后缀)
        String fileName= myFile.getName();
        System.out.println(fileName);
        String filePath = myFile.getUrl();
        File fs = new File(filePath);
        if(fs.exists()){

            byte[] buffer= new byte[1024*100];
            //char[] buffer= new char[1024];
            int len=-1;
            String mimeType = request.getServletContext().getMimeType(fileName);
            System.out.println(mimeType);
//            response.setContentType(mimeType);
            response.setContentType("application/force-download");
            response.setHeader("content-disposition", "attachment;filename="+fileName);
            FileInputStream fis = new FileInputStream(fs);
            OutputStream os = response.getOutputStream();
            while ((len=fis.read(buffer))!=-1){
                System.out.println("下载zhong");
                os.write(buffer,0,len);
                os.flush();
            }
            os.close();
            fis.close();
        }

    }
}
