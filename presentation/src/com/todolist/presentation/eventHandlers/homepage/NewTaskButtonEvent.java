package com.todolist.presentation.eventHandlers.homepage;

import com.todolist.domain.interfaces.IFolder;
import com.todolist.domain.interfaces.ITask;
import com.todolist.presentation.controllers.newtaskpageController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class NewTaskButtonEvent {

    public ITask handleClick(ActionEvent event, IFolder folder){
        try {
            newtaskpageController newtaskpageController = new newtaskpageController();
            newtaskpageController.setFolder(folder);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/newtaskpage.fxml"));
            fxmlLoader.setController(newtaskpageController);
            Parent root = fxmlLoader.load();
            Stage window = new Stage();
            Scene scene = new Scene(root, 600, 400);
            window.setScene(scene);
            window.initModality(Modality.APPLICATION_MODAL);
            window.showAndWait();
            window.setTitle("taak aanmaken");
            return  newtaskpageController.getAnswer();

        }catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace().toString());
            return null;
        }

    }
}
