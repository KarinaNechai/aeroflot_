package com.github.nechai.aeroflot.model;

import java.util.Objects;

public class Plane {
    private int planeId;
    private String planeName;
    private int capacity;//вместимость
    private int range;//дальность полета',
    private boolean isActual;

    public Plane(String newPlaneName, int newCapasity, int newRange) {
        this.planeName = newPlaneName;
        this.capacity = newCapasity;
        this.range = newRange;
        this.isActual = true;
    }


    public Plane() {
    }

    public Plane(int planeId, String planeName, int capacity, int range, boolean isActual) {
        this.planeId = planeId;
        this.planeName = planeName;
        this.capacity = capacity;
        this.range = range;
        this.isActual = isActual;
    }

    public Plane(String planeName) {
        this.planeName = planeName;
        this.isActual =true;
    }

    public int getPlaneId() {
        return planeId;
    }

    public void setPlaneId(int planeId) {
        this.planeId = planeId;
    }

    public String getPlaneName() {
        return planeName;
    }

    public void setPlaneName(String planeName) {
        this.planeName = planeName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public boolean isActual() {
        return isActual;
    }

    public void setActual(boolean actual) {
        isActual = actual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane)) return false;
        Plane plane = (Plane) o;
        return getPlaneId() == plane.getPlaneId() &&
                getCapacity() == plane.getCapacity() &&
                getRange() == plane.getRange() &&
                isActual() == plane.isActual() &&
                getPlaneName().equals(plane.getPlaneName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlaneId(), getPlaneName(), getCapacity(), getRange(), isActual());
    }
}
