package com.xearth.sp.seatonproject.service;

import com.xearth.sp.seatonproject.pojo.Staff;

import java.util.List;

/**
 * @ClassName ExcelListener
 * @Description TODO
 * @Author wangxudong
 * @Date 2021/10/18 15:37
 * @Version 1.0
 **/
public interface StaffService {

    void saveBatch(List<Staff> list);

    void emInsertBatch(List<Staff> list);

    void jdbcInsertBatch(List<Staff> list);
}
