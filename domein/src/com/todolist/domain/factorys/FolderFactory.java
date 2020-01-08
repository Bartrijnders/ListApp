package com.todolist.domain.factorys;

import com.todolist.domain.entities.Folder;
import com.todolist.domain.interfaces.IFolder;

public class FolderFactory {
    public static IFolder create(String name) {
        return new Folder(name);
    }
}
