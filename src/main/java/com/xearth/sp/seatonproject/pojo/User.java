package com.xearth.sp.seatonproject.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer age;

    private Date datetime;

    public User(Integer id, String name, Integer age, Date datetime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.datetime = datetime;
    }

    public User() {
        super();
    }

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
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getdatetime() {
        return datetime;
    }

    public void setdatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", datetime=" + datetime +
                '}';
    }

}