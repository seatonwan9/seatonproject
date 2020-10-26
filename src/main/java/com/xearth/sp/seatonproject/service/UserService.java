package com.xearth.sp.seatonproject.service;

import com.xearth.sp.seatonproject.pojo.User;
import com.xearth.sp.seatonproject.base.BaseService;

import java.util.List;

public interface UserService extends BaseService<User, Integer> {

    void batchInsert(List<User> user);

    void batchUpdate(List<User> user);

}
