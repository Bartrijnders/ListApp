package com.todolist.logic.folderlogic;

import com.todolist.domain.interfaces.IFolder;
import com.todolist.domain.interfaces.ITask;

public class TaskRemover {
    public static void remove(IFolder folder, ITask task) {
        folder.getItems().remove(task);
    }
}
