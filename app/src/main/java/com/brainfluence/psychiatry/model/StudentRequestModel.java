package com.brainfluence.psychiatry.model;

public class StudentRequestModel {
    public String studentName,studentId,studentDept,studentUniName,studentDetails,requestDate,studentPhoneNumber,studentUid,token;


    public StudentRequestModel() {
        this.studentName = null;
        this.studentId = null;
        this.studentDept = null;
        this.studentUniName = null;
        this.studentDetails = null;
        this.requestDate = null;
        this.studentPhoneNumber = null;
        this.studentUid = null;
        this.token = null;
    }

    public StudentRequestModel(String studentName, String studentId, String studentDept, String studentUniName, String studentDetails, String requestDate, String studentPhoneNumber, String studentUid,String token) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.studentDept = studentDept;
        this.studentUniName = studentUniName;
        this.studentDetails = studentDetails;
        this.requestDate = requestDate;
        this.studentPhoneNumber = studentPhoneNumber;
        this.studentUid = studentUid;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentDept() {
        return studentDept;
    }

    public void setStudentDept(String studentDept) {
        this.studentDept = studentDept;
    }

    public String getStudentUniName() {
        return studentUniName;
    }

    public void setStudentUniName(String studentUniName) {
        this.studentUniName = studentUniName;
    }

    public String getStudentDetails() {
        return studentDetails;
    }

    public void setStudentDetails(String studentDetails) {
        this.studentDetails = studentDetails;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getStudentPhoneNumber() {
        return studentPhoneNumber;
    }

    public void setStudentPhoneNumber(String studentPhoneNumber) {
        this.studentPhoneNumber = studentPhoneNumber;
    }

    public String getStudentUid() {
        return studentUid;
    }

    public void setStudentUid(String studentUid) {
        this.studentUid = studentUid;
    }
}
