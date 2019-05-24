package com.scu.szmt.controller;

import com.scu.szmt.dao.Conference;
import com.scu.szmt.dao.Note;
import com.scu.szmt.dao.RespBean;
import com.scu.szmt.dao.User;
import com.scu.szmt.service.NoteService;
import com.scu.szmt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WsController {
    @Autowired
    NoteService noteService;
    @Autowired
    UserService userService;
    @MessageMapping("/receive")
    @SendTo("/topic/getResponse")
    public String broadcast(String msg){
        System.out.println(msg);
        return msg;
    }
    @RequestMapping("/loadMessage")
    @ResponseBody
    public RespBean loadMessage(@RequestBody Map<String,Object> reqMap){
        int id = (int) reqMap.get("id");
        List<Note> NoteList = noteService.findConInNote(id);
        int count = NoteList.size();
        System.out.println("count"+count);
        if(count>0){
            Map<String,Object> obj = new HashMap<String, Object>();
            obj.put("count",count);
            obj.put("msg",NoteList);
            return RespBean.ok("新消息",obj);
        }
        return RespBean.error("没有消息");
    }
    @RequestMapping("/deleteNote")
    @ResponseBody
    public  RespBean deleteNote(@RequestBody Map<String,Object> reqMap){
        int id = (int) reqMap.get("id");
        noteService.deleteNote(id);
        return RespBean.ok("删除成功");
    }
}
