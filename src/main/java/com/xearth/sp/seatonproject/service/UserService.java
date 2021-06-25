package com.xearth.sp.seatonproject.service;

import com.xearth.sp.seatonproject.pojo.User;
import com.xearth.sp.seatonproject.pojo.projection.UserProjection;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService extends BaseService<User, Integer> {

    void batchInsert(List<User> user);

    void batchUpdate(List<User> user);

    List<UserProjection> findAllUser();

    Page<User> findUsersByParamByPageable(String userName, Integer page, Integer size);
}
