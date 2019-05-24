package com.scu.szmt.dao;

public class TimeSlot {
    private int id;
    private int eId;
    private String start;
    private String end;
    private int value;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int geteId() {
        return eId;
    }

    public void seteId(int eId) {
        this.eId = eId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
