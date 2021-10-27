package com.xearth.sp.seatonproject.service.impl;

import com.xearth.sp.seatonproject.dao.StaffRepository;
import com.xearth.sp.seatonproject.pojo.Staff;
import com.xearth.sp.seatonproject.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName StaffServiceImpl
 * @Description TODO
 * @Author wangxudong
 * @Date 2021/10/18 15:39
 * @Version 1.0
 **/
@Transactional
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffRepository staffRepository;

    @Override
    public void saveBatch(List<Staff> list) {
        staffRepository.saveAll(list);
    }
}
