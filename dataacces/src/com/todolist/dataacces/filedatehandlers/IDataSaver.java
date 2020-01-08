package com.todolist.dataacces.filedatehandlers;


import com.todolist.domain.interfaces.IFolder;

import java.util.ArrayList;

public interface IDataSaver {
    void save(ArrayList<IFolder> folders);
}
