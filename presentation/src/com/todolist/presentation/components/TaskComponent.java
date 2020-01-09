package com.todolist.presentation.components;

import com.todolist.domain.interfaces.IFolder;
import com.todolist.domain.interfaces.ITask;
import com.todolist.presentation.controllers.HomePageController;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

public class TaskComponent extends GridPane {

    private Label taskTitleLabel;
    private CheckBox checkBox;
    private ITask task;
    private ContextMenu contextMenu;
    private MenuItem editMenuItem;
    private MenuItem deleteMenuItem;



    public TaskComponent(ITask task) {
        this.task = task;
        this.setCursor(Cursor.HAND);
        this.setPadding(new Insets(20,0, 20, 0 ));
        taskTitleLabel = new Label(task.getTitle());
        checkBox = new CheckBox();
        if(task.isChecked()){
            checkBox.setSelected(true);
        }
        createLayout();
        this.add(taskTitleLabel,0,1,1,1);
        this.add(checkBox,1,1,1,1);
    }

    public GridPane getLayout() {
        return this;
    }

    private void createLayout(){
        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(Priority.ALWAYS);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS);
        this.getColumnConstraints().add(col1);
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> task.setChecked(newValue));

    }

    public void refresh(){
        taskTitleLabel.setText(task.getTitle());
        if(task.isChecked()){
            checkBox.setSelected(true);
        }
    }

    public ITask getTask() {
        return task;
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public void setContextMenu(ContextMenu contextMenu) {
        this.contextMenu = contextMenu;
    }

    public MenuItem getEditMenuItem() {
        return editMenuItem;
    }

    public void setEditMenuItem(MenuItem editMenuItem) {
        this.editMenuItem = editMenuItem;
    }

    public MenuItem getDeleteMenuItem() {
        return deleteMenuItem;
    }

    public void setDeleteMenuItem(MenuItem deleteMenuItem) {
        this.deleteMenuItem = deleteMenuItem;
    }



    public void setTask(ITask task) {
        this.task = task;
    }
}
