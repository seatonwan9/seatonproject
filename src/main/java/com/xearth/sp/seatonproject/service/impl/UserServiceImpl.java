package com.xearth.sp.seatonproject.service.impl;

import com.xearth.sp.seatonproject.dao.StaffDao;
import com.xearth.sp.seatonproject.dao.UserDao;
import com.xearth.sp.seatonproject.pojo.Staff;
import com.xearth.sp.seatonproject.pojo.User;
import com.xearth.sp.seatonproject.pojo.projection.UserProjection;
import com.xearth.sp.seatonproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User, Integer> implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    StaffDao staffRepository;

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
    public Page<User> findUsersByParamByPageable(String userName, Integer page, Integer size) {
        page = page == null || page <= 0 ? 0 : page -1;
        size = size == null ? 5 : size;
        Pageable pageable = PageRequest.of(page, size);
        return userDao.findUsersByParamByPageable(userName, pageable);
    }
}
