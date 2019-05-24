package com.scu.szmt.service.impl;

import com.scu.szmt.dao.Conference;
import com.scu.szmt.dao.Route;
import com.scu.szmt.dao.Schedule;
import com.scu.szmt.mapper.CommonMapper;
import com.scu.szmt.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    CommonMapper commonMapper;
    @Override
    public void addSubscribe(int uId, int subId) {
        commonMapper.addSubscribe(uId,subId);
    }

    @Override
    public int[] findSubById(int id) {
        return commonMapper.findSubById(id);
    }

    @Override
    public List<Conference> findConference(String start, String end) {
        return commonMapper.findConference(start,end);
    }

    @Override
    public List<Route> findRoute(int id, String start, String end) {
        return commonMapper.findRoute(id,start,end);
    }

    @Override
    public List<Schedule> findSchedule(int id, String start, String end) {
        return commonMapper.findSchedule(id, start, end);
    }
}
