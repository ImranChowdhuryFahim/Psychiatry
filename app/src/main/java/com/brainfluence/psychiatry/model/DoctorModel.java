package com.brainfluence.psychiatry.model;


public class DoctorModel {

    private String name,email,phoneNum,pass,degree,exp,uid,fToken;

    public DoctorModel() {
        this.name = null;
        this.email = null;
        this.phoneNum = null;
        this.pass = null;
        this.degree = null;
        this.exp = null;
        this.uid = null;
        this.fToken = null;
    }

    public DoctorModel(String name, String email, String phoneNum, String pass, String degree, String exp, String uid, String fToken) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.pass = pass;
        this.degree = degree;
        this.exp = exp;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
