package com.xearth.sp.seatonproject.service.impl;

import com.xearth.sp.seatonproject.dao.RiskMapper;
import com.xearth.sp.seatonproject.pojo.Risk;
import com.xearth.sp.seatonproject.service.RiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class RiskServiceImpl implements RiskService {
    @Autowired
    RiskMapper riskMapper;

    @Override
    public List<Risk> findAllById(String id) {
        return riskMapper.findAllById(id);
    }

    @Override
    public List<Risk> findAllByParentId(String parentId) {
        return riskMapper.findAllByParentId(parentId);
    }

    @Override
    public void save(Risk risk) {
        riskMapper.save(risk);
    }

    @Override
    public void deleteById(String id) {
        riskMapper.deleteById(id);
    }

    @Override
    public void deleteByParentId(String id) {
        riskMapper.deleteByParentId(id);
    }

    @Override
    public void updateRiskById(String riskDesc, Integer score, Date datetime, String id) {
        riskMapper.updateRiskById(riskDesc, score, datetime, id);
    }
}
