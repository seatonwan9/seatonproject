package com.xearth.sp.seatonproject.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName Staff
 * @Description TODO
 * @Author wangxudong
 * @Date 2021/10/18 15:22
 * @Version 1.0
 **/
@Entity
@Data
@Table(name="staff")
public class Staff {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="number")
    private String number;

    @Column(name="title")
    private String title;

    @Column(name="mobile")
    private String mobile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
