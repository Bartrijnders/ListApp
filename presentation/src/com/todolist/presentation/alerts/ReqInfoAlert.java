package com.todolist.presentation.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class ReqInfoAlert {
    public static void show(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informtion Alert");
        alert.setHeaderText("Niet alle gevraagde informatie is ingevuld");
        Label t = new Label(message);
        alert.getDialogPane().setContent(t);
        alert.showAndWait();
    }
}
