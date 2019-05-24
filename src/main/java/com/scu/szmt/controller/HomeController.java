package com.scu.szmt.controller;

import com.scu.szmt.dao.*;
import com.scu.szmt.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    UserService userService;
    @Autowired
    ConferenceService conferenceService;
    @Autowired
    FileService fileService;
    @Autowired
    RouteService routeService;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    TimeSlotService timeSlotService;
    @Autowired
    PeriodService periodService;
    @RequestMapping("/loadEvents")
    @ResponseBody
    public List<Map<String,Object>> loadEvents(@RequestBody Map<String,Object> reqMap){
        int id = (int) reqMap.get("id");
        int type = (int) reqMap.get("type");
        String start = (String) reqMap.get("start");
        String end = (String) reqMap.get("end");
        System.out.println("load"+type);
        List<Map<String,Object>> data= new ArrayList<>();
        if(type==1){
            List<Conference> conferenceList = conferenceService.findConfeByUserId(id,start,end);
            for(Conference conference: conferenceList){
                if(!conference.isTimeSlot()){
                    Map<String,Object> event=new HashMap<String, Object>();
                    event.put("id",conference.getId());
                    event.put("title",conference.getTitle());
                    event.put("start",conference.getStart());
                    event.put("end",conference.getEnd());
                    event.put("allDay",conference.isAllDay());
                    event.put("place",conference.getPlace());
                    event.put("content",conference.getContent());
                    event.put("creator",userService.findById(conference.getCreId()));
                    if(id!=conference.getCreId()){
                        event.put("backgroundColor","#1a73e8");
                        event.put("borderColor","#1a73e8");
                    }else {
                        event.put("backgroundColor", "#78A6F4");
                        event.put("borderColor", "#78A6F4");
                    }
                    System.out.println(event.get("borderColor"));
                    event.put("persons",userService.findPersonByConId(conference.getId()));
                    event.put("fileList",fileService.findFilesByCid(conference.getId()));
                    data.add(event);
                }else {
                    List<TimeSlot> slots = timeSlotService.findTimeSlotCon(conference.getId(),start,end);
                    for (TimeSlot slot : slots){
                        if(slot.getValue()<5){
                            Map<String,Object> event=new HashMap<String, Object>();
                            event.put("id",conference.getId());
                            event.put("title",conference.getTitle());
                            event.put("start",slot.getStart());
                            event.put("end",slot.getEnd());
                            event.put("allDay",conference.isAllDay());
                            event.put("place",conference.getPlace());
                            event.put("content",conference.getContent());
                            event.put("creator",userService.findById(conference.getCreId()));
                            if(id!=conference.getCreId()){
                                event.put("backgroundColor","#1a73e8");
                                event.put("borderColor","#1a73e8");
                            }else {
                                event.put("backgroundColor", "#78A6F4");
                                event.put("borderColor", "#78A6F4");
                            }
                            System.out.println(event.get("borderColor"));
                            event.put("persons",userService.findPersonByConId(conference.getId()));
                            event.put("fileList",fileService.findFilesByCid(conference.getId()));
                            Map<String,Object> slotTime = new  HashMap<>();
                            slotTime.put("value",slot.getValue());
                            event.put("timeSlot",slotTime);
                            data.add(event);
                        }else {
                            Map<String,Object> event=new HashMap<String, Object>();
                            event.put("id",conference.getId());
                            event.put("title",conference.getTitle());
                            event.put("start",slot.getStart());
                            event.put("end",slot.getEnd());
                            event.put("allDay",conference.isAllDay());
                            event.put("place",conference.getPlace());
                            event.put("content",conference.getContent());
                            event.put("creator",userService.findById(conference.getCreId()));
                            if(id!=conference.getCreId()){
                                event.put("backgroundColor","#1a73e8");
                                event.put("borderColor","#1a73e8");
                            }else {
                                event.put("backgroundColor", "#78A6F4");
                                event.put("borderColor", "#78A6F4");
                            }
                            System.out.println(event.get("borderColor"));
                            event.put("persons",userService.findPersonByConId(conference.getId()));
                            event.put("fileList",fileService.findFilesByCid(conference.getId()));
                            Map<String,Object> slotTime = new  HashMap<>();
                            slotTime.put("value",slot.getValue());
                            slotTime.put("form",periodService.findPeriodCon(slot.geteId()));
                            event.put("timeSlot",slotTime);
                            data.add(event);
                        }
                    }
                }

            }
        }else if(type==2){
            List<Route> routeList = routeService.findRouteByUserId(id,start,end);
            for(Route route: routeList) {
                if(!route.isTimeSlot()){
                    Map<String, Object> event = new HashMap<String, Object>();
                    event.put("id", route.getId());
                    event.put("title", route.getTitle());
                    event.put("start", route.getStart());
                    event.put("end", route.getEnd());
                    event.put("allDay", route.isAllDay());
                    event.put("place", route.getPlace());
                    event.put("content", route.getContent());
                    event.put("creator", userService.findById(route.getCreId()));
                    event.put("backgroundColor", "#3f51b5");
                    event.put("borderColor", "#3f51b5");
                    event.put("persons", userService.findPersonByRid(route.getId()));
                    data.add(event);
                }else {
                    List<TimeSlot> slots = timeSlotService.findTimeSlotRou(route.getId(),start,end);
                    for (TimeSlot slot : slots) {
                        if (slot.getValue() < 5) {
                            Map<String, Object> event = new HashMap<String, Object>();
                            event.put("id", route.getId());
                            event.put("title", route.getTitle());
                            event.put("start", slot.getStart());
                            event.put("end", slot.getEnd());
                            event.put("allDay", route.isAllDay());
                            event.put("place", route.getPlace());
                            event.put("content", route.getContent());
                            event.put("creator", userService.findById(route.getCreId()));
                            event.put("backgroundColor", "#3f51b5");
                            event.put("borderColor", "#3f51b5");
                            event.put("persons", userService.findPersonByRid(route.getId()));
                            Map<String, Object> slotTime = new HashMap<>();
                            slotTime.put("value", slot.getValue());
                            event.put("timeSlot", slotTime);
                            data.add(event);
                        } else {
                            Map<String, Object> event = new HashMap<String, Object>();
                            event.put("id", route.getId());
                            event.put("title", route.getTitle());
                            event.put("start", slot.getStart());
                            event.put("end", slot.getEnd());
                            event.put("allDay", route.isAllDay());
                            event.put("place", route.getPlace());
                            event.put("content", route.getContent());
                            event.put("creator", userService.findById(route.getCreId()));
                            event.put("backgroundColor", "#3f51b5");
                            event.put("borderColor", "#3f51b5");
                            event.put("persons", userService.findPersonByRid(route.getId()));
                            Map<String, Object> slotTime = new HashMap<>();
                            slotTime.put("value", slot.getValue());
                            slotTime.put("form", periodService.findPeriodRou(slot.geteId()));
                            event.put("timeSlot", slotTime);
                            data.add(event);
                        }
                    }
                }

            }
        }else if(type==3){
            List<Schedule> scheduleList = scheduleService.findScheduleByUserId(id,start,end);
            for(Schedule schedule : scheduleList) {
                if(!schedule.isTimeSlot()){
                    Map<String, Object> event = new HashMap<String, Object>();
//            System.out.println(conference.getId());
                    event.put("id", schedule.getId());
                    event.put("title", schedule.getTitle());
                    event.put("start", schedule.getStart());
                    event.put("end", schedule.getEnd());
                    event.put("allDay", schedule.isAllDay());
                    event.put("place", schedule.getPlace());
                    event.put("content", schedule.getContent());
                    event.put("creator", userService.findById(schedule.getCreId()));
                    event.put("backgroundColor", "#33b679");
                    event.put("borderColor", "#33b679");
                    event.put("persons", userService.findPersonBySid(schedule.getId()));
                    data.add(event);
                }else {
                    List<TimeSlot> slots = timeSlotService.findTimeSlotSch(schedule.getId(),start,end);
                    for (TimeSlot slot : slots) {
                        if (slot.getValue() < 5) {
                            Map<String, Object> event = new HashMap<String, Object>();
                            event.put("id", schedule.getId());
                            event.put("title", schedule.getTitle());
                            event.put("start", slot.getStart());
                            event.put("end", slot.getEnd());
                            event.put("allDay", schedule.isAllDay());
                            event.put("place", schedule.getPlace());
                            event.put("content", schedule.getContent());
                            event.put("creator", userService.findById(schedule.getCreId()));
                            event.put("backgroundColor", "#33b679");
                            event.put("borderColor", "#33b679");
                            event.put("persons", userService.findPersonBySid(schedule.getId()));
                            Map<String, Object> slotTime = new HashMap<>();
                            slotTime.put("value", slot.getValue());
                            event.put("timeSlot", slotTime);
                        }else {
                            Map<String, Object> event = new HashMap<String, Object>();
                            event.put("id", schedule.getId());
                            event.put("title", schedule.getTitle());
                            event.put("start", slot.getStart());
                            event.put("end", slot.getEnd());
                            event.put("allDay", schedule.isAllDay());
                            event.put("place", schedule.getPlace());
                            event.put("content", schedule.getContent());
                            event.put("creator", userService.findById(schedule.getCreId()));
                            event.put("backgroundColor", "#33b679");
                            event.put("borderColor", "#33b679");
                            event.put("persons", userService.findPersonBySid(schedule.getId()));
                            Map<String, Object> slotTime = new HashMap<>();
                            slotTime.put("value", slot.getValue());
                            slotTime.put("form", periodService.findPeriodSch(slot.geteId()));
                            event.put("timeSlot", slotTime);
                            data.add(event);
                        }
                    }
                }

            }
        }

        return data;
    }
    @RequestMapping("/deletEvent")
    @ResponseBody
    public RespBean deletEvent(@RequestBody Map<String,Object> map){
        int type = Integer.valueOf(map.get("type").toString());
        int id = Integer.valueOf(map.get("id").toString());
        if(type==1){
            fileService.deletFileByCid(id);

            conferenceService.deletConfernceById(id);
            return RespBean.ok("成功");
        }
        if(type==2){
            routeService.deletRouteById(id);
            return RespBean.ok("成功");
        }
        if(type==3){
            scheduleService.deletScheduleById(id);
            return RespBean.ok("成功");
        }
        return RespBean.error("失败");
    }
    @RequestMapping("/search")
    @ResponseBody
    public List<Map<String,Object>> search(@RequestBody Map<String,Object> reqMap){
        int type = (int) reqMap.get("type");
        String content = (String) reqMap.get("content");
        String person = (String) reqMap.get("person");
        String place = (String) reqMap.get("place");
        String start = (String) reqMap.get("start");
        String end = (String) reqMap.get("end");
        System.out.println(content+"content");
        System.out.println(person);
        System.out.println(place+"place");
        System.out.println(start);
        int id = (int) reqMap.get("id");
        List<Map<String,Object>> data= new ArrayList<>();
        if(type==1){
            List<Conference> conferenceList = conferenceService.searchCon(content,person,place,start,end,id);
            for(Conference conference: conferenceList){
                Map<String,Object> event=new HashMap<String, Object>();
                event.put("id",conference.getId());
                event.put("title",conference.getTitle());
                event.put("start",conference.getStart());
                event.put("end",conference.getEnd());
                event.put("allDay",conference.isAllDay());
                event.put("place",conference.getPlace());
                event.put("content",conference.getContent());
                event.put("type",type);
//                event.put("creator",userService.findById(conference.getCreId()));
//                if(id!=conference.getCreId()){
//                    event.put("backgroundColor","#1a73e8");
//                    event.put("borderColor","#1a73e8");
//                }else {
//                    event.put("backgroundColor", "#78A6F4");
//                    event.put("borderColor", "#78A6F4");
//                }
//                System.out.println(event.get("borderColor"));
//                event.put("persons",userService.findPersonByConId(conference.getId()));
//                event.put("fileList",fileService.findFilesByCid(conference.getId()));
                data.add(event);
            }

        }else if(type==2){
            List<Route> routeList = routeService.searchRou(content,person,place,start,end,id);
            for(Route route: routeList) {
                Map<String, Object> event = new HashMap<String, Object>();
                event.put("id", route.getId());
                event.put("title", route.getTitle());
                event.put("start", route.getStart());
                event.put("end", route.getEnd());
                event.put("allDay", route.isAllDay());
                event.put("place", route.getPlace());
                event.put("content", route.getContent());
                event.put("type",type);
//                event.put("creator", userService.findById(route.getCreId()));
//                event.put("backgroundColor", "#3f51b5");
//                event.put("borderColor", "#3f51b5");
//                event.put("persons", userService.findPersonByRid(route.getId()));
                data.add(event);
            }
        }else if(type==3){
            List<Schedule> scheduleList = scheduleService.findScheduleByUserId(id,start,end);
            for(Schedule schedule : scheduleList) {
                Map<String, Object> event = new HashMap<String, Object>();
//            System.out.println(conference.getId());
                event.put("id", schedule.getId());
                event.put("title", schedule.getTitle());
                event.put("start", schedule.getStart());
                event.put("end", schedule.getEnd());
                event.put("allDay", schedule.isAllDay());
                event.put("place", schedule.getPlace());
                event.put("content", schedule.getContent());
                event.put("type",type);
//                event.put("creator", userService.findById(schedule.getCreId()));
//                event.put("backgroundColor", "#33b679");
//                event.put("borderColor", "#33b679");
//                event.put("persons", userService.findPersonBySid(schedule.getId()));
                data.add(event);
            }
        }

        return data;
    }
}
