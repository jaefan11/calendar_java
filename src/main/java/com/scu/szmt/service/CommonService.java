package com.scu.szmt.service;

import com.scu.szmt.dao.Conference;
import com.scu.szmt.dao.Route;
import com.scu.szmt.dao.Schedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommonService {
    public void addSubscribe(int uId,int subId);
    public int[] findSubById(int id);
    public List<Conference> findConference(@Param("start") String start, @Param("end") String end);
    public List<Route> findRoute(@Param("id") int id, @Param("start") String start, @Param("end") String end);
    public List<Schedule> findSchedule(@Param("id") int id, @Param("start") String start, @Param("end") String end);
}
