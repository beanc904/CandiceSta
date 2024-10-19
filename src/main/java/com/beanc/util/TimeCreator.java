package com.beanc.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeCreator {
    private String time;

    public TimeCreator() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
        time = localDate.format(formatter);
    }

    @Override
    public String toString() {
        return time;
    }
}
