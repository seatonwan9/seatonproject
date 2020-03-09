package com.xearth.sp.seatonproject.service.impl;

import com.xearth.sp.seatonproject.dao.UserMapper;
import com.xearth.sp.seatonproject.pojo.User;
import com.xearth.sp.seatonproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll(Sort.by(Sort.Direction.DESC,"datatime"));
    }

    @Override
    public Page<User> findAllByPage(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.DESC,"datatime"));
        return userMapper.findAll(pageable);
    }

}
