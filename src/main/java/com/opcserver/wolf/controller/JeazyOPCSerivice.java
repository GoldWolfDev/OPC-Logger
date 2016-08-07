package com.opcserver.wolf.controller;

import com.opcserver.wolf.model.BaseOPC;
import com.opcserver.wolf.model.OIS;
import com.opcserver.wolf.model.OPCServer;
import com.opcserver.wolf.tools.HibernetSession;
import javafish.clients.opc.JOpc;
import javafish.clients.opc.component.OpcGroup;
import javafish.clients.opc.component.OpcItem;
import javafish.clients.opc.exception.*;
import org.apache.log4j.Logger;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JeazyOPCSerivice implements Runnable{

    private final static Logger loger = Logger.getLogger(JeazyOPCSerivice.class);

    private static boolean working = true;

    private List<BaseOPC> list = null;
    private OPCServer server = null;    
    private int time;
    private int status;

    public JeazyOPCSerivice() {
    }

    public JeazyOPCSerivice(List<BaseOPC> list, OPCServer server, int time, int status) {
        this.list = list;
        this.server = server;
        this.time = time;
        this.status = status;
    }

    //основной метод записи данных в БД(уже готовые данные)
    private void saveOPCDateToBase(ArrayList<OpcItem> opcGroups, List<BaseOPC> opcList) {
        for (OpcItem item : opcGroups){
            for (BaseOPC opc : opcList){
                if(item.getItemName() == opc.getItemName()){
                    opc.setItemValue(item.getValue().toString());
                    opc.setTimeStamp(item.getTimeStamp());
                    opc.setDateImport(Date.from(Instant.now()));
                }
            }
        }
        //получаю готовый список данных для записи в БД OIS
        List<OIS> list = getOISImport(opcList);
        //пишу в OIS
        HibernetSession.getInstance().getBase().add(list);
    }


    //заполнение списка для импорта в OIS, т.е. данные конфига и OIS перемешиваются -> отдаются обратно списком.
    private List<OIS> getOISImport(List<BaseOPC> opcList){
        List<OIS> list = new ArrayList<OIS>();
        for (BaseOPC opc : opcList){
            list.add(new OIS(opc.getEventType(),opc.getObjType(),opc.getObjID(),opc.getPropType(),
                    opc.getTimeStamp(),opc.getItemValue(),opc.getDateImport()));
        }
        return list;
    }


    //запуск потока, и выполнение считывания данных с OPC сервера
    public void run() {
        JOpc.coInitialize();

        //Create opc instance
        JOpc jopc = new JOpc(server.getServerName(),server.getOpcServer(),server.getClientNameForOPC());

        //Create group opc instance
        OpcGroup group = new OpcGroup(server.getNameGroup(), true, 500, 0.0f);

        //Create list OPCItem for add to group
        List<OpcItem> itemList = new ArrayList<OpcItem>();

        //Add to List OPCItem
        for(BaseOPC opc : list){
            itemList.add(new OpcItem(opc.getItemName(), true, ""));
        }

        //Add item in group
        for(OpcItem item : itemList){
            group.addItem(item);
        }

        //Add group to OPC server
        jopc.addGroup(group);

        //Connect to OPC server
        connectToOPCServer(jopc);

        //Read group data from OPC server in While-cicle
        try{
            //status this(info from BD Config) -> OPC server read - 1(true)/0(false)
            while (status==1){
                if(working==false){
                    break;
                }
                if(jopc.ping()==true){
                    OpcGroup opcGroup = jopc.synchReadGroup(group);
                    saveOPCDateToBase(opcGroup.getItems(), list);
                }
                //if connect -> false, when reconnect to OPC server
                else {
                    connectToOPCServer(jopc);
                }
                Thread.sleep(time);
            }
        } catch (ComponentNotFoundException e) {
            loger.error("OPC Sever "+server.getOpcServer() + " -> Address:" +server.getServerName() + "компонент не найден. ");
        }
        catch (SynchReadException e) {
            loger.error("OPC Sever "+server.getOpcServer() + " -> Address:" +server.getServerName() + "не возможно считать асинхронно. ");
        } catch (InterruptedException e) {
            loger.error("OPC Sever "+server.getOpcServer() + " -> Address:" +server.getServerName()+ "  ->>> "+ e);
        } finally {
            JOpc.coUninitialize();
            loger.debug("Disconnect OPC Server " + server.getOpcServer() + " -> Address:" +server.getServerName());
        }
    }

    public void finish(){
        working = false;
    }

    //подключение к OPC серверу
    private void connectToOPCServer(JOpc jopc){
        loger.debug("Connect OPC Server " +server.getOpcServer() + " -> Address:" +server.getServerName());
        try {
            //Connect and registration in OPC Server
            jopc.connect();
            jopc.registerGroups();
        } catch (ConnectivityException e) {
            loger.error("связь с OPC Sever " + server.getOpcServer() + " -> Address:" +server.getServerName() + " не установлена.");
        } catch (UnableAddItemException e) {
            loger.error("не удалось добавить item в OPC Sever "+server.getOpcServer() + " -> Address:" +server.getServerName()+" не установлена.");
        } catch (UnableAddGroupException e) {
            loger.error("не удалось добавить группу в OPC Sever "+server.getOpcServer() + " -> Address:" +server.getServerName());
        }
    }

}
