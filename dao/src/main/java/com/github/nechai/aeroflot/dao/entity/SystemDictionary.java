package com.github.nechai.aeroflot.dao.entity;

import javax.persistence.*;

@MappedSuperclass
public class SystemDictionary {

    private Integer id;
    private String code;
    private String value;
    private int actFl;

    public SystemDictionary() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clasid")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "clascode")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @Column(name = "clasval")
    public String getvalue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    @Column(name = "actfl")
    public int getActFl() {
        return actFl;
    }

    public void setActFl(int actual) {
        this.actFl = actual;
    }
}
