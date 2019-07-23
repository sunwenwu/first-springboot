package com.example.firstspringboot.common.demo.other.test;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: sunwenwu
 * @Date: 2019/6/13 10：56
 * @Description:
 */
public class TestStream {

  public static void main(String[] args) {
      List<String> strings = Arrays.asList("1", "", "2");
      List<String> sssss = new ArrayList<>();
      int i = 0;
      for (String s : strings) {
          if (!StringUtils.isEmpty(s)) {
              i++;
          }
      }

    System.out.println("i="+i);

      int size = sssss.stream().filter(ss -> !StringUtils.isEmpty(ss)).collect(Collectors.toList()).size();

      System.out.println("size="+size);

  }
}
