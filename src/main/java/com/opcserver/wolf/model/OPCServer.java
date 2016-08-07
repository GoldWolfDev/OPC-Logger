package com.opcserver.wolf.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//БД в которой хранится вся информация о Серверах OIS
@Entity
@Table(name = "OPCServer")
public class OPCServer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //Имя сервера(Lectus.DA.2)
    @Column(name = "serverName")
    private String serverName;

    //адресс сервера(localhost or 192.168.0.100)
    @Column(name = "opcServer")
    private String opcServer;

    //имя регистрастрации
    @Column(name = "clientNameForOPC")
    private String clientNameForOPC;

    //имя группы
    @Column(name = "NameGroup")
    private String nameGroup;

    //время опроса сервера
    @Column(name = "TimeQuestian")
    private int time;

    //опрашивать сервер или нет(1-да,0-нет)
    @Column(name = "ServerStatus")
    private int ServerStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "OPCServerID")
    Set<BaseOPC> opc = new HashSet<BaseOPC>();


    //getter setter
    public int getId() {
        return id;
    }

    public int isServerStatus() {
        return ServerStatus;
    }

    public void setServerStatus(int serverStatus) {
        ServerStatus = serverStatus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getOpcServer() {
        return opcServer;
    }

    public void setOpcServer(String opcServer) {
        this.opcServer = opcServer;
    }

    public String getClientNameForOPC() {
        return clientNameForOPC;
    }

    public void setClientNameForOPC(String clientNameForOPC) {
        this.clientNameForOPC = clientNameForOPC;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Set<BaseOPC> getOpc() {
        return opc;
    }

    public void setOpc(Set<BaseOPC> opc) {
        this.opc = opc;
    }

    public int getServerStatus() {
        return ServerStatus;
    }
}
