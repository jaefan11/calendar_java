package com.scu.szmt.service.impl;

import com.scu.szmt.dao.Period;
import com.scu.szmt.mapper.PeriodMapper;
import com.scu.szmt.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeriodServiceImpl implements PeriodService {
    @Autowired
    PeriodMapper periodMapper;
    @Override
    public void addPeriodCon(Period period){
        periodMapper.addPeriodCon(period);
    }

    @Override
    public void addPeriodRou(Period period) {
        periodMapper.addPeriodRou(period);
    }

    @Override
    public void addPeriodSch(Period period) {
        periodMapper.addPeriodSch(period);
    }

    @Override
    public Period findPeriodCon(int cid) {
        return periodMapper.findPeriodCon(cid);
    }

    @Override
    public Period findPeriodRou(int rid) {
        return periodMapper.findPeriodRou(rid);
    }

    @Override
    public Period findPeriodSch(int sid) {
        return periodMapper.findPeriodSch(sid);
    }

    @Override
    public void deletPeriodCon(int cid) {
        periodMapper.deletPeriodCon(cid);
    }

    @Override
    public void deletPeriodRou(int rid) {
        periodMapper.deletPeriodRou(rid);
    }

    @Override
    public void deletPeriodSch(int sid) {
        periodMapper.deletPeriodSch(sid);
    }


}
