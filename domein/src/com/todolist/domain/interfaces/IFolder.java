package com.todolist.domain.interfaces;

import java.util.List;

public interface IFolder {
    List<ITask> getItems();

    void setItems(List<ITask> items);

    String getTitle();

    void setTitle(String title);
}