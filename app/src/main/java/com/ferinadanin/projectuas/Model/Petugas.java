package com.ferinadanin.projectuas.Model;

public class Petugas {
    private int id;
    private String name;
    private String username;
    private String password;
    private String telp;
    private String usr;
    private String level;

    public Petugas(String name, String telp, String username, String password, String level) {
        this.telp = telp;
        this.name = name;
        this.username = username;
        this.password = password;
        this.level = level;
    }

    public Petugas(){
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
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

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
