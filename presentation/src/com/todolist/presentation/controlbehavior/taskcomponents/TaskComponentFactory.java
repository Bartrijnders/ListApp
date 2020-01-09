package com.todolist.presentation.controlbehavior.taskcomponents;

import com.todolist.domain.interfaces.ITask;
import com.todolist.presentation.components.TaskComponent;

public class TaskComponentFactory {

    //takes a single Task and makes an component out of it.
    public TaskComponent createComponent(ITask task){
        return new TaskComponent(task);
    }
}
