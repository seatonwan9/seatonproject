package com.xearth.sp.seatonproject.pojo;

import javax.persistence.*;

@Entity
@Table(name = "company")
public class Company {
    @Id
    private Integer companyId;

    private String companyName;

    private Integer companyAddress;

    public Company() {
    }

    public Company(Integer companyId, String companyName, Integer companyAddress) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(Integer companyAddress) {
        this.companyAddress = companyAddress;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", companyAddress=" + companyAddress +
                '}';
    }
}