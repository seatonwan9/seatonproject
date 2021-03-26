package com.xearth.sp.seatonproject.dao;

import com.xearth.sp.seatonproject.pojo.RiskDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RiskDetailsMapper extends JpaRepositoryImplementation<RiskDetails,Integer> {

    List<RiskDetails> findAllByRiskId(String riskId);

    void deleteByRiskId(String riskId);
}