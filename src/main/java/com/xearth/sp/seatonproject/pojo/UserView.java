package com.xearth.sp.seatonproject.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 视图UserInfo
 */
@Entity
public class UserView implements Serializable {

    @Id

    private String userName;
    private Integer userAge;
    private String userPhone;
    private String companyName;
    private String companyAddress;

    public UserView() {

    }

    public String getUserName() {
        return userName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", userPhone='" + userPhone + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                '}';
    }
}
