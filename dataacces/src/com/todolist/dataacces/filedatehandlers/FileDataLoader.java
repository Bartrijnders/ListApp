package com.todolist.dataacces.filedatehandlers;

import com.todolist.domain.interfaces.IFolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class FileDataLoader implements IDataLoader {

    @Override
    public ArrayList<IFolder> load() {
        ArrayList<IFolder> output = null;
        File file = SaveFileFinder.getSaveFile();
        assert file != null;
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            output = (ArrayList<IFolder>) objectInputStream.readObject();
            return output;

        } catch (Exception e) {
            System.out.println("ERROR in file reading\n" + e.getMessage());
            return new ArrayList<>();
        } finally {
            if (output != null) {
                System.out.println("reading save file succeeded");
            } else {
                System.out.println("reading save file has failed");
            }
        }

    }
}
