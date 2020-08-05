package com.example.firstspringboot.demo;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * @Author :sunwenwu
 * @Date : 2020/5/11 17:25
 * @Description :
 */
public class TestMap {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        Map<String, String> objectObjectMap = Collections.emptyMap();

        Map<String, String> objectObjectMap2 = Collections.emptyMap();

        System.out.println(objectObjectMap.equals(objectObjectMap2));
        System.out.println(objectObjectMap2);

    }





}
