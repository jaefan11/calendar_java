package com.scu.szmt.service.impl;

import com.scu.szmt.dao.Route;
import com.scu.szmt.dao.TimeSlot;
import com.scu.szmt.mapper.RouteMapper;
import com.scu.szmt.service.RouteService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    RouteMapper routeMapper;
    @Override
    public List<Route> findRouteByUserId(@Param("id")int id, @Param("start") String start, @Param("end")String end) {
        return routeMapper.findRouteByUserId(id,start,end);
    }

    @Override
    public int addRoute(Route route) {
        return routeMapper.addRoute(route);
    }

    @Override
    public void addPersons(int rId, int userId) {
        routeMapper.addPersons(rId,userId);
    }

    @Override
    public void addUserRou(int uId, int rId) {
        routeMapper.addUserRou(uId,rId);
    }

    @Override
    public void deletRouteById(int id) {
        routeMapper.deletRouteById(id);
    }

    @Override
    public int updateRouteById(Route route) {
        return routeMapper.updateRouteById(route);
    }

    @Override
    public void deletPersonsRouByRid(int rId) {
        routeMapper.deletPersonsRouByRid(rId);
    }

    @Override
    public List<Route> searchRou(String content, String person, String place, String start, String end, int id) {
        return routeMapper.searchRou(content, person, place, start, end, id);
    }


}
