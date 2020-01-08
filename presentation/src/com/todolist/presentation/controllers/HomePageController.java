package com.todolist.presentation.controllers;

import com.todolist.domain.factorys.FolderFactory;
import com.todolist.domain.interfaces.IFolder;
import com.todolist.domain.interfaces.ITask;
import com.todolist.logic.todolistlogic.*;
import com.todolist.presentation.components.TaskComponent;
import com.todolist.presentation.eventHandlers.homepage.NewFolderButtonEvent;
import com.todolist.presentation.eventHandlers.homepage.NewTaskButtonEvent;
import com.todolist.presentation.main.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();


    @FXML
    private TreeView<IFolder> contentTreeview;
    @FXML
    private DatePicker datePicker;
    @FXML
    private VBox viewVbox;
    @FXML
    private Button newTaskButton;
    @FXML
    private Button newFolderButton;
    @FXML
    private Button newLabelButton;
    @FXML
    private AnchorPane anchorPane;

    private ArrayList<IFolder> list;

    private IFolder rootFolder;

    private ITask task;

    private IFolder folder;

    private MultipleSelectionModel multipleSelectionModel;

    private DataSaver dataSaver;
    private DataLoader dataLoader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataSaver = new DataSaver();
        dataLoader = new DataLoader();
        list = dataLoader.loadFromFile();
        fillInData();
        shapeVbox();
        getTreeviewContent();
        setActionListenerTreeview();
        multipleSelectionModel = contentTreeview.getSelectionModel();
        multipleSelectionModel.select(0);
        contentTreeview.requestFocus();
        getItems(LocalDateToDateConV.convertToDate(datePicker.getValue()),rootFolder);
        newTaskButton.setOnAction(e -> handleNewButton(e));
        newFolderButton.setOnAction(e -> handleNewFolderButton(e));
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> getItems(LocalDateToDateConV.convertToDate(newValue), rootFolder));
        Main.getPs().setOnCloseRequest(e->dataSaver.saveToFile(list));

    }


    public void shapeVbox(){
        viewVbox.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        viewVbox.setFillWidth(true);
    }

    public void fillInData(){
        datePicker.setValue(LocalDate.now());
    }

    public void getItems(Date date, IFolder rootFolder){
        TaskGetterDate taskGetterDate = new TaskGetterDate();
        ArrayList<ITask> worklist;
        TreeItem<IFolder> treeItem;
        treeItem = (TreeItem<IFolder>) multipleSelectionModel.getSelectedItem();
        if(treeItem != null && treeItem.getValue() !=rootFolder){
            worklist = taskGetterDate.getTaskByDate(getSelectedFolder(multipleSelectionModel), date);
            viewVbox.getChildren().clear();
            if(!worklist.isEmpty())
                for(ITask task : worklist){
                    TaskComponent taskComponent = new TaskComponent(task, this);
                    taskComponent.setFolder(treeItem.getValue());
                    viewVbox.getChildren().addAll(taskComponent.getLayout());
                }
        }
        else {
            AllTasksGetter allTasksGetter = new AllTasksGetter();
            worklist = allTasksGetter.getTasks(list,date);
            viewVbox.getChildren().clear();
            if(!worklist.isEmpty())
                for(ITask task : worklist){
                    TaskComponent taskComponent = new TaskComponent(task, this);
                    taskComponent.setFolder(rootFolder);
                    viewVbox.getChildren().addAll(taskComponent.getLayout());
                }
        }
    }

    public void getTreeviewContent(){
        //root branch
        contentTreeview.setRoot(null);
        boolean check = false;
        rootFolder = FolderFactory.create("FOLDERS");
        TreeItem<IFolder> root;
        root = new TreeItem<>(rootFolder);
        root.setExpanded(true);
        contentTreeview.setRoot(root);

        //folders branch
        if(!list.isEmpty()){
            for(IFolder folder : list){
                    TreeItem<IFolder> treeItem = new TreeItem<>(folder);
                    root.getChildren().add(treeItem);
            }
        }

    }

    public TreeItem<String> makeBranch(String name, TreeItem<String> parent){
        TreeItem<String> item = new TreeItem<>(name);
        parent.getChildren().add(item);
        return item;
    }

    public void handleNewButton(ActionEvent e){
        NewTaskButtonEvent newTaskButtonEvent = new NewTaskButtonEvent();
        task = newTaskButtonEvent.handleClick(e, getSelectedFolder(multipleSelectionModel));
        if(task != null){
            dataSaver.saveToFile(list);
            getItems(LocalDateToDateConV.convertToDate(datePicker.getValue()),rootFolder);
        }

    }


    public void handleNewFolderButton(ActionEvent e){
        NewFolderButtonEvent newfolderbuttonevent = new NewFolderButtonEvent();
        folder = newfolderbuttonevent.handleClick(e, list);
        if (folder != null) {
            list.add(folder);
            dataSaver.saveToFile(list);
            getTreeviewContent();
        }

    }

    public IFolder getSelectedFolder(MultipleSelectionModel multipleSelectionModel){
        TreeItem<IFolder> selectedItem = (TreeItem<IFolder>)multipleSelectionModel.getSelectedItem();
        if(selectedItem == null){

            selectedItem = contentTreeview.getRoot();
            contentTreeview.requestFocus();
        }
        IFolder folder = selectedItem.getValue();
        return folder;
    }


    public void setActionListenerTreeview(){
        contentTreeview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<IFolder>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<IFolder>> observable, TreeItem<IFolder> oldValue, TreeItem<IFolder> newValue) {
                getItems(LocalDateToDateConV.convertToDate(datePicker.getValue()), rootFolder);
            }
        });
    }




}
