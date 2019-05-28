package com.ferinadanin.projectuas.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Pasien {
    public int id;
    public String name, alamat, telp, username, password, level;

    public Pasien(String name, String alamat, String telp, String username, String password, String level) {
        this.name = name;
        this.alamat = alamat;
        this.telp = telp;
        this.username = username;
        this.password = password;
        this.level = level;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("alamat", alamat);
        result.put("telp", telp);
        result.put("username", username);
        result.put("password", password);
        return result;
    }

    public Pasien(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
