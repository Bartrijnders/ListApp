package com.todolist.presentation.controllers;

import com.todolist.domain.factorys.TaskFactory;
import com.todolist.domain.interfaces.ITask;
import com.todolist.logic.todolistlogic.DateFormatter;
import com.todolist.logic.todolistlogic.LocalDateToDateConV;
import com.todolist.presentation.alerts.ReqInfoAlert;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class EditTaskPageController implements Initializable {


    static ITask answer;
    private ITask workTask;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField titleTextField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    @FXML
    private AnchorPane anchorPane;


    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        close();
    }

    @FXML
    public void handleConfirmButtonAction(ActionEvent event){
        if(!checkNotEmpty()){
            ReqInfoAlert.show(missingInfoBuilder());

        }
        else{
            workTask.setTitle(titleTextField.getText());
            workTask.setDateOfCreation(LocalDateToDateConV.convertToDate(datePicker.getValue()));
            answer = workTask;
            close();
        }
    }

    public ITask getAnswer(){
        return answer;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComponents();
    }

    public void initComponents(){
        cancelButton.setOnAction(e -> {
            handleCloseButtonAction(e);
        });

        titleTextField.setPromptText("Vul hier uw titel in");
        confirmButton.setOnAction(e -> handleConfirmButtonAction(e));
    }

    public void close(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public Boolean checkNotEmpty(){
        if(titleTextField.getText().isEmpty() || datePicker.getValue() == null) {
            return false;
        }else{
            return true;
        }
    }

    public String missingInfoBuilder(){
        String output = "missing the Following info:";
        StringBuilder sb = new StringBuilder(output);
        sb.append("\n");
        if(titleTextField.getText().isEmpty()){
            sb.append("- Titel\n");
        }
        if(datePicker.getValue() == null){
            sb.append("- Datum\n");
        }
        sb.append("Vul de bovenstaande info alsnog in a.u.b.");
        return sb.toString();
    }

    public void setWorkTask(ITask workTask) {
        this.workTask = workTask;
        datePicker.setValue(LocalDateToDateConV.convertToLocalDate(workTask.getDateOfCreation()));
        titleTextField.setText(workTask.getTitle());
    }


}
