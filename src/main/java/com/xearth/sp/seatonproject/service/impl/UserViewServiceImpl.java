package com.xearth.sp.seatonproject.service.impl;

import com.xearth.sp.seatonproject.dao.UserViewDao;
import com.xearth.sp.seatonproject.pojo.UserView;
import com.xearth.sp.seatonproject.service.UserViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserViewServiceImpl extends BaseServiceImpl<UserViewDao, UserView, Integer> implements UserViewService {

    @Autowired
    UserViewDao userViewDao;

    @Override
    public List<UserView> findUserInfo() {
        return userViewDao.findUserInfo();
    }
}
