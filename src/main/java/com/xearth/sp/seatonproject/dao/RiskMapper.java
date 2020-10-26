package com.xearth.sp.seatonproject.dao;

import com.xearth.sp.seatonproject.pojo.Risk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RiskMapper extends JpaRepository<Risk,Integer> {

    List<Risk> findAllById(String id);

    List<Risk> findAllByParentId(String parentId);

    void deleteById(String id);

    void deleteByParentId(String id);

    @Modifying
    @Query("update Risk r set r.riskDesc=?1, r.score=?2, r.datetime=?3 where r.id=?4")
    void updateRiskById(String riskDesc, Integer score, Date datetime, String id);
}