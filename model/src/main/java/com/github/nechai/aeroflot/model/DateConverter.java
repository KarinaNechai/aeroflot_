package com.github.nechai.aeroflot.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public  class DateConverter {
    public static LocalDateTime DateConverter(LocalDateTime locDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = locDate.format(formatter);
        locDate = LocalDateTime.parse(formattedDateTime, formatter);
        return locDate;
    }
}
