package com.todolist.presentation.components;

import com.todolist.domain.interfaces.IFolder;
import com.todolist.domain.interfaces.ITask;
import com.todolist.presentation.alerts.DeleteAlertBox;
import com.todolist.presentation.controllers.EditTaskPageController;
import com.todolist.presentation.controllers.HomePageController;
import com.todolist.presentation.eventHandlers.TaskEvents;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskComponent extends Node {

    private GridPane layout;
    private Label taskTitleLabel;
    private CheckBox checkBox;
    private ITask task;
    private ContextMenu contextMenu;
    private MenuItem editMenuItem;
    private MenuItem deleteMenuItem;
    private HomePageController homePageController;
    private IFolder folder;


    public TaskComponent(ITask task, HomePageController homePageController) {
        this.homePageController = homePageController;
        this.task = task;
        taskTitleLabel = new Label(task.getTitle());
        checkBox = new CheckBox();
        if(task.isChecked()){
            checkBox.setSelected(true);
        }
        createLayout();
        layout.add(taskTitleLabel,0,1,1,1);
        layout.add(checkBox,1,1,1,1);
        addContextMenu();
    }

    public GridPane getLayout() {
        return layout;
    }

    public void setFolder(IFolder folder) {
        this.folder = folder;
    }

    private void createLayout(){
        layout = new GridPane();
        layout.setGridLinesVisible(false);
        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(Priority.ALWAYS);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS);
        System.out.println(parentProperty().toString());
        layout.getColumnConstraints().add(col1);
        addRightCLickEvent();
        checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                task.setChecked(newValue);
            }
        });

    }

    public void addRightCLickEvent(){
        layout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.SECONDARY){
                    TaskEvents taskEvents = new TaskEvents();
                    taskEvents.rightClick();
                    contextMenu.show(layout, event.getScreenX(), event.getScreenY());
                }
            }
        });
    }
    public void addOptions(){
        editMenuItem.setOnAction(e -> {
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
                editTaskPageController.setWorkTask(task);
                window.showAndWait();
                window.setTitle("taak Editen");
                task = editTaskPageController.getAnswer();
                refresh();

            }catch (IOException ex){
                System.out.println(ex.getMessage());
                System.out.println(ex.getCause());
                System.out.println(ex.getStackTrace().toString());
            }
        });
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

    public void addDeleteOption(){
        deleteMenuItem.setOnAction(event -> {
            if(DeleteAlertBox.show()){
                homePageController.getItems(task.getDateOfCreation(), folder);
            }
        });
    }

    public void addContextMenu(){
        contextMenu = new ContextMenu();
        editMenuItem = new MenuItem("Edit");
        deleteMenuItem = new MenuItem(("Delete"));
        contextMenu.getItems().add(editMenuItem);
        contextMenu.getItems().add(deleteMenuItem);
        addOptions();
        addDeleteOption();
        ContextMenu folderMenu = new ContextMenu();
    }


}
