package com.todolist.presentation.controllers;

import com.todolist.domain.factorys.FolderFactory;
import com.todolist.domain.interfaces.IFolder;
import com.todolist.domain.interfaces.ITask;
import com.todolist.logic.operations.*;
import com.todolist.presentation.components.TaskComponent;
import com.todolist.presentation.controlbehavior.viewBox.ViewBoxInitBehavior;
import com.todolist.presentation.eventHandlers.homepage.NewFolderButtonEvent;
import com.todolist.presentation.eventHandlers.homepage.NewTaskButtonEvent;
import com.todolist.presentation.main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
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

    private ArrayList<IFolder> list;

    private MultipleSelectionModel<TreeItem<IFolder>> multipleSelectionModel;

    private DataSaver dataSaver;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataSaver = new DataSaver();
        DataLoader dataLoader = new DataLoader();
        list = dataLoader.loadFromFile();
        fillInData();
        shapeVbox();
        getTreeviewContent();
        setActionListenerTreeview();
        multipleSelectionModel = contentTreeview.getSelectionModel();
        multipleSelectionModel.select(0);
        contentTreeview.requestFocus();
        getItems(LocalDateToDateConV.convertToDate(datePicker.getValue()));
        newTaskButton.setOnAction(this::handleNewButton);
        newFolderButton.setOnAction(this::handleNewFolderButton);
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> getItems(LocalDateToDateConV.convertToDate(newValue)));
        Main.getPs().setOnCloseRequest(e->dataSaver.saveToFile(list));

    }

    public ArrayList<IFolder> getAllFolders(){
        return list;
    }
    public IFolder getActiveFolder(){
      return multipleSelectionModel.getSelectedItem().getValue();
    }

    public IFolder getRootFolder(){
        return contentTreeview.getRoot().getValue();
    }

    public Date getSelectedDate(){
        return LocalDateToDateConV.convertToDate(datePicker.getValue());
    }

    public VBox getViewVbox(){
        return viewVbox;
    }


    public void shapeVbox(){
        viewVbox.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        viewVbox.setFillWidth(true);
    }

    public void fillInData(){
        datePicker.setValue(LocalDate.now());
    }

    public void getItems(Date date){
        TaskGetterDate taskGetterDate = new TaskGetterDate();
        ArrayList<ITask> worklist;
        TreeItem<IFolder> treeItem;
        treeItem = multipleSelectionModel.getSelectedItem();
        if(treeItem != null && treeItem.getValue() != contentTreeview.getRoot().getValue()){
            worklist = taskGetterDate.getTaskByDate(getSelectedFolder(multipleSelectionModel), date);
            viewVbox.getChildren().clear();
            if(!worklist.isEmpty())
                for(ITask task : worklist){
                    TaskComponent taskComponent = new TaskComponent(task);
                    viewVbox.getChildren().addAll(taskComponent);
                }
        }
        else {
            AllTasksGetter allTasksGetter = new AllTasksGetter();
            worklist = allTasksGetter.getTasks(list,date);
            viewVbox.getChildren().clear();
            if(!worklist.isEmpty())
                for(ITask task : worklist){
                    TaskComponent taskComponent = new TaskComponent(task);
                    viewVbox.getChildren().addAll(taskComponent);
                }
        }
        ViewBoxInitBehavior viewBoxInitBehavior = new ViewBoxInitBehavior();
        viewBoxInitBehavior.initBehavior(viewVbox,this);
    }

    public void getTreeviewContent(){
        contentTreeview.setRoot(null);
        Optional<IFolder> stream = list.stream().filter(x -> x.getTitle().toUpperCase().matches("FOLDERS")).findFirst();
        if(stream.isPresent()){
            setRootFolder(stream.get());
            for(IFolder folder : list){
                if(!folder.getTitle().matches(contentTreeview.getRoot().getValue().getTitle())){
                    TreeItem<IFolder> treeItem = new TreeItem<>(folder);
                    contentTreeview.getRoot().getChildren().add(treeItem);
                }
            }
        }
        else {
            IFolder rootFolder = FolderFactory.create("FOLDERS");
            list.add(rootFolder);
            setRootFolder(rootFolder);
        }

    }

    public void setRootFolder(IFolder rootFolder){
        TreeItem<IFolder> root;
        root = new TreeItem<>(rootFolder);
        root.setExpanded(true);
        contentTreeview.setRoot(root);
    }


    public void handleNewButton(ActionEvent e){
        NewTaskButtonEvent newTaskButtonEvent = new NewTaskButtonEvent();
        ITask task = newTaskButtonEvent.handleClick(e, getSelectedFolder(multipleSelectionModel));
        if(task != null){
            dataSaver.saveToFile(list);
            getItems(LocalDateToDateConV.convertToDate(datePicker.getValue()));
        }

    }


    public void handleNewFolderButton(ActionEvent e){
        NewFolderButtonEvent newfolderbuttonevent = new NewFolderButtonEvent();
        IFolder folder = newfolderbuttonevent.handleClick(e, list);
        if (folder != null) {
            list.add(folder);
            dataSaver.saveToFile(list);
            getTreeviewContent();
        }

    }

    public IFolder getSelectedFolder(MultipleSelectionModel<TreeItem<IFolder>> multipleSelectionModel){
        TreeItem<IFolder> selectedItem = multipleSelectionModel.getSelectedItem();
        if(selectedItem == null){

            selectedItem = contentTreeview.getRoot();
            contentTreeview.requestFocus();
        }
        return selectedItem.getValue();
    }


    public void setActionListenerTreeview(){
        contentTreeview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> getItems(LocalDateToDateConV.convertToDate(datePicker.getValue())));
    }

    public void refresh(){
        getItems(LocalDateToDateConV.convertToDate(datePicker.getValue()));
        getTreeviewContent();
    }

}
