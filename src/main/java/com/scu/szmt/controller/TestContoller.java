package com.scu.szmt.controller;

import com.scu.szmt.dao.User;
import com.scu.szmt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestContoller {
    @Autowired
    UserService userService;

    @RequestMapping("/hello")
    @ResponseBody
    public  String HelloWorld(){
        User user;
        user = userService.findByUsername("fanyi");
        return  "Hello World!"+user;
    }
}
