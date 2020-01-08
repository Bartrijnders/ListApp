package com.todolist.domain.entities;

import com.todolist.domain.interfaces.IDetail;
import com.todolist.domain.interfaces.ITask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task implements ITask, Serializable {

    //fields
    private String title;
    private boolean checked;
    private Date dateOfCreation;
    private List<IDetail> details;

    public Task(String title) {
        this.title = title;
        this.checked = false;
        this.dateOfCreation = new Date(System.currentTimeMillis());
        details = new ArrayList<>();
    }

    public Task(String title, Date date){
        this.title = title;
        this.checked = false;
        this.dateOfCreation = date;
        details = new ArrayList<>();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public Date getDateOfCreation() {
        return dateOfCreation;
    }


    @Override
    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public List<IDetail> getDetails() {
        return details;
    }

    public void setDetails(List<IDetail> details) {
        this.details = details;
    }
}
