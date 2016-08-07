package com.opcserver.wolf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMainForm extends Application {

    private String _title = "Wolf Logger";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("FXMainForm.fxml"));
        //primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResource("com/opcserver/wolf/tools/wolf.png").getPath()));
        primaryStage.setTitle(_title);
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();



    }
}
