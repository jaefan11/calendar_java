package com.scu.szmt.controller;

import com.scu.szmt.dao.RespBean;
import com.scu.szmt.dao.User;
import com.scu.szmt.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @RequestMapping(value="/login", method = RequestMethod.POST)
    @ResponseBody
    public RespBean login(@RequestBody Map<String,Object> reqMap){

        String username= reqMap.get("username").toString();
        String password= reqMap.get("password").toString();
        System.out.println(username);
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            User user = new User();
            user = userService.findByUsername(username);
            Map<String,Object> data = new HashMap<String,Object>();
            data.put("user",user);
            data.put("token",token);
            return RespBean.ok("登陆成功",data);
        }catch (UnknownAccountException e){
            //用户名不存在
            return RespBean.error("登陆失败，用户名不存在");
        }catch (IncorrectCredentialsException e){
            return RespBean.error("登陆失败，密码错误");
        }

    }
}
