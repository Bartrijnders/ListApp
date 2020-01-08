package com.todolist.domain.entities;

import com.todolist.domain.interfaces.IFolder;
import com.todolist.domain.interfaces.ITask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Folder implements IFolder, Serializable {
    private List<ITask> items;
    private String title;

    public Folder(String title) {
        this.title = title;
        this.items = new ArrayList<>();
    }

    @Override
    public List<ITask> getItems() {
        return items;
    }

    @Override
    public void setItems(List<ITask> items) {
        this.items = items;
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
    public String toString() {
        return title;
    }
}
