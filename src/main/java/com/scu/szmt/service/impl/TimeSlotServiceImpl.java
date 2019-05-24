package com.scu.szmt.service.impl;

import com.scu.szmt.dao.TimeSlot;
import com.scu.szmt.mapper.TimeSlotMapper;
import com.scu.szmt.service.TimeSlotService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {
    @Autowired
    TimeSlotMapper timeSlotMapper;
    @Override
    public void addTimeSlotCon(TimeSlot timeSlot) {
        timeSlotMapper.addTimeSlotCon(timeSlot);
    }

    @Override
    public void addTimeSlotRou(TimeSlot timeSlot) {
        timeSlotMapper.addTimeSlotRou(timeSlot);
    }

    @Override
    public void addTimeSlotSch(TimeSlot timeSlot) {
        timeSlotMapper.addTimeSlotSch(timeSlot);
    }

    @Override
    public List<TimeSlot> findTimeSlotCon(@Param("cid")int cid, @Param("start") String start, @Param("end") String end) {
        return timeSlotMapper.findTimeSlotCon(cid, start, end);
    }

    @Override
    public List<TimeSlot> findTimeSlotRou(@Param("rid")int rid, @Param("start") String start, @Param("end") String end) {
        return timeSlotMapper.findTimeSlotRou(rid, start, end);
    }

    @Override
    public List<TimeSlot> findTimeSlotSch(@Param("sid")int sid, @Param("start") String start, @Param("end") String end) {
        return timeSlotMapper.findTimeSlotSch(sid,start,end);
    }

    @Override
    public void deletTimeSlotCon(int cid) {
        timeSlotMapper.deletTimeSlotCon(cid);
    }

    @Override
    public void deletTimeSlotRou(int rid) {
        timeSlotMapper.deletTimeSlotRou(rid);
    }

    @Override
    public void deletTimeSlotSch(int sid) {
        timeSlotMapper.deletTimeSlotSch(sid);
    }

}
