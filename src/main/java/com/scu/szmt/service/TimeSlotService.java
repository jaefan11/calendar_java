package com.scu.szmt.service;

import com.scu.szmt.dao.TimeSlot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TimeSlotService {
    public void addTimeSlotCon(TimeSlot timeSlot);
    public void addTimeSlotRou(TimeSlot timeSlot);
    public void addTimeSlotSch(TimeSlot timeSlot);
    public List<TimeSlot> findTimeSlotCon(@Param("cid") int cid, @Param("start") String start, @Param("end") String end);
    public List<TimeSlot> findTimeSlotRou(@Param("rid") int rid,@Param("start") String start, @Param("end") String end);
    public List<TimeSlot> findTimeSlotSch(@Param("sid") int sid,@Param("start") String start, @Param("end") String end);
    public void deletTimeSlotCon(int cid);
    public void deletTimeSlotRou(int rid);
    public void deletTimeSlotSch(int sid);
}
