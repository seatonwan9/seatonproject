package com.xearth.sp.seatonproject.service.impl;

import com.xearth.sp.seatonproject.dao.UserDao;
import com.xearth.sp.seatonproject.pojo.User;
import com.xearth.sp.seatonproject.pojo.projection.UserProjection;
import com.xearth.sp.seatonproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User, Integer> implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public void batchInsert(List<User> user) {
        userDao.batchInsert(user);
    }

    @Override
    public void batchUpdate(List<User> user) {
        userDao.batchUpdate(user);
    }

    @Override
    public List<UserProjection> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public List<User> findUsersByUserName(String userName, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return userDao.findUsersByUserName(userName, pageable);
    }

}
