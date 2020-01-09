package com.todolist.presentation.controlbehavior.viewBox;

import com.todolist.presentation.alerts.DeleteAlertBox;
import com.todolist.presentation.components.TaskComponent;
import com.todolist.presentation.controllers.HomePageController;
import javafx.scene.layout.VBox;

public class ViewBoxDeleteOption {
    public void addDeleteTaskOption(VBox vBox, HomePageController homePageController){
        vBox.getChildren().stream().filter(x -> x instanceof TaskComponent).forEach(x -> {
            ((TaskComponent) x).getDeleteMenuItem().setOnAction(e -> {
                if(DeleteAlertBox.show()){
                    homePageController.getActiveFolder().getItems().remove(((TaskComponent) x).getTask());
                    homePageController.refresh();
                }
            });
        });
    }
}
