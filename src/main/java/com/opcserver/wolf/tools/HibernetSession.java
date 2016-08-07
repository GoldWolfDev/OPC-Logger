package com.opcserver.wolf.tools;

import com.opcserver.wolf.dao.IJeazyBaseOPC;
import com.opcserver.wolf.dao.JeazyBaseOPCDAO;

public class HibernetSession {

    private static HibernetSession instance =null;

    private IJeazyBaseOPC  base = null;

    private HibernetSession(){}

    public static HibernetSession getInstance() {
        if(instance == null){
            instance = new HibernetSession();
        }
        return instance;
    }

    public IJeazyBaseOPC getBase() {
        if(base == null){
            base = new JeazyBaseOPCDAO();
        }
        return base;
    }
}
