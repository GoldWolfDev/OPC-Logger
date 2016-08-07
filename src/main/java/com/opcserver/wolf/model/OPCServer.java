package com.opcserver.wolf.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//�� � ������� �������� ��� ���������� � �������� OIS
@Entity
@Table(name = "OPCServer")
public class OPCServer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //��� �������(Lectus.DA.2)
    @Column(name = "serverName")
    private String serverName;

    //������ �������(localhost or 192.168.0.100)
    @Column(name = "opcServer")
    private String opcServer;

    //��� ���������������
    @Column(name = "clientNameForOPC")
    private String clientNameForOPC;

    //��� ������
    @Column(name = "NameGroup")
    private String nameGroup;

    //����� ������ �������
    @Column(name = "TimeQuestian")
    private int time;

    //���������� ������ ��� ���(1-��,0-���)
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
