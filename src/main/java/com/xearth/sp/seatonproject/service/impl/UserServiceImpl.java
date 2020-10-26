package com.xearth.sp.seatonproject.service.impl;

import com.xearth.sp.seatonproject.dao.BaseRepository;
import com.xearth.sp.seatonproject.dao.UserMapper;
import com.xearth.sp.seatonproject.pojo.User;
import com.xearth.sp.seatonproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll(Sort.by(Sort.Direction.DESC,"datetime"));
    }

    @Override
    public Page<User> findAllByPage(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.DESC,"datetime"));
        return userMapper.findAll(pageable);
    }

    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    public void saveAll(List<User> user) {
        userMapper.saveAll(user);
    }

    @Override
    public void batchInsert(List<User> user) {
        userMapper.batchInsert(user);
    }

    @Override
    public void batchUpdate(List<User> user) {
        userMapper.batchUpdate(user);
    }

}
