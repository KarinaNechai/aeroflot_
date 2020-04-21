package com.github.nechai.aeroflot.model;

public class Airport {
    private String name;
    private boolean isActual;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Airport(){
    }

    public Airport(int id,String name, boolean isActual) {
        this.name = name;
        this.isActual = isActual;
        this.id = id;
    }
    public Airport(int id,String name) {
        this.name = name;
        this.id = id;
        this.isActual = true;
    }

    public Airport(String name) {
        this.name = name;
        this.isActual=true;
    }
    public String getFullName (String name) {
        return this.name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport)) return false;
        Airport airport = (Airport) o;
        return isActual() == airport.isActual() &&
               name.equals(airport.getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActual() {
        return isActual;
    }

    public void setActual(boolean actual) {
        isActual = actual;
    }
}
