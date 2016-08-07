package com.opcserver.wolf.dao;

import com.opcserver.wolf.model.OIS;
import com.opcserver.wolf.model.OPCServer;
import com.opcserver.wolf.model.OPCTagsForMainForm;
import com.opcserver.wolf.tools.HibernateUtil;
import com.opcserver.wolf.model.BaseOPC;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class JeazyBaseOPCDAO implements IJeazyBaseOPC {

    //�����������
    private final static Logger loger = Logger.getLogger(JeazyBaseOPCDAO.class);

    ///������ � OIS BD
    public void add(List<OIS> list) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionBase().openSession();
            session.beginTransaction();
            for(OIS base : list){
                session.save(base);
            }
            session.getTransaction().commit();
        }catch (Exception ex){
            loger.error("Add in OIS BD(add):�� ������� ����������� � �� ��� ��� ����� �������.");
        }finally {
            if (session != null){
                session.close();
            }
        }
    }

    ///��������� ������ ����� �� OPC �������
    public List<BaseOPC> list(int param) {
        Session session = null;
        List<BaseOPC> list = null;
        try {
            session = HibernateUtil.getSessionConfig().openSession();
            list = (List<BaseOPC>) session.createCriteria(BaseOPC.class).
                                    add(Restrictions.eq("OPCServerID.id", param)).list();
        }catch (Exception ex){
            loger.error("Read tags with DB(list):�� ������� ����������� � �� ��� ��� ����� �������.");
        }finally {
            if (session != null){
                session.close();
            }
        }
        return list;
    }

    ///��������� ������ OPC ��������
    public List<OPCServer> opc() {
        Session session = null;
        List<OPCServer> list = null;
        try {
            session = HibernateUtil.getSessionConfig().openSession();
            list = (List<OPCServer>) session.createCriteria(OPCServer.class).list();
        }catch (Exception ex){
            loger.error("Read OPC Servers with BD(opc):�� ������� ����������� � �� ��� ��� ����� �������.");
        }finally {
            if (session != null){
                session.close();
            }
        }
        return list;
    }

    //������ ����
    public void firstOpenProject(BaseOPC baseOPC, OIS ois, OPCServer opcServer) {
        Session session = null;
        Session session2 = null;
        try {
            session2 = HibernateUtil.getSessionConfig().openSession();
            session2.beginTransaction();
            session2.save(baseOPC);
            session2.save(opcServer);
            session2.getTransaction().commit();
        }catch (Exception ex){
            loger.error("First create table BD(firstOpenProject):�� ������� ����������� � �� ��� ��� ����� �������.");
        }finally {
            if (session != null){
                session.close();
            }
        }
    }
//������ ����� ����������� ������� � �� ���-�� �����.
    public List<OPCTagsForMainForm> getTagsCount() {
        double timer;
        Number count;
        boolean status;
        //������ ��� sql
//        String sql = "SELECT opcServer, serverName, count(base.id) as countTags\n" +
//                "                FROM OPCServer AS opc\n" +
//                "        left outer join BaseOPC base ON opc.id = base.opc \n" +
//                "        GROUP BY opc.opcServer, opc.serverName";
        List<OPCServer> list = null;
        List<OPCTagsForMainForm> list1 = new ArrayList<OPCTagsForMainForm>();
        Session session = null;
        try {
            session = HibernateUtil.getSessionConfig().openSession();
            //����������� ��� �������
            list = (List<OPCServer>)session.createCriteria(OPCServer.class).list();
            //������ ������ �� ���-�� �����
            for(OPCServer opc : list){
                count = (Number)session.createCriteria(BaseOPC.class).
                        add(Restrictions.eq("OPCServerID.id", opc.getId()))
                        .setProjection(Projections.rowCount()).uniqueResult();
                timer = opc.getTime()/60000;
                if(opc.getServerStatus() == 1){
                    status = true;
                }else status = false;
                list1.add(new OPCTagsForMainForm(opc.getOpcServer(),opc.getServerName(),count, timer, status));
            }
        }catch (Exception ex){
            loger.error("Read count tags in OPC Servers(getTagsCount):�� ������� ����������� � �� ��� ��� ����� �������.");
        }finally {
            if (session != null){
                session.close();
            }
        }
        return list1;
    }

    public Number getCountRowsOIS() {
        Session session = null;
        Number count = null;
        try {
            session = HibernateUtil.getSessionBase().openSession();
            count = (Number)session.createCriteria(OIS.class)
                        .setProjection(Projections.rowCount()).uniqueResult();
        }catch (Exception ex){
            loger.error("Read count tags in OPC Servers(getTagsCount):�� ������� ����������� � �� ��� ��� ����� �������.");
        }finally {
            if (session != null){
                session.close();
            }
        }
        return count;
    }


}
