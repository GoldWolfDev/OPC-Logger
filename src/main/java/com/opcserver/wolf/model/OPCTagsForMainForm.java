package com.opcserver.wolf.model;

/**
 * Created by Artur on 15.07.2016.
 */
public class OPCTagsForMainForm {

    private String opcServerName;

    private String address;

    private Number countTags;

    private double timerCicle;

    private boolean ServerStatus;

    public OPCTagsForMainForm(){}


    public OPCTagsForMainForm(String opcServer, String serverName, Number count, double timerCicle, boolean i) {
        this.opcServerName = opcServer;
        this.address = serverName;
        this.countTags = count;
        this.timerCicle = timerCicle;
        ServerStatus = i;
    }

    public String getOpcServerName() {
        return opcServerName;
    }

    public void setOpcServerName(String opcServerName) {
        this.opcServerName = opcServerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Number getCountTags() {
        return countTags;
    }

    public void setCountTags(Number countTags) {
        this.countTags = countTags;
    }

    public double getTimerCicle() {
        return timerCicle;
    }

    public void setTimerCicle(double timerCicle) {
        this.timerCicle = timerCicle;
    }

    public boolean getServerStatus() {
        return ServerStatus;
    }

    public void setServerStatus(boolean serverStatus) {
        ServerStatus = serverStatus;
    }
}
