package com.github.nechai.aeroflot.model;

import java.util.Date;

public class Check {
    private String userLogin;
    private TypeOfCheck typeOfCheck;
    private Date date;
    private float sum;
    private String text;
    private int number;
    private boolean isActual;

    public Check(String userLogin, TypeOfCheck typeOfCheck, Date date, float sum, String text) {
        this.userLogin = userLogin;
        this.typeOfCheck = typeOfCheck;
        this.date = date;
        this.sum = sum;
        this.text = text;
        this.isActual=true;
    }

    public boolean getIsActual() {
        return isActual;
    }

    public void setActual(boolean actual) {
        isActual = actual;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public TypeOfCheck getTypeOfCheck() {
        return typeOfCheck;
    }

    public Date getDate() {
        return date;
    }

    public float getSum() {
        return sum;
    }

    public String getText() {
        return text;
    }

    public int getNumber() {
        return number;
    }
}
