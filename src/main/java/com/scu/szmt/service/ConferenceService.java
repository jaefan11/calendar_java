package com.scu.szmt.service;

import com.scu.szmt.dao.Conference;
import com.scu.szmt.dao.TimeSlot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConferenceService {
    public List<Conference> findConfeByUserId(@Param("id")int id, @Param("start") String start, @Param("end")String end);
    public int addConference(Conference conference);
    public void addPersons(int conId,int userId);
    public void addUserCon(int uId,int cId);
    public void addFileCon(int fId,int cId);
    public void deletConfernceById(int id);
    public int updateConferenceById(Conference conference);
    public void deletPersonsConByCid(int Cid);
    public List<Conference> searchCon(@Param("content")String content,@Param("person")String person,@Param("place")String place,@Param("start")String start,@Param("end")String end,@Param("id")int id);
}
