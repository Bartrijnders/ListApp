package com.todolist.dataacces.filedatehandlers;

import com.todolist.domain.interfaces.IFolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class
FileDataSaver implements IDataSaver {


    public void save(ArrayList<IFolder> folders) {
        File file = SaveFileFinder.getSaveFile();
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(folders);
            System.out.println("List succesfully written to file");

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("error in saving the list");
        }
    }
}
