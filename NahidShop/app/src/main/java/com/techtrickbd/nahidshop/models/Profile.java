package com.techtrickbd.nahidshop.models;

public class Profile {
    private String name;
    private String email;
    private Integer coin;
    private String uid;
    private Integer tk;

    public Profile() {
    }

    public Profile(String name, String email, Integer coin, String uid, Integer tk) {
        this.name = name;
        this.email = email;
        this.coin = coin;
        this.uid = uid;
        this.tk = tk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getTk() {
        return tk;
    }

    public void setTk(Integer tk) {
        this.tk = tk;
    }
}
