package com.github.nechai.aeroflot.model;

public  class Page{

    private int pageNumber;
    public final static int RECORD_NUMBER=5;
    public Page(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getFirst() {
        return (pageNumber - 1) * getMax();
    }

    public int getMax() {
        return RECORD_NUMBER;
    }
}