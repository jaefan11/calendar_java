package com.scu.szmt.mapper;

import com.scu.szmt.dao.Conference;
import com.scu.szmt.dao.Route;
import com.scu.szmt.dao.Schedule;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface CommonMapper {
    public void addSubscribe(int uId,int subId);
    public int[] findSubById(int id);
    public List<Conference> findConference(@Param("start") String start,@Param("end") String end);
    public List<Route> findRoute(@Param("id") int id,@Param("start") String start, @Param("end") String end);
    public List<Schedule> findSchedule(@Param("id") int id, @Param("start") String start, @Param("end") String end);
}
