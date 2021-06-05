package com.xearth.sp.seatonproject.service;

import com.xearth.sp.seatonproject.pojo.UserView;

import java.util.List;

public interface UserViewService extends BaseService<UserView, Integer> {

    List<UserView> findUserInfo();
}
