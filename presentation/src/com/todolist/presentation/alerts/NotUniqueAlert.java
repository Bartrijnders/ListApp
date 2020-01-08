package com.todolist.presentation.alerts;

import javafx.scene.control.Alert;

public class NotUniqueAlert {
    public static void show(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("information alert");
        alert.setHeaderText("Ingevulde informatie bestaat al");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
