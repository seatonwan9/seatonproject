package com.xearth.sp.seatonproject.pojo;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private Integer companyId;

    private String userName;

    private Integer userAge;

    private String userPhone;

    public User() {
    }

    public User(Integer userId, Integer companyId, String userName, Integer userAge, String userPhone) {
        this.userId = userId;
        this.companyId = companyId;
        this.userName = userName;
        this.userAge = userAge;
        this.userPhone = userPhone;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", companyId=" + companyId +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }
}