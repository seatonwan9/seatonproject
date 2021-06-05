package com.xearth.sp.seatonproject.service;

import com.xearth.sp.seatonproject.pojo.User;
import com.xearth.sp.seatonproject.pojo.projection.UserProjection;

import java.util.List;

public interface UserService extends BaseService<User, Integer> {

    void batchInsert(List<User> user);

    void batchUpdate(List<User> user);

    List<UserProjection> findAllUser();

    List<User> findUsersByUserName(String userName, Integer page, Integer size);
}
