package com.scu.szmt.dao;

public class Schedule {
    private int id;
    private String title;
    private String start;
    private String end;
    private boolean allDay;
    private String place;
    private String content;
    private int creId;
    private boolean timeSlot;


    public boolean isTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(boolean timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCreId() {
        return creId;
    }

    public void setCreId(int creId) {
        this.creId = creId;
    }
}
