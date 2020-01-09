package com.todolist.presentation.controlbehavior.taskcomponents;

import com.todolist.presentation.controllers.HomePageController;

public class RootFolderCheck {

    //checks if the selected folder is the rootfolder.
    //returns true if active folder is not the rootfolder.
    //returns false if the active folder is the rootfolder.
    //returns null if the active folder is null.
    public Boolean check(HomePageController homePageController) {
        return (homePageController.getActiveFolder() != null && homePageController.getActiveFolder() != homePageController.getRootFolder());
    }

}
