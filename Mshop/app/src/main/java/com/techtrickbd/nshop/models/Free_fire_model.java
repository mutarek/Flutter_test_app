package com.techtrickbd.nshop.models;

public class Free_fire_model {
    private int plan;
    private String uid;

    public Free_fire_model() {
    }

    public Free_fire_model(int plan, String uid) {
        this.plan = plan;
        this.uid = uid;
    }

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
