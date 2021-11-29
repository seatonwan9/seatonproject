package com.xearth.sp.seatonproject.dao;

import com.xearth.sp.seatonproject.pojo.Staff;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/**
 * @ClassName ExcelListener
 * @Description TODO
 * @Author wangxudong
 * @Date 2021/10/18 15:35
 * @Version 1.0
 **/
@Repository
public interface StaffDao extends JpaRepositoryImplementation<Staff, Integer> {

}
