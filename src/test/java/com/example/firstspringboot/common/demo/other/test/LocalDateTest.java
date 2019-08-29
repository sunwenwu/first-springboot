package com.example.firstspringboot.common.demo.other.test;

import java.time.LocalDateTime;

public class LocalDateTest {

    public static void main(String[] args) {

        LocalDateTime end = LocalDateTime.now().minusMinutes(3);
        LocalDateTime start = end.minusDays(1);

        System.out.println(start);
        System.out.println(end);

    }
}
