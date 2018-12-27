package com.gauravsngarg.healthbook.model;

public class Vaccine {
    String vName;

    public Vaccine() {
    }

    public Vaccine(String vName) {
        this.vName = vName;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }
}
