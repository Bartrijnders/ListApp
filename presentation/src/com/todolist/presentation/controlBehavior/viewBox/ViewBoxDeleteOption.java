package com.todolist.presentation.controlBehavior.viewBox;

import com.todolist.presentation.alerts.DeleteAlertBox;
import com.todolist.presentation.components.TaskComponent;
import com.todolist.presentation.controllers.HomePageController;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
