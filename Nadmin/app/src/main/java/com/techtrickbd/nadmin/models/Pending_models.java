package com.techtrickbd.nadmin.models;

import com.google.firebase.Timestamp;

public class Pending_models {
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

    public Pending_models() {
    }

    public Pending_models(String paymentNumber, String gameid, String pack, String email, String paymentTrancID, Timestamp timestamp, boolean status, String parent, String server, boolean ps) {
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

    public String getGameid() {
        return gameid;
    }

    public String getPack() {
        return pack;
    }

    public String getEmail() {
        return email;
    }

    public String getPaymentTrancID() {
        return paymentTrancID;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public boolean isStatus() {
        return status;
    }

    public String getParent() {
        return parent;
    }

    public String getServer() {
        return server;
    }

    public boolean isPs() {
        return ps;
    }
}
