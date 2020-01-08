package com.todolist.presentation.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DeleteAlertBox {
    public static boolean show(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Weet je zeker dat je dit wilt verwijderen?");
        ButtonType cancel = new ButtonType("Annuleren", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType bevestig = new ButtonType("Bevestig");
        alert.getButtonTypes().setAll(cancel, bevestig);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == cancel){
            return false;
        }
        else return true;
    }
}
