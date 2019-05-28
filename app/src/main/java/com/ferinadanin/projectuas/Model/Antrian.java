package com.ferinadanin.projectuas.Model;

public class Antrian {
    private int id;
    private String waktuantri;
    private Boolean status;

    public Antrian(int id, String waktuantri, Boolean status) {
        this.id = id;
        this.waktuantri = waktuantri;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWaktuantri() {
        return waktuantri;
    }

    public void setWaktuantri(String waktuantri) {
        this.waktuantri = waktuantri;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
