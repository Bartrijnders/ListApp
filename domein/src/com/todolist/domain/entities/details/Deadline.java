package com.todolist.domain.entities.details;

import com.todolist.domain.interfaces.IDetail;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline implements IDetail, Serializable {

    private String type;
    private LocalDateTime dateTime;

    public Deadline(LocalDateTime dateTime) {
        type = "Deadline";
        this.dateTime = dateTime;
    }

    @Override
    public String getValue() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dateTime.format(format);
    }

    @Override
    public String getType() {
        return type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
