package com.todolist.dataacces.filedatehandlers;

import java.io.File;

public class SaveFileFinder {
    private static final String pathToFile = "dataacces/src/com/todolist/dataacces/savefiles/savefile.txt";


    public static File getSaveFile() {
        try {
            File file = new File(pathToFile);
            return file;
        } catch (Exception e) {
            System.out.println("there was an error in getting the SaveFile:\n" + e.getMessage());
        }
        return null;
    }
}
