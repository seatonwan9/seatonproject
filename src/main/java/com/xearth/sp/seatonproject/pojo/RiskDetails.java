package com.xearth.sp.seatonproject.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "risk_details")
public class RiskDetails {
    @Id
    private String id;

    private String riskId;

    private String type;

    private Integer detailCode;

    private String detailDesc;

    private Integer score;

    private Integer isFull;

    private Date datetime;

    public RiskDetails(String id, String riskId, String type, Integer detailCode, String detailDesc, Integer score, Integer isFull, Date datetime) {
        this.id = id;
        this.riskId = riskId;
        this.type = type;
        this.detailCode = detailCode;
        this.detailDesc = detailDesc;
        this.score = score;
        this.isFull = isFull;
        this.datetime = datetime;
    }

    public RiskDetails() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id  == null ? null : id.trim();
    }

    public String getRiskId() {
        return riskId;
    }

    public void setRiskId(String riskId) {
        this.riskId = riskId  == null ? null : riskId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(Integer detailCode) {
        this.detailCode = detailCode;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc == null ? null : detailDesc.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getIsFull() {
        return isFull;
    }

    public void setIsFull(Integer isFull) {
        this.isFull = isFull;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "RiskDetails{" +
                "id=" + id +
                ", riskId=" + riskId +
                ", type='" + type + '\'' +
                ", detailCode=" + detailCode +
                ", detailDesc='" + detailDesc + '\'' +
                ", score=" + score +
                ", isFull=" + isFull +
                ", datetime=" + datetime +
                '}';
    }
}