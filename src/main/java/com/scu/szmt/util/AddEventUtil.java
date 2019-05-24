package com.scu.szmt.util;

import com.scu.szmt.dao.*;
import com.scu.szmt.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class AddEventUtil {
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
    public static AddEventUtil addEventUtil;

    @PostConstruct
    public void init() {
        addEventUtil = this;
    }


    public static boolean addConference(Map<String,Object> reqMap) throws ParseException {
        Conference conference = new Conference();
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
        if (reqMap.get("allDay").toString().equals("true") ) {
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
        HashMap<String,Object> timeSlot = (HashMap<String,Object>)reqMap.get("timeSlot");
        int value=(int) timeSlot.get("value");
        if(value==1){
            conference.setTimeSlot(false);
        }else {
            conference.setTimeSlot(true);
        }
        conference.setCreId(Integer.valueOf(reqMap.get("creId").toString()));
        addEventUtil.conferenceService.addConference(conference);
        int conId = conference.getId();
        int uId = conference.getCreId();
        addEventUtil.conferenceService.addUserCon(uId, conId);
        if(value>1 && value<5){
            if(value==2){
                if(conference.getEnd()!=null){
                   List<TimeSlot> times= TimeSlotGeneration.everyDay(conId,value,conference.getStart(),conference.getEnd());
                   for (TimeSlot time : times){
                       addEventUtil.timeSlotService.addTimeSlotCon(time);
                   }
                }else {
                    List<TimeSlot> times= TimeSlotGeneration.everyDay(conId,value,conference.getStart());
                    for (TimeSlot time : times){
                        addEventUtil.timeSlotService.addTimeSlotCon(time);
                    }
                }
            }else if(value==3){
                if(conference.getEnd()!=null){
                    List<TimeSlot> times= TimeSlotGeneration.everyMonth(conId,value,conference.getStart(),conference.getEnd());
                    for (TimeSlot time : times){
                        addEventUtil.timeSlotService.addTimeSlotCon(time);
                    }
                }else {
                    List<TimeSlot> times= TimeSlotGeneration.everyMonth(conId,value,conference.getStart());
                    for (TimeSlot time : times){
                        addEventUtil.timeSlotService.addTimeSlotCon(time);
                    }
                }
            }else if(value==4){
                if(conference.getEnd()!=null){
                    List<TimeSlot> times= TimeSlotGeneration.everyYear(conId,value,conference.getStart(),conference.getEnd());
                    for (TimeSlot time : times){
                        addEventUtil.timeSlotService.addTimeSlotCon(time);
                    }
                }else {
                    List<TimeSlot> times= TimeSlotGeneration.everyYear(conId,value,conference.getStart());
                    for (TimeSlot time : times){
                        addEventUtil.timeSlotService.addTimeSlotCon(time);
                    }
                }
            }

        }else  if(value>=5){
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
            period.seteId(conId);
            if(conference.getEnd()!=null){
                period.setEnd(conference.getEnd());
            }
            addEventUtil.periodService.addPeriodCon(period);
            List<TimeSlot> times= TimeSlotGeneration.ByPeriod(conId,value,period);
            for (TimeSlot time : times){
                addEventUtil.timeSlotService.addTimeSlotCon(time);
            }
        }
        if (reqMap.containsKey("persons")) {
            ArrayList<Integer> list = (ArrayList<Integer>) reqMap.get("persons");
            for (Integer userId : list) {
                System.out.println("user" + userId);
                addEventUtil.conferenceService.addPersons(conId, userId);
                addEventUtil.conferenceService.addUserCon(userId,conId);
                addEventUtil.noteService.addNote(userId,conId);
            }
        }
        if (reqMap.containsKey("fileList")){
            ArrayList<Map<String,Object>> fileList = (ArrayList<Map<String,Object>>)reqMap.get("fileList");
            for (Map<String,Object> file : fileList){
                int fId = Integer.valueOf(file.get("id").toString());
                addEventUtil.conferenceService.addFileCon(fId,conId);
            }
        }
        return true;
    }

    public static boolean addRoute(Map<String,Object> reqMap) throws ParseException {
        Route route = new Route();
        if (reqMap.containsKey("title")) {
            route.setTitle(reqMap.get("title").toString());
            System.out.println(reqMap.get("title").toString());
        }
        if (reqMap.containsKey("start")) {
            route.setStart(reqMap.get("start").toString());
        }
        if (reqMap.containsKey("end")) {
            route.setEnd(reqMap.get("end").toString());
        }
        if (reqMap.get("allDay").toString().equals("true") ) {
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
        HashMap<String,Object> timeSlot = (HashMap<String,Object>)reqMap.get("timeSlot");
        int value=(int) timeSlot.get("value");
        if(value==1){
            route.setTimeSlot(false);
        }else {
            route.setTimeSlot(true);
        }
        route.setCreId(Integer.valueOf(reqMap.get("creId").toString()));
        addEventUtil.routeService.addRoute(route);
        int rId = route.getId();
        int uId = route.getCreId();
        addEventUtil.routeService.addUserRou(uId, rId);
        if(value>1 && value<5){
            if(value==2){
                if(route.getEnd()!=null){
                    List<TimeSlot> times= TimeSlotGeneration.everyDay(rId,value,route.getStart(),route.getEnd());
                    for (TimeSlot time : times){
                        addEventUtil.timeSlotService.addTimeSlotRou(time);
                    }
                }else {
                    List<TimeSlot> times= TimeSlotGeneration.everyDay(rId,value,route.getStart());
                    for (TimeSlot time : times){
                        addEventUtil.timeSlotService.addTimeSlotRou(time);
                    }
                }
            }else if(value==3){
                if(route.getEnd()!=null){
                    List<TimeSlot> times= TimeSlotGeneration.everyMonth(rId,value,route.getStart(),route.getEnd());
                    for (TimeSlot time : times){
                        addEventUtil.timeSlotService.addTimeSlotRou(time);
                    }
                }else {
                    List<TimeSlot> times= TimeSlotGeneration.everyMonth(rId,value,route.getStart());
                    for (TimeSlot time : times){
                        addEventUtil.timeSlotService.addTimeSlotRou(time);
                    }
                }
            }else if(value==4){
                if(route.getEnd()!=null){
                    List<TimeSlot> times= TimeSlotGeneration.everyYear(rId,value,route.getStart(),route.getEnd());
                    for (TimeSlot time : times){
                        addEventUtil.timeSlotService.addTimeSlotRou(time);
                    }
                }else {
                    List<TimeSlot> times= TimeSlotGeneration.everyYear(rId,value,route.getStart());
                    for (TimeSlot time : times){
                        addEventUtil.timeSlotService.addTimeSlotRou(time);
                    }
                }
            }

        }else  if(value>=5){
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
            period.seteId(rId);
            if(route.getEnd()!=null){
                period.setEnd(route.getEnd());
            }
            addEventUtil.periodService.addPeriodRou(period);
            List<TimeSlot> times= TimeSlotGeneration.ByPeriod(rId,value,period);
            for (TimeSlot time : times){
                addEventUtil.timeSlotService.addTimeSlotRou(time);
            }
        }
        if (reqMap.containsKey("persons")) {
            ArrayList<Integer> list = (ArrayList<Integer>) reqMap.get("persons");
            for (Integer userId : list) {
                System.out.println("user" + userId);
                addEventUtil.routeService.addPersons(rId, userId);
                addEventUtil.routeService.addUserRou(userId,rId);
            }
        }
        return true;
    }

    public static boolean addSchedule(Map<String,Object> reqMap) throws ParseException {
        Schedule schedule = new Schedule();
        if (reqMap.containsKey("title")) {
            schedule.setTitle(reqMap.get("title").toString());
            System.out.println(reqMap.get("title").toString());
        }
        if (reqMap.containsKey("start")) {
            schedule.setStart(reqMap.get("start").toString());
        }
        if (reqMap.containsKey("end")) {
            schedule.setEnd(reqMap.get("end").toString());
        }
        if (reqMap.get("allDay").toString().equals("true") ) {
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
        HashMap<String,Object> timeSlot = (HashMap<String,Object>)reqMap.get("timeSlot");
        int value=(int) timeSlot.get("value");
        if(value==1){
            schedule.setTimeSlot(false);
        }else {
            schedule.setTimeSlot(true);
        }
        schedule.setCreId(Integer.valueOf(reqMap.get("creId").toString()));
        addEventUtil.scheduleService.addSchedule(schedule);
        int sId = schedule.getId();
        int uId = schedule.getCreId();
        addEventUtil.scheduleService.addUserSch(uId, sId);
        if(value>1 && value<5){
            if(value==2){
                if(schedule.getEnd()!=null){
                    List<TimeSlot> times= TimeSlotGeneration.everyDay(sId,value,schedule.getStart(),schedule.getEnd());
                    for (TimeSlot time : times){
                        addEventUtil.timeSlotService.addTimeSlotSch(time);
                    }
                }else {
                    List<TimeSlot> times= TimeSlotGeneration.everyDay(sId,value,schedule.getStart());
                    for (TimeSlot time : times){
                        addEventUtil.timeSlotService.addTimeSlotSch(time);
                    }
                }
            }else if(value==3){
                if(schedule.getEnd()!=null){
                    List<TimeSlot> times= TimeSlotGeneration.everyMonth(sId,value,schedule.getStart(),schedule.getEnd());
                    for (TimeSlot time : times){
                        addEventUtil.timeSlotService.addTimeSlotSch(time);
                    }
                }else {
                    List<TimeSlot> times= TimeSlotGeneration.everyMonth(sId,value,schedule.getStart());
                    for (TimeSlot time : times){
                        addEventUtil.timeSlotService.addTimeSlotSch(time);
                    }
                }
            }else if(value==4){
                if(schedule.getEnd()!=null){
                    List<TimeSlot> times= TimeSlotGeneration.everyYear(sId,value,schedule.getStart(),schedule.getEnd());
                    for (TimeSlot time : times){
                        addEventUtil.timeSlotService.addTimeSlotSch(time);
                    }
                }else {
                    List<TimeSlot> times= TimeSlotGeneration.everyYear(sId,value,schedule.getStart());
                    for (TimeSlot time : times){
                        addEventUtil.timeSlotService.addTimeSlotSch(time);
                    }
                }
            }

        }else  if(value>=5){
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
            period.seteId(sId);
            if(schedule.getEnd()!=null){
                period.setEnd(schedule.getEnd());
            }
            addEventUtil.periodService.addPeriodSch(period);
            List<TimeSlot> times= TimeSlotGeneration.ByPeriod(sId,value,period);
            for (TimeSlot time : times){
                addEventUtil.timeSlotService.addTimeSlotSch(time);
            }
        }
        if (reqMap.containsKey("persons")) {
            ArrayList<Integer> list = (ArrayList<Integer>) reqMap.get("persons");
            for (Integer userId : list) {
                System.out.println("user" + userId);
                addEventUtil.scheduleService.addPersons(sId, userId);
                addEventUtil.scheduleService.addUserSch(userId,sId);
            }
        }
        return true;
    }
}
