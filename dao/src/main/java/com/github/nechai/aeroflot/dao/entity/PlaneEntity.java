package com.github.nechai.aeroflot.dao.entity;

import javax.persistence.*;

@Entity
@Table(name = "plane")
public class PlaneEntity {
    private int id;
    private String planeName;
    private int capacity;//вместимость
    private int range;//дальность полета',
    private int actFl;

    public PlaneEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planeid")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "planename")
    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    @Column(name = "capacity")
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Column(name = "length")
    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
    @Column(name = "actfl")
    public int getActFl() {
        return actFl;
    }

    public void setActFl(int actual) {
        this.actFl = actual;
    }
}
