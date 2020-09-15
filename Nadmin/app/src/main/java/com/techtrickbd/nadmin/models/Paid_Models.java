package com.techtrickbd.nadmin.models;

import com.google.firebase.Timestamp;

public class Paid_Models {
    private String id;
    private String paymentNumber;
    private String gameid;
    private String pack;
    private String email;
    private String paymentTrancID;
    private Timestamp timestamp;
    private boolean status;
    private String parent;
    private String server;
    private boolean ps;

    public Paid_Models() {
    }

    public Paid_Models(String id, String paymentNumber, String gameid, String pack, String email, String paymentTrancID, Timestamp timestamp, boolean status, String parent, String server, boolean ps) {
        this.id = id;
        this.paymentNumber = paymentNumber;
        this.gameid = gameid;
        this.pack = pack;
        this.email = email;
        this.paymentTrancID = paymentTrancID;
        this.timestamp = timestamp;
        this.status = status;
        this.parent = parent;
        this.server = server;
        this.ps = ps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public boolean isPs() {
        return ps;
    }

    public void setPs(boolean ps) {
        this.ps = ps;
    }
}
