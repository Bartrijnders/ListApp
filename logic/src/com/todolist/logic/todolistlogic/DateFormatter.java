package com.todolist.logic.todolistlogic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    public static Date format(Date date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "dd/MM/yyyy");
            return formatter.parse(formatter.format(date));
        } catch (ParseException e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            return null;
        }
    }
}
