package com.github.nechai.aeroflot.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "airport")
public class AirportEntity {
    private String name;
    private int actFl;
    private int id;

    public AirportEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airportid")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "airportname")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "actfl")
    public int getActFl() {
        return actFl;
    }

    public void setActFl(int actual) {
        this.actFl = actual;
    }
}
