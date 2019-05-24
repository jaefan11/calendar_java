package com.scu.szmt.controller;

import com.scu.szmt.dao.*;
import com.scu.szmt.service.*;


import com.scu.szmt.util.AddEventUtil;
import com.scu.szmt.util.TimeSlotGeneration;
import com.scu.szmt.util.UploadUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class CreateController {
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
    NoteService noteService;
    @Autowired
    TimeSlotService timeSlotService;
    @Autowired
    PeriodService periodService;
    @RequestMapping("/creInitPerson")
    @ResponseBody
    public List<User> creInitPerson(@RequestBody Map<String,Object> reqMap){
        int id = (int) reqMap.get("id");
        List<User> data =userService.findAllExcId(id);
        System.out.println(data.get(0).getName());
        return data;
    }
    @PostMapping("/uploadFile")
    @ResponseBody
    public RespBean uploadFile(@RequestParam("files") MultipartFile files,@RequestParam("id") int id){
        System.out.println(files.getOriginalFilename());
        InputStream fis = null;
        OutputStream outputStream = null;
        File directory = new File("");

        try {
            String pfs = directory.getCanonicalPath();
            fis = files.getInputStream();
            String name = files.getOriginalFilename();
            String uuidname = UploadUtil.getUuidName(name);
            File fs = new File(pfs+"\\uploadFile\\"+id);
            System.out.println(fs.toString());
            if(!fs.exists()){
                fs.mkdirs();
            }
            File url = new File(fs,uuidname);
            outputStream = new FileOutputStream(new File(fs,uuidname));
            IOUtils.copy(fis,outputStream);
            MyFile myFile = new MyFile();
            myFile.setName(name);
            myFile.setUrl(url.toString());
            fileService.uploadFile(myFile);

            return RespBean.ok("上传成功",myFile);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  RespBean.error("上传失败");
    }
    @RequestMapping("/addEvent")
    @ResponseBody
    public RespBean addEvent(@RequestBody Map<String,Object> reqMap) {
        int type = Integer.valueOf(reqMap.get("type").toString());
        if (type == 1) {
            try {
                AddEventUtil.addConference(reqMap);
                return RespBean.ok("创建成功");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if (type==2){
            try {
                AddEventUtil.addRoute(reqMap);
                return RespBean.ok("创建成功");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if(type==3){
            try {
                AddEventUtil.addSchedule(reqMap);
                return RespBean.ok("创建成功");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return RespBean.error("创建失败");
    }
    @GetMapping("/removeFile")
    @ResponseBody
    public RespBean removeFile(@RequestParam("id") int id){
        MyFile myFile = fileService.findFileById(id);
        File file = new File(myFile.getUrl());
        if(file.delete()) {
            fileService.removeFileById(id);
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
    @RequestMapping("/updateEvent")
    @ResponseBody
    public RespBean updateEvent(@RequestBody Map<String,Object> reqMap) throws ParseException {
        int type = Integer.valueOf(reqMap.get("type").toString());
        int Id = Integer.valueOf(reqMap.get("id").toString());
        System.out.println(Id);
        if (type == 1) {
            Conference conference = new Conference();
            conference.setId(Id);
            if (reqMap.containsKey("title")) {
                conference.setTitle(reqMap.get("title").toString());
                System.out.println(reqMap.get("title").toString());
            }
            if (reqMap.containsKey("start")) {
                conference.setStart(reqMap.get("start").toString());
            }
            if (reqMap.containsKey("end")) {
                conference.setEnd(reqMap.get("end").toString());
            }
            if (reqMap.get("allDay").toString() == "true") {
                conference.setAllDay(true);
            } else {
                conference.setAllDay(false);
            }
            if (reqMap.containsKey("content")) {
                conference.setContent(reqMap.get("content").toString());
            }
            if (reqMap.containsKey("place")) {
                conference.setPlace(reqMap.get("place").toString());
            }
            System.out.println(conferenceService.updateConferenceById(conference));
            if (reqMap.containsKey("persons")) {
                conferenceService.deletPersonsConByCid(Id);
                ArrayList<Integer> list = (ArrayList<Integer>) reqMap.get("persons");
                for (Integer userId : list) {
                    conferenceService.addPersons(Id, userId);
                }
            }
            if (reqMap.containsKey("fileList")){
                ArrayList<Map<String,Object>> fileList = (ArrayList<Map<String,Object>>)reqMap.get("fileList");
                for (Map<String,Object> file : fileList){
                    int fId = Integer.valueOf(file.get("id").toString());
                    System.out.println("Fid"+fId);
                    conferenceService.addFileCon(fId,Id);
                }
            }
            HashMap<String,Object> timeSlot = (HashMap<String,Object>)reqMap.get("timeSlot");
            int value=(int) timeSlot.get("value");
            if(value==1){
                conference.setTimeSlot(false);
            }else {
                conference.setTimeSlot(true);
                timeSlotService.deletTimeSlotCon(Id);
            }
            if(value>1 && value <5){

                if(value==2){
                    if(conference.getEnd()!=null){
                        List<TimeSlot> times= TimeSlotGeneration.everyDay(Id,value,conference.getStart(),conference.getEnd());
                        for (TimeSlot time : times){
                            timeSlotService.addTimeSlotCon(time);
                        }
                    }else {
                        List<TimeSlot> times= TimeSlotGeneration.everyDay(Id,value,conference.getStart());
                        for (TimeSlot time : times){
                            timeSlotService.addTimeSlotCon(time);
                        }
                    }
                }else if(value==3){
                    if(conference.getEnd()!=null){
                        List<TimeSlot> times= TimeSlotGeneration.everyMonth(Id,value,conference.getStart(),conference.getEnd());
                        for (TimeSlot time : times){
                            timeSlotService.addTimeSlotCon(time);
                        }
                    }else {
                        List<TimeSlot> times= TimeSlotGeneration.everyMonth(Id,value,conference.getStart());
                        for (TimeSlot time : times){
                            timeSlotService.addTimeSlotCon(time);
                        }
                    }
                }else if(value==4){
                    if(conference.getEnd()!=null){
                        List<TimeSlot> times= TimeSlotGeneration.everyYear(Id,value,conference.getStart(),conference.getEnd());
                        for (TimeSlot time : times){
                            timeSlotService.addTimeSlotCon(time);
                        }
                    }else {
                        List<TimeSlot> times= TimeSlotGeneration.everyYear(Id,value,conference.getStart());
                        for (TimeSlot time : times){
                            timeSlotService.addTimeSlotCon(time);
                        }
                    }
                }
            }else  if(value>=5){
                periodService.deletPeriodCon(Id);
                Period period = new Period();
                period.setStart(conference.getStart());
                ArrayList<String> weeks =new ArrayList<>();
                HashMap<String,Object> slot = (HashMap<String,Object>) timeSlot.get("slot");
                int num1 = (int)slot.get("num1");
                period.setNum1(num1);
                String week = "";
                weeks= (ArrayList<String>) slot.get("week1");
                for(int i = 0; i < weeks.size(); i++){
                    if(i<weeks.size()-1){
                        week+=weeks.get(i)+",";
                    }else {
                        week+=weeks.get(i);
                    }
                }
                period.setWeek(week);
                String radio = (String)slot.get("radio");
                period.setRadio(radio);
                if(radio.equals("2")){
                    String dateEnd = (String)slot.get("date3");
                    period.setDateEnd(dateEnd);
                }else if(radio.equals("3")){
                    int num2 = (int)slot.get("num2");
                    period.setNum2(num2);
                }
                period.seteId(Id);
                if(conference.getEnd()!=null){
                    period.setEnd(conference.getEnd());
                }
                periodService.addPeriodCon(period);
                List<TimeSlot> times= TimeSlotGeneration.ByPeriod(Id,value,period);
                for (TimeSlot time : times){
                    timeSlotService.addTimeSlotCon(time);
                }
            }
            return RespBean.ok("修改成功");
        }else if(type==2){
            Route route = new  Route();
            route.setId(Id);
            if (reqMap.containsKey("title")) {
                route.setTitle(reqMap.get("title").toString());

            }
            if (reqMap.containsKey("start")) {
                route.setStart(reqMap.get("start").toString());
            }
            if (reqMap.containsKey("end")) {
                route.setEnd(reqMap.get("end").toString());
            }
            if (reqMap.get("allDay").toString() == "true") {
                route.setAllDay(true);
            } else {
                route.setAllDay(false);
            }
            if (reqMap.containsKey("content")) {
                route.setContent(reqMap.get("content").toString());
            }
            if (reqMap.containsKey("place")) {
                route.setPlace(reqMap.get("place").toString());
            }

            if (reqMap.containsKey("persons")) {
                routeService.deletPersonsRouByRid(Id);
                ArrayList<Integer> list = (ArrayList<Integer>) reqMap.get("persons");
                for (Integer userId : list) {
                    routeService.addPersons(Id,userId);
                }
            }
            HashMap<String,Object> timeSlot = (HashMap<String,Object>)reqMap.get("timeSlot");
            int value=(int) timeSlot.get("value");
            if(value==1){
                route.setTimeSlot(false);
            }else {
                route.setTimeSlot(true);
                timeSlotService.deletTimeSlotRou(Id);
            }
            if(value>1 && value<5){
                if(value==2){
                    if(route.getEnd()!=null){
                        List<TimeSlot> times= TimeSlotGeneration.everyDay(Id,value,route.getStart(),route.getEnd());
                        for (TimeSlot time : times){
                            timeSlotService.addTimeSlotRou(time);
                        }
                    }else {
                        List<TimeSlot> times= TimeSlotGeneration.everyDay(Id,value,route.getStart());
                        for (TimeSlot time : times){
                            timeSlotService.addTimeSlotRou(time);
                        }
                    }
                }else if(value==3){
                    if(route.getEnd()!=null){
                        List<TimeSlot> times= TimeSlotGeneration.everyMonth(Id,value,route.getStart(),route.getEnd());
                        for (TimeSlot time : times){
                            timeSlotService.addTimeSlotRou(time);
                        }
                    }else {
                        List<TimeSlot> times= TimeSlotGeneration.everyMonth(Id,value,route.getStart());
                        for (TimeSlot time : times){
                            timeSlotService.addTimeSlotRou(time);
                        }
                    }
                }else if(value==4){
                    if(route.getEnd()!=null){
                        List<TimeSlot> times= TimeSlotGeneration.everyYear(Id,value,route.getStart(),route.getEnd());
                        for (TimeSlot time : times){
                            timeSlotService.addTimeSlotRou(time);
                        }
                    }else {
                        List<TimeSlot> times= TimeSlotGeneration.everyYear(Id,value,route.getStart());
                        for (TimeSlot time : times){
                            timeSlotService.addTimeSlotRou(time);
                        }
                    }
                }

            }else  if(value>=5){
                periodService.deletPeriodRou(Id);
                Period period = new Period();
                period.setStart(route.getStart());
                ArrayList<String> weeks =new ArrayList<>();
                HashMap<String,Object> slot = (HashMap<String,Object>) timeSlot.get("slot");
                int num1 = (int)slot.get("num1");
                period.setNum1(num1);
                String week = "";
                weeks= (ArrayList<String>) slot.get("week1");
                for(int i = 0; i < weeks.size(); i++){
                    if(i<weeks.size()-1){
                        week+=weeks.get(i)+",";
                    }else {
                        week+=weeks.get(i);
                    }
                }
                period.setWeek(week);
                String radio = (String)slot.get("radio");
                period.setRadio(radio);
                if(radio.equals("2")){
                    String dateEnd = (String)slot.get("date3");
                    period.setDateEnd(dateEnd);
                }else if(radio.equals("3")){
                    int num2 = (int)slot.get("num2");
                    period.setNum2(num2);
                }
                period.seteId(Id);
                if(route.getEnd()!=null){
                    period.setEnd(route.getEnd());
                }
                periodService.addPeriodRou(period);
                List<TimeSlot> times= TimeSlotGeneration.ByPeriod(Id,value,period);
                for (TimeSlot time : times){
                    timeSlotService.addTimeSlotRou(time);
                }
            }
            return RespBean.ok("修改成功");
        }else if(type==3){
            Schedule schedule = new Schedule();
            schedule.setId(Id);
            if (reqMap.containsKey("title")) {
                schedule.setTitle(reqMap.get("title").toString());

            }
            if (reqMap.containsKey("start")) {
                schedule.setStart(reqMap.get("start").toString());
            }
            if (reqMap.containsKey("end")) {
                schedule.setEnd(reqMap.get("end").toString());
            }
            if (reqMap.get("allDay").toString() == "true") {
                schedule.setAllDay(true);
            } else {
                schedule.setAllDay(false);
            }
            if (reqMap.containsKey("content")) {
                schedule.setContent(reqMap.get("content").toString());
            }
            if (reqMap.containsKey("place")) {
                schedule.setPlace(reqMap.get("place").toString());
            }

            if (reqMap.containsKey("persons")) {
                ArrayList<Integer> list = (ArrayList<Integer>) reqMap.get("persons");
                for (Integer userId : list) {
                    scheduleService.addPersons(Id,userId);
                }                routeService.deletPersonsRouByRid(Id);

            }
            HashMap<String,Object> timeSlot = (HashMap<String,Object>)reqMap.get("timeSlot");
            int value=(int) timeSlot.get("value");
            if(value==1){
                schedule.setTimeSlot(false);
            }else {
                schedule.setTimeSlot(true);
                timeSlotService.deletTimeSlotSch(Id);
            }
            if(value>1 && value<5){
                if(value==2){
                    if(schedule.getEnd()!=null){
                        List<TimeSlot> times= TimeSlotGeneration.everyDay(Id,value,schedule.getStart(),schedule.getEnd());
                        for (TimeSlot time : times){
                            timeSlotService.addTimeSlotSch(time);
                        }
                    }else {
                        List<TimeSlot> times= TimeSlotGeneration.everyDay(Id,value,schedule.getStart());
                        for (TimeSlot time : times){
                            timeSlotService.addTimeSlotSch(time);
                        }
                    }
                }else if(value==3){
                    if(schedule.getEnd()!=null){
                        List<TimeSlot> times= TimeSlotGeneration.everyMonth(Id,value,schedule.getStart(),schedule.getEnd());
                        for (TimeSlot time : times){
                            timeSlotService.addTimeSlotSch(time);
                        }
                    }else {
                        List<TimeSlot> times= TimeSlotGeneration.everyMonth(Id,value,schedule.getStart());
                        for (TimeSlot time : times){
                            timeSlotService.addTimeSlotSch(time);
                        }
                    }
                }else if(value==4){
                    if(schedule.getEnd()!=null){
                        List<TimeSlot> times= TimeSlotGeneration.everyYear(Id,value,schedule.getStart(),schedule.getEnd());
                        for (TimeSlot time : times){
                            timeSlotService.addTimeSlotSch(time);
                        }
                    }else {
                        List<TimeSlot> times= TimeSlotGeneration.everyYear(Id,value,schedule.getStart());
                        for (TimeSlot time : times){
                            timeSlotService.addTimeSlotSch(time);
                        }
                    }
                }

            }else  if(value>=5){
                periodService.deletPeriodSch(Id);
                Period period = new Period();
                period.setStart(schedule.getStart());
                ArrayList<String> weeks =new ArrayList<>();
                HashMap<String,Object> slot = (HashMap<String,Object>) timeSlot.get("slot");
                int num1 = (int)slot.get("num1");
                period.setNum1(num1);
                String week = "";
                weeks= (ArrayList<String>) slot.get("week1");
                for(int i = 0; i < weeks.size(); i++){
                    if(i<weeks.size()-1){
                        week+=weeks.get(i)+",";
                    }else {
                        week+=weeks.get(i);
                    }
                }
                period.setWeek(week);
                String radio = (String)slot.get("radio");
                period.setRadio(radio);
                if(radio.equals("2")){
                    String dateEnd = (String)slot.get("date3");
                    period.setDateEnd(dateEnd);
                }else if(radio.equals("3")){
                    int num2 = (int)slot.get("num2");
                    period.setNum2(num2);
                }
                period.seteId(Id);
                if(schedule.getEnd()!=null){
                    period.setEnd(schedule.getEnd());
                }
               periodService.addPeriodSch(period);
                List<TimeSlot> times= TimeSlotGeneration.ByPeriod(Id,value,period);
                for (TimeSlot time : times){
                    timeSlotService.addTimeSlotSch(time);
                }
            }
            return RespBean.ok("修改成功");
        }
        return  RespBean.error("修改失败");
    }
}
