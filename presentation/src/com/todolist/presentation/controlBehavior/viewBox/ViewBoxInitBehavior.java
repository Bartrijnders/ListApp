package com.todolist.presentation.controlBehavior.viewBox;

import com.todolist.presentation.controllers.HomePageController;
import javafx.scene.layout.VBox;

public class ViewBoxInitBehavior {
    public void initBehavior(VBox vBox, HomePageController homePageController){
        ViewBoxRightClick viewBoxRightClick = new ViewBoxRightClick();
        ViewBoxCreateCMenu viewBoxCreateCMenu = new ViewBoxCreateCMenu();
        ViewBoxEditOption viewBoxEditOption = new ViewBoxEditOption();
        ViewBoxDeleteOption viewBoxDeleteOption = new ViewBoxDeleteOption();

        //adding the behavior to the viewbox.
        viewBoxCreateCMenu.createCMenu(vBox);
        viewBoxEditOption.addEditOption(vBox);
        viewBoxDeleteOption.addDeleteTaskOption(vBox, homePageController);
        viewBoxRightClick.addRightClick(vBox);
    }
}
