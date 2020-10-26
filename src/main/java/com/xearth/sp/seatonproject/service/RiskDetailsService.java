package com.xearth.sp.seatonproject.service;

import com.xearth.sp.seatonproject.pojo.RiskDetails;
import java.util.List;

public interface RiskDetailsService {

    List<RiskDetails> findAllByRiskId(String riskId);

    void save(RiskDetails riskDetails);

    void deleteByRiskId(String riskId);
}
