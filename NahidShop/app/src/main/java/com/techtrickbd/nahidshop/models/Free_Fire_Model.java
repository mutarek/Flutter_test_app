package com.techtrickbd.nahidshop.models;

import com.google.firebase.Timestamp;

public class Free_Fire_Model {
    private String gameid;
    private String pack;
    private String method;
    private String paymentNumber;
    private String paymentTrancID;
    private String userEmail;
    private Timestamp timestamp;
    private boolean status;

    public Free_Fire_Model() {
    }

    public Free_Fire_Model(String gameid, String pack, String method, String paymentNumber, String paymentTrancID, String userEmail, Timestamp timestamp, boolean status) {
        this.gameid = gameid;
        this.pack = pack;
        this.method = method;
        this.paymentNumber = paymentNumber;
        this.paymentTrancID = paymentTrancID;
        this.userEmail = userEmail;
        this.timestamp = timestamp;
        this.status = status;
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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public String getPaymentTrancID() {
        return paymentTrancID;
    }

    public void setPaymentTrancID(String paymentTrancID) {
        this.paymentTrancID = paymentTrancID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
}
