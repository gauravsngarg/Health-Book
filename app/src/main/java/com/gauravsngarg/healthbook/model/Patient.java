package com.gauravsngarg.healthbook.model;

import java.util.List;

public class Patient {
    int age;
    String bloodGroup;
   // Medicine disease;
    String dob;
    String patientName;
    String phsID;

    public Patient() {
    }

    public Patient(int age, String bloodGroup, String dob, String patientName, String phsID) {
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.dob = dob;
        this.patientName = patientName;
        this.phsID = phsID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPhsID() {
        return phsID;
    }

    public void setPhsID(String phsID) {
        this.phsID = phsID;
    }
}
