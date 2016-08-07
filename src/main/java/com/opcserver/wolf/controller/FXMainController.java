package com.opcserver.wolf.controller;

import com.opcserver.wolf.model.BaseOPC;
import com.opcserver.wolf.model.OPCServer;
import com.opcserver.wolf.model.OPCTagsForMainForm;
import com.opcserver.wolf.tools.HibernetSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Artur on 15.07.2016.
 */
public class FXMainController {

    private final static Logger loger = Logger.getLogger(FXMainController.class);

    private final String isTrue = "true";

    private final String isFalse = "false";

    private  JeazyOPCSerivice opc = null;

    @FXML
    public TableColumn<OPCTagsForMainForm, String> idServer1;
    @FXML
    public TableColumn<OPCTagsForMainForm, String> idAddress1;
    @FXML
    public TableColumn<OPCTagsForMainForm, Integer> idTags1;
    @FXML
    public TableColumn<OPCTagsForMainForm, Double> idTimerCicle;

    public TableColumn<OPCTagsForMainForm, Boolean> idWork;
    @FXML
    public TableView<OPCTagsForMainForm> idFXTags;
    @FXML
    public Label idWorking;
    @FXML
    public Label fxCountRows;


    public void onGetOISRows(ActionEvent event){
            fxCountRows.setText(HibernetSession.getInstance().getBase().getCountRowsOIS().toString());
        }

    public void fxStartOPC(ActionEvent actionEvent){
            idWorking.setText(isTrue);
            // Берем список серверов
            List<OPCServer> servers =  HibernetSession.getInstance().getBase().opc();
            List<BaseOPC> baseOPCList = null;
            //прогоняем по отдельности каждый сервер через 2 метода:
            //1.Мы получаем список тегов через 1 сервер и так повторяем цикл
            //2.Мы полученные теги и сервер отправляем на обработку в основной контроллер, который закачивает теги и достает данные
            //с сервера. После того как он их получил данные c OPC мы сортируем и закачиваем данные на сервак.
            for (OPCServer opcServer : servers){
                baseOPCList = HibernetSession.getInstance().getBase().list(opcServer.getId());
                opc =  new JeazyOPCSerivice(baseOPCList, opcServer, opcServer.getTime(), opcServer.isServerStatus());
                new Thread(opc).start();
            }
    }

    public void fxStopOPC(ActionEvent actionEvent){
        idWorking.setText(isFalse);
        opc.finish();
    }

    public void fxUpdateTags(ActionEvent actionEvent){
            idServer1.setCellValueFactory(new PropertyValueFactory<OPCTagsForMainForm, String>("opcServerName"));
            idAddress1.setCellValueFactory(new PropertyValueFactory<OPCTagsForMainForm, String>("address"));
            idTags1.setCellValueFactory(new PropertyValueFactory<OPCTagsForMainForm, Integer>("countTags"));
            idTimerCicle.setCellValueFactory(new PropertyValueFactory<OPCTagsForMainForm, Double>("timerCicle"));
            idWork.setCellValueFactory(new PropertyValueFactory<OPCTagsForMainForm, Boolean>("ServerStatus"));
            List<OPCTagsForMainForm> list = HibernetSession.getInstance().getBase().getTagsCount();
            ObservableList<OPCTagsForMainForm> data = FXCollections.observableArrayList(list);
            idFXTags.setItems(data);
    }
}
