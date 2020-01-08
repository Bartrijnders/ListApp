package com.todolist.dataacces.filedatehandlers;


import com.todolist.domain.interfaces.IFolder;

import java.util.ArrayList;

public interface IDataLoader {
    ArrayList<IFolder> load();
}
