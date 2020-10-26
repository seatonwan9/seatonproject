package com.xearth.sp.seatonproject.pojo;

import java.util.List;

public class RiskMap {
    public Risk risk;
    public List<RiskDetails> riskDetails;

    public Risk getRisk() {
        return risk;
    }

    public void setRisk(Risk risk) {
        this.risk = risk;
    }

    public List<RiskDetails> getRiskDetails() {
        return riskDetails;
    }

    public void setRiskDetails(List<RiskDetails> riskDetails) {
        this.riskDetails = riskDetails;
    }
}
