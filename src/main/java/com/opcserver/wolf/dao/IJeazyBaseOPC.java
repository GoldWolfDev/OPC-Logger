package com.opcserver.wolf.dao;

import com.opcserver.wolf.model.BaseOPC;
import com.opcserver.wolf.model.OIS;
import com.opcserver.wolf.model.OPCServer;
import com.opcserver.wolf.model.OPCTagsForMainForm;

import java.util.List;

/**
 * Created by RafikovAR on 26.05.2016.
 */
public interface IJeazyBaseOPC {

    //add OPC value with Server
    public void add(List<OIS> list);

    //read list DB for OPC
    public List<BaseOPC> list(int param);

    public List<OPCServer> opc();

    public void firstOpenProject(BaseOPC baseOPC, OIS ois, OPCServer opcServer);

    public List<OPCTagsForMainForm> getTagsCount();

    public Number getCountRowsOIS();
}
