package com.gauravsngarg.healthbook.model;

import java.util.List;

class Prescription {
    String url;
    String doctorName;
    String date;
    List<Medicine> prescMedicines;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Medicine> getPrescMedicines() {
        return prescMedicines;
    }

    public void setPrescMedicines(List<Medicine> prescMedicines) {
        this.prescMedicines = prescMedicines;
    }
}
