package com.brainfluence.psychiatry.model;

public class DoctorRequestModel {
    public String patientName,patientAge,patientGender,patientUid,patientDetails,requestDate,phoneNumber;

    public DoctorRequestModel() {
        this.patientName = null;
        this.patientAge = null;
        this.patientGender = null;
        this.patientUid = null;
        this.patientDetails = null;
        this.requestDate = null;
        this.phoneNumber = null;
    }

    public DoctorRequestModel(String patientName, String patientAge, String patientGender, String patientUid, String patientDetails, String requestDate, String phoneNumber) {
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientGender = patientGender;
        this.patientUid = patientUid;
        this.patientDetails = patientDetails;
        this.requestDate = requestDate;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientUid() {
        return patientUid;
    }

    public void setPatientUid(String patientUid) {
        this.patientUid = patientUid;
    }

    public String getPatientDetails() {
        return patientDetails;
    }

    public void setPatientDetails(String patientDetails) {
        this.patientDetails = patientDetails;
    }
}
