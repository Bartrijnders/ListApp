package com.todolist.logic.todolistlogic;

import com.todolist.domain.interfaces.IFolder;
import com.todolist.domain.interfaces.ITask;

import java.util.ArrayList;
import java.util.Date;

public class AllTasksGetter {
    public ArrayList<ITask> getTasks(ArrayList<IFolder> folders, Date date){
        try{
            ArrayList<ITask> output = new ArrayList();
            date = DateFormatter.format(date);
            for(IFolder folder : folders){
                for(ITask task : folder.getItems()){
                    if(task != null){
                        Date taskdate = DateFormatter.format(task.getDateOfCreation());
                        if(taskdate.compareTo(date)==0){
                            output.add(task);
                        }
                    }
                }
            }

            return output;
        }catch (Exception e){
            return null;
        }
    }
}
