package com.todolist.domain.entities.details;

import com.todolist.domain.enums.Priorities;
import com.todolist.domain.interfaces.IDetail;

import java.io.Serializable;

public class Priority implements IDetail, Serializable {

    private Priorities prio;
    private String type;

    public Priority(Priorities priority) {
        this.prio = priority;
        type = "Priority";
    }

    @Override
    public String getValue() {
        return prio.toString();
    }

    @Override
    public String getType() {
        return type;
    }
}
