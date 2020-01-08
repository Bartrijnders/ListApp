package com.todolist.presentation.eventHandlers.homepage;

import com.todolist.domain.interfaces.IFolder;
import com.todolist.domain.interfaces.ITask;
import com.todolist.presentation.controllers.newfolderpageController;
import com.todolist.presentation.controllers.newtaskpageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class NewFolderButtonEvent {

    public IFolder handleClick(ActionEvent event, ArrayList<IFolder> folders){
        try {
            newfolderpageController newfolderpageController = new newfolderpageController();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/newfolderpage.fxml"));
            fxmlLoader.setController(newfolderpageController);
            Parent root = fxmlLoader.load();
            Stage window = new Stage();
            newfolderpageController.setList(folders);
            window.setTitle("folder aanmaken");
            Scene scene = new Scene(root, 600, 400);
            window.setScene(scene);
            window.initModality(Modality.APPLICATION_MODAL);
            window.showAndWait();
            return newfolderpageController.getAnswer();

        }catch (IOException e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace().toString());
            return null;
        }

    }
}
