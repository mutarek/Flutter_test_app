package com.techtrickbd.nahidshop.models;

import com.google.firebase.Timestamp;

public class Free_Fire_Model {
    private String paymentNumber;
    private String gameid;
    private String pack;
    private String email;
    private String paymentTrancID;
    private Timestamp timestamp;
    private boolean status;
    private String parent;
    private String uid;

    public Free_Fire_Model() {
    }

    public Free_Fire_Model(String paymentNumber, String gameid, String pack, String email, String paymentTrancID, Timestamp timestamp, boolean status, String parent, String uid) {
        this.paymentNumber = paymentNumber;
        this.gameid = gameid;
        this.pack = pack;
        this.email = email;
        this.paymentTrancID = paymentTrancID;
        this.timestamp = timestamp;
        this.status = status;
        this.parent = parent;
        this.uid = uid;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaymentTrancID() {
        return paymentTrancID;
    }

    public void setPaymentTrancID(String paymentTrancID) {
        this.paymentTrancID = paymentTrancID;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
