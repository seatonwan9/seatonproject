package com.xearth.sp.seatonproject.service;

import com.xearth.sp.seatonproject.pojo.Risk;

import java.util.Date;
import java.util.List;

public interface RiskService {

    List<Risk> findAllById(String id);

    List<Risk> findAllByParentId(String parentId);

    void save(Risk risk);

    void deleteById(String id);

    void deleteByParentId(String id);

    void updateRiskById(String riskDesc, Integer score, Date datetime, String id);
}
