package com.scu.szmt.util;

import org.springframework.context.annotation.Bean;

import java.util.UUID;

public class UploadUtil {

    public static String getUuidName(String fileName) {
        int index = fileName.lastIndexOf(".");
        if (index != -1) {
            return UUID.randomUUID() + fileName.substring(index);
        }else {
            return UUID.randomUUID().toString();
        }
    }
}
