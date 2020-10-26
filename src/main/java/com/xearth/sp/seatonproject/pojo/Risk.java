package com.xearth.sp.seatonproject.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "risk")
public class Risk {
    @Id
    private String id;

    private String parentId;

    private String riskName;

    private String riskDesc;

    private Integer score;

    private String target;

    private String geometry;

    private Date datetime;

    public Risk(String id, String parentId, String riskName, String riskDesc, Integer score, String target, String geometry, Date datetime) {
        this.id = id;
        this.parentId = parentId;
        this.riskName = riskName;
        this.riskDesc = riskDesc;
        this.score = score;
        this.target = target;
        this.geometry = geometry;
        this.datetime = datetime;
    }

    public Risk() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId  == null ? null : parentId.trim();
    }

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName == null ? null : riskName.trim();
    }

    public String getRiskDesc() {
        return riskDesc;
    }

    public void setRiskDesc(String riskDesc) {
        this.riskDesc = riskDesc == null ? null : riskDesc.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry == null ? null : geometry.trim();
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "Risk{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", riskName='" + riskName + '\'' +
                ", riskDesc='" + riskDesc + '\'' +
                ", score=" + score +
                ", target='" + target + '\'' +
                ", geometry='" + geometry + '\'' +
                ", datetime=" + datetime +
                '}';
    }
}