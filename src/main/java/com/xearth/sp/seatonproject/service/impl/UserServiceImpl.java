package com.xearth.sp.seatonproject.service.impl;

import com.xearth.sp.seatonproject.dao.UserMapper;
import com.xearth.sp.seatonproject.pojo.User;
import com.xearth.sp.seatonproject.service.UserService;
import com.xearth.sp.seatonproject.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User, Integer> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void batchInsert(List<User> user) {
        userMapper.batchInsert(user);
    }

    @Override
    public void batchUpdate(List<User> user) {
        userMapper.batchUpdate(user);
    }

}
