package com.todolist.presentation.eventHandlers.newtaskpage;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CancelButtonEvent {

    public void handle(Stage stage){
        stage.close();
    }
}
