package com.xearth.sp.seatonproject.service;

import com.xearth.sp.seatonproject.pojo.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    List<User> findAll();

    Page<User> findAllByPage(int page, int size);

    void save(User user);

    void saveAll(List<User> user);

    void batchInsert(List<User> user);

    void batchUpdate(List<User> user);

}
