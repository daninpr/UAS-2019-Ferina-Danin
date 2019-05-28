package com.ferinadanin.projectuas.Model;

public class Resep {
    private int id;
    private String namaobat;
    private String jumlah;

    public Resep(int id, String namaobat, String jumlah) {
        this.id = id;
        this.namaobat = namaobat;
        this.jumlah = jumlah;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaobat() {
        return namaobat;
    }

    public void setNamaobat(String namaobat) {
        this.namaobat = namaobat;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
}
