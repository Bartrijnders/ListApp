package com.todolist.logic.printers;

import com.todolist.domain.interfaces.IDetail;
import com.todolist.domain.interfaces.ITask;

public class TaskPrinter {

    public void print(ITask task) {
        System.out.println("Title: " + task.getTitle());

        if (!task.getDetails().isEmpty()) {
            for (IDetail detail : task.getDetails()) {
                System.out.println(detail.getType() + ": " + detail.getValue());
            }
        }
        System.out.println("------------------------------------------------------------------------");

    }
}
