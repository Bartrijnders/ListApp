package com.todolist.presentation.controllers;

import com.todolist.domain.factorys.FolderFactory;
import com.todolist.domain.interfaces.IFolder;
import com.todolist.presentation.alerts.NotUniqueAlert;
import com.todolist.presentation.alerts.ReqInfoAlert;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class newfolderpageController implements Initializable {

    private IFolder answer;
    @FXML
    private TextField titleTextField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    private ArrayList<IFolder> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cancelButton.setOnAction(e -> close());
        confirmButton.setOnAction(e -> confirmEvent());

    }

    public void close(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public IFolder getAnswer(){
        return answer;
    }

    public void confirmEvent(){
        if(titleTextField.getText().isEmpty()){
            ReqInfoAlert.show("Geen titel ingevuld. vul deze a.u.b. in");
        }
        else{
            for(IFolder tempFolder : list){
                if (tempFolder.getTitle().equals(titleTextField.getText())){
                    NotUniqueAlert.show("Folder titel is niet unique, kies een andere titel");
                    return;
                }
            }
            answer = FolderFactory.create(titleTextField.getText());
            close();
        }
    }

    public void setList(ArrayList<IFolder> list) {
        this.list = list;
    }

    public ArrayList<IFolder> getList() {
        return list;
    }


}
