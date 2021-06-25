package com.xearth.sp.seatonproject.pojo;

import javax.persistence.*;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_address")
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