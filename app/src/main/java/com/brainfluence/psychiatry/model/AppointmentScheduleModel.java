package com.brainfluence.psychiatry.model;

public class AppointmentScheduleModel {
    public String name,time,date;

    public AppointmentScheduleModel() {
        this.name = null;
        this.time = null;
        this.date = null;
    }

    public AppointmentScheduleModel(String name, String time, String date) {
        this.name = name;
        this.time = time;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
