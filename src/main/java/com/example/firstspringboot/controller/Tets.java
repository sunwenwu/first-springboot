package com.example.firstspringboot.controller;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author :sunwenwu
 * @Date : 2020/9/22 15:31
 * @Description :
 */
public class Tets {



    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();


        LocalDateTime localDateTime = LocalDateTime.now().plusDays(1L);

        System.out.println(now.isAfter(localDateTime));


        Map<Integer, String> jobThreadRepository = new HashMap<>();
//        Map<Integer, String> jobThreadRepository = new ConcurrentHashMap<>();

        System.out.println(jobThreadRepository.put(1,"111"));
        System.out.println(jobThreadRepository.put(1,"222"));




        LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.of("Asia/Shanghai"));
        System.out.println(time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")));


    }
}
