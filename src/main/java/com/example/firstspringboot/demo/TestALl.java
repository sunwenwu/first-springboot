package com.example.firstspringboot.demo;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @Author :sunwenwu
 * @Date : 2020/5/11 17:25
 * @Description :
 */
public class TestALl {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        double floor = Math.floor(80/9);

//        System.out.println(floor);


        LocalDateTime withOne = LocalDateTime.now().minusWeeks(1).with(DayOfWeek.MONDAY);
        System.out.println(withOne.format(dateTimeFormatter));

        LocalDateTime with7 =  LocalDateTime.now().minusWeeks(1).with(DayOfWeek.SUNDAY);
        System.out.println(with7.format(dateTimeFormatter));


        LocalDateTime withStart = LocalDateTime.now().minusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(withStart.format(dateTimeFormatter));

        LocalDateTime withLast = LocalDateTime.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(withLast.format(dateTimeFormatter));


    }





}
