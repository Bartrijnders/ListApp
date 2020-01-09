package com.todolist.presentation.controlBehavior.taskComponents;

import com.todolist.domain.interfaces.ITask;
import com.todolist.presentation.components.TaskComponent;

public class TaskComponentFactory {
    public TaskComponent createComponent(ITask task){
        return new TaskComponent(task);
    }
}
