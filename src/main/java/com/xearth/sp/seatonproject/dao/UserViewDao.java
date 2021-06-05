package com.xearth.sp.seatonproject.dao;

import com.xearth.sp.seatonproject.pojo.UserView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserViewDao extends JpaRepositoryImplementation<UserView, Integer> {

    @Query(value = "select * from user_info", nativeQuery = true)
    List<UserView> findUserInfo();
}
