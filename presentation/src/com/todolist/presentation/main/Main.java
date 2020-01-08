package com.todolist.presentation.main;

//import com.todolist.logic.todolistlogic.ToDoListSaver;
import com.todolist.presentation.controllers.HomePageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    static Stage ps;

    @Override
    public void start(Stage primaryStage) throws Exception {
        ps = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/homepage.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
        primaryStage.setTitle("To-Do List app");
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static Stage getPs() {
        return ps;
    }
}
