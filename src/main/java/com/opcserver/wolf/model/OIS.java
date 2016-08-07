package com.opcserver.wolf.model;

import javax.persistence.*;
import java.util.Date;
import java.util.GregorianCalendar;

//Основная БД OIS в которую будет выгружатся инфа
@Entity
@Table(name = "OIS")
public class OIS {

    //Стандартные табличные данные
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "EventType")
    private int eventType;

    @Column(name = "ObjType")
    private int objType;

    @Column(name = "ObjID")
    private String objID;

    @Column(name = "PropType")
    private int propType;

    @Column(name = "TimeStamp")
    private GregorianCalendar timeStamp;

    @Column(name = "PropValue")
    private String propValue;

    @Column(name = "DateImport")
    private Date dateImport;

    //getter setter
    public OIS(int eventType, int objType, String objID, int propType, GregorianCalendar timeStamp, String propValue, Date dateImport) {
        this.eventType = eventType;
        this.objType = objType;
        this.objID = objID;
        this.propType = propType;
        this.timeStamp = timeStamp;
        this.propValue = propValue;
        this.dateImport = dateImport;
    }

    public OIS() {
    }

    public String getPropValue() {
        return propValue;
    }

    public void setPropValue(String propValue) {
        this.propValue = propValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public int getObjType() {
        return objType;
    }

    public void setObjType(int objType) {
        this.objType = objType;
    }

    public String getObjID() {
        return objID;
    }

    public void setObjID(String objID) {
        this.objID = objID;
    }

    public int getPropType() {
        return propType;
    }

    public void setPropType(int propType) {
        this.propType = propType;
    }

    public GregorianCalendar getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(GregorianCalendar timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Date getDateImport() {
        return dateImport;
    }

    public void setDateImport(Date dateImport) {
        this.dateImport = dateImport;
    }
}
