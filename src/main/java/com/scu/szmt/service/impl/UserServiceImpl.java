package com.scu.szmt.service.impl;

import com.scu.szmt.dao.User;
import com.scu.szmt.mapper.UserMapper;
import com.scu.szmt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public User findById(int id) {
        return userMapper.findById(id);
    }

    @Override
    public List<User> findAllExcId(int id) {
        return userMapper.findAllExcId(id);
    }

    @Override
    public List<User> findPersonByConId(int id) {
        return userMapper.findPersonByConId(id);
    }

    @Override
    public List<User> findPersonByRid(int id) {
        return userMapper.findPersonByRid(id);
    }

    @Override
    public List<User> findPersonBySid(int id) {
        return userMapper.findPersonBySid(id);
    }


}
