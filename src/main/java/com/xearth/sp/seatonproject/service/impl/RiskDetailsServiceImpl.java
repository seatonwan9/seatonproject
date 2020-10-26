package com.xearth.sp.seatonproject.service.impl;

import com.xearth.sp.seatonproject.dao.RiskDetailsMapper;
import com.xearth.sp.seatonproject.pojo.RiskDetails;
import com.xearth.sp.seatonproject.service.RiskDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RiskDetailsServiceImpl implements RiskDetailsService {
    @Autowired
    RiskDetailsMapper riskDetailsMapper;

    @Override
    public List<RiskDetails> findAllByRiskId(String riskId) {
        return riskDetailsMapper.findAllByRiskId(riskId);
    }

    @Override
    public void save(RiskDetails riskDetails) {
        riskDetailsMapper.save(riskDetails);
    }

    @Override
    public void deleteByRiskId(String riskId) {
        riskDetailsMapper.deleteByRiskId(riskId);
    }
}
