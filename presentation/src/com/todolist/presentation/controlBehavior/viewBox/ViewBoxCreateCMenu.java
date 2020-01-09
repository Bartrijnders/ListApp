package com.todolist.presentation.controlBehavior.viewBox;

import com.todolist.presentation.components.TaskComponent;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class ViewBoxCreateCMenu {
    public void createCMenu(VBox vBox){
        for(Node node : vBox.getChildren()){
           // if(node instanceof TaskComponent){
                ((TaskComponent) node).setContextMenu(new ContextMenu());
                ((TaskComponent) node).setDeleteMenuItem(new MenuItem("Delete"));
                ((TaskComponent) node).setEditMenuItem(new MenuItem("Edit"));
                ((TaskComponent) node).getContextMenu().getItems().add(((TaskComponent) node).getDeleteMenuItem());
                ((TaskComponent) node).getContextMenu().getItems().add(((TaskComponent) node).getEditMenuItem());
           // }
        }

    }
}
