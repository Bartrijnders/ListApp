package com.todolist.logic.operations;

import com.todolist.domain.interfaces.IFolder;
import com.todolist.domain.interfaces.ITask;

import java.util.ArrayList;
import java.util.Date;

public class TaskGetterDate {
    public ArrayList<ITask> getTaskByDate(IFolder folder, Date date){
            try{
                ArrayList<ITask> output = new ArrayList();
                date = DateFormatter.format(date);
                for(ITask task : folder.getItems()){
                    if(task != null){
                        Date taskdate = DateFormatter.format(task.getDateOfCreation());
                        if(taskdate.compareTo(date)==0){
                            output.add(task);
                        }
                    }
                }
                return output;
            }catch (Exception e){
                return null;
            }



    }
}
