package com.scu.szmt.mapper;

import com.scu.szmt.dao.Route;
import com.scu.szmt.dao.TimeSlot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RouteMapper {
    public List<Route> findRouteByUserId(@Param("id")int id, @Param("start") String start, @Param("end")String end);
    public int addRoute(Route route);
    public void addPersons(int rId,int userId);
    public void addUserRou(int uId,int rId);
    public void deletRouteById(int id);
    public int updateRouteById(Route route);
    public void deletPersonsRouByRid(int rId);
    public List<Route> searchRou(@Param("content")String content,@Param("person")String person,@Param("place")String place,@Param("start")String start,@Param("end")String end,@Param("id")int id);

}

