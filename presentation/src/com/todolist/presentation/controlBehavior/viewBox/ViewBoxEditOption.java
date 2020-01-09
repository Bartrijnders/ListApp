package com.todolist.presentation.controlBehavior.viewBox;

import com.todolist.presentation.alerts.DeleteAlertBox;
import com.todolist.presentation.components.TaskComponent;
import com.todolist.presentation.controllers.EditTaskPageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewBoxEditOption {
    public void addEditOption(VBox vBox){
        vBox.getChildren().stream().filter(x -> x instanceof TaskComponent).forEach(x -> {
            ((TaskComponent) x).getEditMenuItem().setOnAction(e -> {
                try {
                    EditTaskPageController editTaskPageController = new EditTaskPageController();
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/newtaskpage.fxml"));
                    fxmlLoader.setController(editTaskPageController);
                    Parent root = fxmlLoader.load();
                    Stage window = new Stage();
                    Scene scene = new Scene(root, 600, 400);
                    window.setScene(scene);
                    window.initModality(Modality.APPLICATION_MODAL);
                    editTaskPageController.setWorkTask(((TaskComponent) x).getTask());
                    window.showAndWait();
                    window.setTitle("taak Editen");
                    ((TaskComponent) x).setTask(editTaskPageController.getAnswer());
                    ((TaskComponent) x).refresh();

                }catch (IOException ex){
                    System.out.println(ex.getMessage());
                    System.out.println(ex.getCause());
                    System.out.println(ex.getStackTrace().toString());
                }
            });
        });
    }
}
