package com.todolist.presentation.controlbehavior.taskcomponents;

import com.todolist.logic.operations.AllTasksGetter;
import com.todolist.logic.operations.TaskGetterDate;
import com.todolist.presentation.controlbehavior.viewBox.ViewBoxInitBehavior;
import com.todolist.presentation.controllers.HomePageController;

public class viewBoxFiller {
    public void fill(HomePageController homePageController) {
        HomePageController home = homePageController;
        TaskComponentCollector taskComponentCollector = new TaskComponentCollector();
        RootFolderCheck rootFolderCheck = new RootFolderCheck();
        if(rootFolderCheck.check(homePageController)){
            TaskGetterDate taskGetterDate = new TaskGetterDate();
            home.getViewVbox().getChildren().addAll(taskComponentCollector.collectComponents(taskGetterDate.getTaskByDate(home.getActiveFolder(),home.getSelectedDate())));
        }else{
            AllTasksGetter allTasksGetter = new AllTasksGetter();
            home.getViewVbox().getChildren().addAll(taskComponentCollector.collectComponents(allTasksGetter.getTasks(home.getAllFolders(),home.getSelectedDate())));

        }
        ViewBoxInitBehavior viewBoxInitBehavior = new ViewBoxInitBehavior();
        viewBoxInitBehavior.initBehavior(home.getViewVbox(),home);


    }
}
