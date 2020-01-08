package com.todolist.logic.todolistlogic;

import com.todolist.dataacces.filedatehandlers.FileDataSaver;
import com.todolist.dataacces.filedatehandlers.IDataSaver;
import com.todolist.domain.interfaces.IFolder;

import java.util.ArrayList;

public class DataSaver {
    public void saveToFile(ArrayList<IFolder> folders){
        IDataSaver dataSaver = new FileDataSaver();
        dataSaver.save(folders);
    }
}
