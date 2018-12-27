package com.gauravsngarg.healthbook.model;

import java.util.List;

public class Prescription {
    String url;
    String doctorId;
    String date;
    List<String> medicineID;

    public Prescription() {
    }

    public Prescription(String url, String doctorId, String date, List<String> medicineID) {
        this.url = url;
        this.doctorId = doctorId;
        this.date = date;
        this.medicineID = medicineID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(List<String> medicineID) {
        this.medicineID = medicineID;
    }
}
