package com.xearth.sp.seatonproject.dao;

import com.xearth.sp.seatonproject.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface UserMapper extends BaseRepository<User, Integer> {

}