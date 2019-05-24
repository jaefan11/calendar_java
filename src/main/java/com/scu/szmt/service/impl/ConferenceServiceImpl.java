package com.scu.szmt.service.impl;

import com.scu.szmt.dao.Conference;
import com.scu.szmt.dao.TimeSlot;
import com.scu.szmt.mapper.ConferenceMapper;
import com.scu.szmt.service.ConferenceService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceServiceImpl implements ConferenceService {
    @Autowired
    ConferenceMapper conferenceMapper;
    @Override
    public List<Conference> findConfeByUserId(@Param("id")int id, @Param("start") String start, @Param("end")String end){
        return conferenceMapper.findConfeByUserId(id,start,end);
    }
    public int addConference(Conference conference) {
        return conferenceMapper.addConference(conference);
    }

    @Override
    public void addPersons(int conId, int userId) {
        conferenceMapper.addPersons(conId,userId);
    }

    @Override
    public void addUserCon(int uId, int cId) {
        conferenceMapper.addUserCon(uId,cId);
    }

    @Override
    public void addFileCon(int fId, int cId) {
        conferenceMapper.addFileCon(fId,cId);
    }

    @Override
    public void deletConfernceById(int id) {
        conferenceMapper.deletConfernceById(id);
    }

    @Override
    public int updateConferenceById(Conference conference) {
        return conferenceMapper.updateConferenceById(conference);
    }

    @Override
    public void deletPersonsConByCid(int Cid) {
        conferenceMapper.deletPersonsConByCid(Cid);
    }

    @Override
    public List<Conference> searchCon(String content, String person, String place, String start, String end, int id) {
        return conferenceMapper.searchCon(content, person, place, start, end, id);
    }


}
