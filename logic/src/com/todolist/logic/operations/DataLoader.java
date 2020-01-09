package com.todolist.logic.operations;

import com.todolist.dataacces.filedatehandlers.FileDataLoader;
import com.todolist.dataacces.filedatehandlers.IDataLoader;
import com.todolist.domain.interfaces.IFolder;

import java.util.ArrayList;

public class DataLoader {
    public ArrayList<IFolder> loadFromFile(){
        IDataLoader dataLoader = new FileDataLoader();
        return dataLoader.load();
    }
}
