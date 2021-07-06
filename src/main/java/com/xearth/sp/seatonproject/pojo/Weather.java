package com.xearth.sp.seatonproject.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "weather")
public class Weather implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 企业编码
    @Column(name = "code")
    private String code;

    // 降雨量
    @Column(name = "rainfall")
    private String rainfall;

    // 时间段
    @Column(name = "timeslot")
    private String timeslot;

    // 创建时间
    @Column(name = "creatime")
    private Date creatime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRainfall() {
        return rainfall;
    }

    public void setRainfall(String rainfall) {
        this.rainfall = rainfall;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public Date getCreatime() {
        return creatime;
    }

    public void setCreatime(Date creatime) {
        this.creatime = creatime;
    }


    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", rainfall='" + rainfall + '\'' +
                ", timeslot='" + timeslot + '\'' +
                ", creatime=" + creatime +
                '}';
    }
}
