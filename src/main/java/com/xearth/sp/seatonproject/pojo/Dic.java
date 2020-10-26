package com.xearth.sp.seatonproject.pojo;

import javax.persistence.*;

@Entity
@Table(name = "dic")
public class Dic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer parentId;

    private Integer code;

    private String type;

    private String text;

    private String desc;

    public Dic(Integer id, Integer parentId, Integer code, String type, String text, String desc) {
        this.id = id;
        this.parentId = parentId;
        this.code = code;
        this.type = type;
        this.text = text;
        this.desc = desc;
    }

    public Dic() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    @Override
    public String toString() {
        return "Dic{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", code=" + code +
                ", type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}