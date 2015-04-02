package com.juliakram.labs.movietickets.util;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import static java.time.LocalDateTime.ofInstant;
import static java.time.ZoneId.systemDefault;

/**
 * Created by yuliya.kramarenko on 06.01.2015.
 */
public class DateHelper {

    public static final LocalDate TODAY = LocalDate.now();

    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(systemDefault()).toInstant());
    }

    public static LocalDate toLocalDate(Date date) {
        Instant instant = Instant.ofEpochMilli((date).getTime());
        return ofInstant(instant, systemDefault()).toLocalDate();
    }

    public static Date refineDate(Date date) {
        Instant instant = Instant.ofEpochMilli((date).getTime());
        LocalDate localDate = ofInstant(instant, systemDefault()).toLocalDate();

        return java.util.Date.from(localDate.atStartOfDay().atZone(systemDefault()).toInstant());
    }
}
