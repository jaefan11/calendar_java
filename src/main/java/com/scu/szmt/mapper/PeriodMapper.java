package com.scu.szmt.mapper;

import com.scu.szmt.dao.Period;

public interface PeriodMapper {
    public void addPeriodCon(Period period);
    public void addPeriodRou(Period period);
    public void addPeriodSch(Period period);
    public Period findPeriodCon(int cid);
    public Period findPeriodRou(int rid);
    public Period findPeriodSch(int sid);
    public void deletPeriodCon(int cid);
    public void deletPeriodRou(int rid);
    public void deletPeriodSch(int sid);
}
