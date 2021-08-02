package com.xearth.sp.seatonproject.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "company")
public class Company implements Serializable {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_address")
    private Integer companyAddress;

    @JsonIgnore
    @OneToMany(targetEntity = User.class, fetch=FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Set<User> users = new HashSet<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", companyAddress=" + companyAddress +
                '}';
    }
}