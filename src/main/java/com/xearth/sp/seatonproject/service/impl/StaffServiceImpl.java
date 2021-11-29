package com.xearth.sp.seatonproject.service.impl;

import com.xearth.sp.seatonproject.controller.ExcelListener;
import com.xearth.sp.seatonproject.dao.StaffDao;
import com.xearth.sp.seatonproject.pojo.Staff;
import com.xearth.sp.seatonproject.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(Staff.class);

    @Autowired
    StaffDao staffDao;

    @PersistenceContext
    protected EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void saveBatch(List<Staff> list) {
        staffDao.saveAll(list);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void emInsertBatch(List<Staff> list) {
        try {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                entityManager.persist(list.get(i));
                if (i % 10 == 0 || i == (size - 1)) {
                    entityManager.flush();
                    entityManager.clear();
                }
            }
            LOGGER.info("入库成功,共 {}条数据", list.size());
        } catch (Exception e) {
            LOGGER.error("批量插入失败");
            e.printStackTrace();
        }
    }

    @Override
    public void jdbcInsertBatch(List<Staff> list) {
        int[] updatedCountArray = jdbcTemplate.batchUpdate("INSERT INTO staff (name,mobile) VALUES (?,?);", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, list.get(i).getName());
                preparedStatement.setString(2, list.get(i).getMobile());
            }
            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
        LOGGER.info("入库成功,共 {}条数据", list.size());
    }
}
