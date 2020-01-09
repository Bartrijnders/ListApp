package com.todolist.presentation.controlbehavior.taskcomponents;

import com.todolist.domain.interfaces.ITask;
import com.todolist.presentation.components.TaskComponent;

import java.util.ArrayList;

public class TaskComponentCollector {

    //Takes in a list of tasks and returns a list with task components.
    public ArrayList<TaskComponent> collectComponents(ArrayList<ITask> list){
        ArrayList<TaskComponent> output = new ArrayList<>();
        TaskComponentFactory taskComponentFactory = new TaskComponentFactory();
        list.stream().forEach(x -> output.add(taskComponentFactory.createComponent(x)));
        return output;
    }
}
