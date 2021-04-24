package com.brainfluence.psychiatry.model;

public class StudentModel {
    public String name,email,studentId,universityName,department,phoneNumber,dob,gender,password,uid;

    public StudentModel()
    {
        this.name = null;
        this.email = null;
        this.studentId = null;
        this.universityName = null;
        this.department = null;
        this.phoneNumber = null;
        this.dob = null;
        this.gender = null;
        this.password = null;
        this.uid = null;
    }

    public StudentModel(String name, String email, String studentId, String universityName, String department, String phoneNumber, String dob, String gender, String password,String uid) {
        this.name = name;
        this.email = email;
        this.studentId = studentId;
        this.universityName = universityName;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.gender = gender;
        this.password = password;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
