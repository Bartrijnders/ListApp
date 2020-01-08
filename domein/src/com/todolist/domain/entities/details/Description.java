package com.todolist.domain.entities.details;

import com.todolist.domain.interfaces.IDetail;

import java.io.Serializable;

public class Description implements IDetail, Serializable {

    private String text;
    private String type;

    public Description(String text) {
        this.text = text;
        type = "Description";
    }

    @Override
    public String getValue() {
        return text;
    }

    @Override
    public String getType() {
        return type;
    }
}
