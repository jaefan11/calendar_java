package com.scu.szmt.service;

import com.scu.szmt.dao.User;

import java.util.List;

public interface UserService {
    public User findByUsername(String username);
    public User findById(int id);
    public List<User> findAllExcId(int id);
    public List<User> findPersonByConId(int id);
    public List<User>  findPersonByRid(int id);
    public List<User>  findPersonBySid(int id);
}
