package com.todolist.domain.factorys;

import com.todolist.domain.entities.Task;
import com.todolist.domain.interfaces.ITask;

import java.util.Date;

public class TaskFactory {
    public static ITask create(String name) {
        return new Task(name);
    }

    public static ITask create(String name, Date date){
        return new Task(name, date);
    }


}
