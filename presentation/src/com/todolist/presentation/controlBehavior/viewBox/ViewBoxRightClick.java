package com.todolist.presentation.controlBehavior.viewBox;

import com.todolist.presentation.components.TaskComponent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;

public class ViewBoxRightClick {
    public void addRightClick(VBox vBox){
        vBox.getChildren().stream().filter( x -> x instanceof TaskComponent).forEach(x -> x.setOnMouseClicked(event -> {
            System.out.println(event.getSource().toString());
            if(event.getButton() == MouseButton.SECONDARY){
                ((TaskComponent)x).getContextMenu().show(x,event.getScreenX(),event.getScreenY());
            }
        }));
    }
}
