package com.opcserver.wolf.tools;

import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

public class HibernateUtil {

    //Логирование
    private final static Logger loger = Logger.getLogger(HibernateUtil.class);

    private static SessionFactory sessionBase = null;

    private static SessionFactory sessionConfig = null;

    private HibernateUtil(){}

    static {

        try {
            if(sessionBase == null){
                sessionBase = new Configuration().configure("hibernate-base.cfg.xml").buildSessionFactory();
                sessionConfig = new Configuration().configure("hibernate-config.cfg.xml").buildSessionFactory();
            }
        }catch (Exception ex){
            loger.error("HibernateUtil:файл не найден или проблема с конфигурационным файлом SQL");
        }
    }
    public static SessionFactory getSessionBase() {
        return sessionBase;
    }

    public static SessionFactory getSessionConfig() {
        return sessionConfig;
    }
}
