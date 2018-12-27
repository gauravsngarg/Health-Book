package com.gauravsngarg.healthbook.model;

public class Doctor {
    String name;
    String location;
    String timing;
    String days;

    public Doctor() {
    }

    public Doctor(String name, String location, String timing, String days) {
        this.name = name;
        this.location = location;
        this.timing = timing;
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
