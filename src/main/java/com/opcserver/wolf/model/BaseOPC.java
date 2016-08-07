package com.opcserver.wolf.model;


import javax.persistence.*;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@Table(name = "BaseOPC")
public class BaseOPC {

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
    private String itemValue;

    @Column(name = "DateImport")
    private Date dateImport;

    //Доп. параметры

    //Наименование тега c OPC server(E1.level)
    @Column(name = "ItemName")
    private String itemName;

    //внешний ключ -> id for OPC server
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opc")
    private OPCServer OPCServerID;

    //getter setter
    public GregorianCalendar getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(GregorianCalendar timeStamp) {
        this.timeStamp = timeStamp;
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

    public Date getDateImport() {
        return dateImport;
    }

    public void setDateImport(Date dateImport) {
        this.dateImport = dateImport;
    }

    public OPCServer getOPCServerID() {
        return OPCServerID;
    }

    public void setOPCServerID(OPCServer OPCServerID) {
        this.OPCServerID = OPCServerID;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
