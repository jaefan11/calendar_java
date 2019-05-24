package com.scu.szmt.util;

import com.scu.szmt.dao.Period;
import com.scu.szmt.dao.TimeSlot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TimeSlotGeneration {
    public static List<TimeSlot> everyDay(int eId,int value,String... args) throws ParseException {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        calendarStart.setTime(sdf.parse(args[0]));
        List<TimeSlot> timeSlot = new ArrayList<TimeSlot>();
        if(args.length==1){
            for(int i=0;i<=90;i++){
                if(i==0){
                    TimeSlot timeSlot1 = new TimeSlot();
                    timeSlot1.seteId(eId);
                    timeSlot1.setValue(value);
                    timeSlot1.setStart(args[0]);
                    timeSlot.add(timeSlot1);
                }else {
                    TimeSlot timeSlot1 = new TimeSlot();
                    timeSlot1.seteId(eId);
                    timeSlot1.setValue(value);
                    calendarStart.add(Calendar.DATE,1);
                    timeSlot1.setStart(sdf.format(calendarStart.getTime()));
                    timeSlot.add(timeSlot1);
                }


            }
        }else {
            calendarEnd.setTime(sdf.parse(args[1]));
            for(int i=0;i<=90;i++){
                if(i==0){
                    TimeSlot timeSlot1 = new TimeSlot();
                    timeSlot1.seteId(eId);
                    timeSlot1.setValue(value);
                    timeSlot1.setStart(args[0]);
                    timeSlot1.setEnd(args[1]);
                    timeSlot.add(timeSlot1);
                }else {
                    TimeSlot timeSlot1 = new TimeSlot();
                    timeSlot1.seteId(eId);
                    timeSlot1.setValue(value);
                    calendarStart.add(Calendar.DATE,1);
                    timeSlot1.setStart(sdf.format(calendarStart.getTime()));
                    calendarEnd.add(Calendar.DATE,1);
                    timeSlot1.setEnd(sdf.format(calendarEnd.getTime()));
                    timeSlot.add(timeSlot1);
                }
            }
        }
        return timeSlot;
    }
    public static List<TimeSlot> everyMonth(int eId,int value,String... args) throws ParseException {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        calendarStart.setTime(sdf.parse(args[0]));
        List<TimeSlot> timeSlot = new ArrayList<TimeSlot>();
        if(args.length==1){
            for(int i=0;i<=12;i++){
                if(i==0){
                    TimeSlot timeSlot1 = new TimeSlot();
                    timeSlot1.seteId(eId);
                    timeSlot1.setValue(value);
                    timeSlot1.setStart(args[0]);
                    timeSlot.add(timeSlot1);
                }else {
                    TimeSlot timeSlot1 = new TimeSlot();
                    timeSlot1.seteId(eId);
                    timeSlot1.setValue(value);
                    calendarStart.add(Calendar.MONTH,1);
                    timeSlot1.setStart(sdf.format(calendarStart.getTime()));
                    timeSlot.add(timeSlot1);
                }


            }
        }else {
            calendarEnd.setTime(sdf.parse(args[1]));
            for(int i=0;i<=12;i++){
                if(i==0){
                    TimeSlot timeSlot1 = new TimeSlot();
                    timeSlot1.seteId(eId);
                    timeSlot1.setValue(value);
                    timeSlot1.setStart(args[0]);
                    timeSlot1.setEnd(args[1]);
                    timeSlot.add(timeSlot1);
                }else {
                    TimeSlot timeSlot1 = new TimeSlot();
                    timeSlot1.seteId(eId);
                    timeSlot1.setValue(value);
                    calendarStart.add(Calendar.MONTH,1);
                    timeSlot1.setStart(sdf.format(calendarStart.getTime()));
                    calendarEnd.add(Calendar.MONTH,1);
                    timeSlot1.setEnd(sdf.format(calendarEnd.getTime()));
                    timeSlot.add(timeSlot1);
                }
            }
        }
        return timeSlot;
    }
    public static List<TimeSlot> everyYear(int eId,int value,String... args) throws ParseException {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        calendarStart.setTime(sdf.parse(args[0]));
        List<TimeSlot> timeSlot = new ArrayList<TimeSlot>();
        if(args.length==1){
            for(int i=0;i<=6;i++){
                if(i==0){
                    TimeSlot timeSlot1 = new TimeSlot();
                    timeSlot1.seteId(eId);
                    timeSlot1.setValue(value);
                    timeSlot1.setStart(args[0]);
                    timeSlot.add(timeSlot1);
                }else {
                    TimeSlot timeSlot1 = new TimeSlot();
                    timeSlot1.seteId(eId);
                    timeSlot1.setValue(value);
                    calendarStart.add(Calendar.YEAR,1);
                    timeSlot1.setStart(sdf.format(calendarStart.getTime()));
                    timeSlot.add(timeSlot1);
                }


            }
        }else {
            calendarEnd.setTime(sdf.parse(args[1]));
            for(int i=0;i<=12;i++){
                if(i==0){
                    TimeSlot timeSlot1 = new TimeSlot();
                    timeSlot1.seteId(eId);
                    timeSlot1.setValue(value);
                    timeSlot1.setStart(args[0]);
                    timeSlot1.setEnd(args[1]);
                    timeSlot.add(timeSlot1);
                }else {
                    TimeSlot timeSlot1 = new TimeSlot();
                    timeSlot1.seteId(eId);
                    timeSlot1.setValue(value);
                    calendarStart.add(Calendar.YEAR,1);
                    timeSlot1.setStart(sdf.format(calendarStart.getTime()));
                    calendarEnd.add(Calendar.YEAR,1);
                    timeSlot1.setEnd(sdf.format(calendarEnd.getTime()));
                    timeSlot.add(timeSlot1);
                }
            }
        }
        return timeSlot;
    }

    public static List<TimeSlot> ByPeriod(int eId,int value,Period period) throws ParseException{
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        calendarStart.setTime(sdf.parse(period.getStart()));
        List<TimeSlot> timeSlot = new ArrayList<TimeSlot>();
        if (period.getEnd()!=null){;
            calendarEnd.setTime(sdf1.parse(period.getEnd()));
        }
        if(period.getRadio().equals("1")){
            for(int i=0;i<=60;i++) {
                for (String w : period.getWeek().split(",")) {
                    TimeSlot timeSlot1 = new TimeSlot();
                    timeSlot1.seteId(eId);
                    timeSlot1.setValue(value);
                    int m = getWeekIndex(w);
                    calendarStart.set(Calendar.DAY_OF_WEEK,m);
                    timeSlot1.setStart(sdf.format(calendarStart.getTime()));
                    if(period.getEnd()!=null){
                        calendarEnd.set(Calendar.DAY_OF_WEEK,m);
                        timeSlot1.setEnd(sdf.format(calendarEnd.getTime()));
                    }
                    timeSlot.add(timeSlot1);
                }
                calendarStart.add(Calendar.DATE,7*period.getNum1());
                if(period.getEnd()!=null){
                    calendarEnd.add(Calendar.DATE,7*period.getNum1());
                }
            }
        }else if(period.getRadio().equals("2")){
            Calendar end = Calendar.getInstance();
            end.setTime(sdf1.parse(period.getDateEnd()));
            while (calendarStart.before(end)){
                for (String w : period.getWeek().split(",")) {
                    TimeSlot timeSlot1 = new TimeSlot();
                    timeSlot1.seteId(eId);
                    timeSlot1.setValue(value);
                    int m = getWeekIndex(w);
                    calendarStart.set(Calendar.DAY_OF_WEEK,m);
                    timeSlot1.setStart(sdf.format(calendarStart.getTime()));
                    if(period.getEnd()!=null){
                        calendarEnd.set(Calendar.DAY_OF_WEEK,m);
                        timeSlot1.setEnd(sdf.format(calendarEnd.getTime()));
                    }
                    timeSlot.add(timeSlot1);
                }
                calendarStart.add(Calendar.DATE,7*period.getNum1());
                if(period.getEnd()!=null){
                    calendarEnd.add(Calendar.DATE,7*period.getNum1());
                }
            }
        }else if(period.getRadio().equals("3")){
            for (int i=0;i<period.getNum2();i++){
                for (String w : period.getWeek().split(",")) {
                    TimeSlot timeSlot1 = new TimeSlot();
                    timeSlot1.seteId(eId);
                    timeSlot1.setValue(value);
                    int m = getWeekIndex(w);
                    calendarStart.set(Calendar.DAY_OF_WEEK,m);
                    timeSlot1.setStart(sdf.format(calendarStart.getTime()));
                    if(period.getEnd()!=null){
                        calendarEnd.set(Calendar.DAY_OF_WEEK,m);
                        timeSlot1.setEnd(sdf.format(calendarEnd.getTime()));
                    }
                    timeSlot.add(timeSlot1);
                }
                calendarStart.add(Calendar.DATE,7*period.getNum1());
                if(period.getEnd()!=null){
                    calendarEnd.add(Calendar.DATE,7*period.getNum1());
                }
            }
        }
        return timeSlot;
    }
    public static int getWeekIndex(String week){
        int m=0;
        switch(week) {
            case "周日": {
                m = 1;
                break;
            }
            case "周一": {
                m = 2;
                break;
            }
            case "周二": {
                m = 3;
                break;
            }
            case "周三": {
                m = 4;
                break;
            }
            case "周四": {
                m=5;
                break;
            }
            case "周五": {
                m = 6;
                break;
            }
            case "周六": {
                m = 7;
                break;
            }
        }
        return  m;
    }
}
