package com.scu.szmt.service;

import com.scu.szmt.dao.Schedule;
import com.scu.szmt.dao.TimeSlot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScheduleService {
    public List<Schedule> findScheduleByUserId(@Param("id")int id, @Param("start") String start, @Param("end")String end);
    public int addSchedule(Schedule schedule);
    public void addPersons(int sId,int userId);
    public void addUserSch(int uId,int sId);
    public void deletScheduleById(int id);
    public int updateScheduleById(Schedule schedule);
    public void deletPersonsSchBySid(int sId);
    public List<Schedule> searchSch(@Param("content")String content,@Param("person")String person,@Param("place")String place,@Param("start")String start,@Param("end")String end,@Param("id")int id);
}
