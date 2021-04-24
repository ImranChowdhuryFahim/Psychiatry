package com.brainfluence.psychiatry.model;

public class TeacherModel {
    public String name,email,universityName,department,position,dsw,phoneNumber,password,uid,fToken;

    public TeacherModel() {
        this.name = null;
        this.email = null;
        this.universityName = null;
        this.department = null;
        this.position = null;
        this.dsw = null;
        this.phoneNumber = null;
        this.password = null;
        this.uid = null;
        this.fToken = null;
    }

    public TeacherModel(String name, String email, String universityName, String department, String position, String dsw, String phoneNumber, String password, String uid, String fToken) {
        this.name = name;
        this.email = email;
        this.universityName = universityName;
        this.department = department;
        this.position = position;
        this.dsw = dsw;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.uid = uid;
        this.fToken = fToken;
    }

    public String getfToken() {
        return fToken;
    }

    public void setfToken(String fToken) {
        this.fToken = fToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDsw() {
        return dsw;
    }

    public void setDsw(String dsw) {
        this.dsw = dsw;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
