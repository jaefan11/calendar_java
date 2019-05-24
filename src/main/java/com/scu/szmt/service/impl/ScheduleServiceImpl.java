package com.scu.szmt.service.impl;

import com.scu.szmt.dao.Schedule;
import com.scu.szmt.dao.TimeSlot;
import com.scu.szmt.mapper.ScheduleMapper;
import com.scu.szmt.service.ScheduleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleMapper scheduleMapper;
    @Override
    public List<Schedule> findScheduleByUserId(@Param("id")int id, @Param("start") String start, @Param("end")String end) {
        return scheduleMapper.findScheduleByUserId(id,start,end);
    }

    @Override
    public int addSchedule(Schedule schedule) {
        return scheduleMapper.addSchedule(schedule);
    }

    @Override
    public void addPersons(int sId, int userId) {
        scheduleMapper.addPersons(sId,userId);
    }

    @Override
    public void addUserSch(int uId, int sId) {
        scheduleMapper.addUserSch(uId,sId);
    }

    @Override
    public void deletScheduleById(int id) {
        scheduleMapper.deletScheduleById(id);
    }

    @Override
    public int updateScheduleById(Schedule schedule) {
        return scheduleMapper.updateScheduleById(schedule);
    }

    @Override
    public void deletPersonsSchBySid(int sId) {
        scheduleMapper.deletPersonsSchBySid(sId);
    }

    @Override
    public List<Schedule> searchSch(String content, String person, String place, String start, String end, int id) {
        return scheduleMapper.searchSch(content, person, place, start, end, id);
    }


}
