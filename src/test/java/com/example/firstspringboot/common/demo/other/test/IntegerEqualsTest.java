package com.example.firstspringboot.common.demo.other.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: sunwenwu
 * @Date: 2018/12/27 10：33
 * @Description:
 */
public class IntegerEqualsTest {
  public static void main(String[] args) {
    //

   /*   int i = 128;
      int j = 129;

      Integer ii = 128;
      Integer iii = 128;
      Integer jj = 129;
      Integer jjj = 129;
      Integer kk = 127;
      Integer kkk = 127;

    System.out.println(iii.equals(ii));
    System.out.println(jjj.intValue() == jj);
    System.out.println(kkk == kk);

    System.out.println((char) 45);
    System.out.println((int)'-');*/

   String a = "1-9";
   String b = "5";
   String c = "2-4-6";
   setConcurrency(a);
   setConcurrency(b);
//   setConcurrency(c);


      AtomicInteger ai = new AtomicInteger();

      for (int i = 1;i<=10;i++) {

          ai.addAndGet(i);
          System.out.println("=========================="+ai);
      }
  }

    public static void setConcurrency(String concurrency) {
        try {
            int separatorIndex = concurrency.indexOf(45);
            if (separatorIndex != -1) {
                System.out.println((Integer.parseInt(concurrency.substring(0, separatorIndex))));
                System.out.println((Integer.parseInt(concurrency.substring(separatorIndex + 1, concurrency.length()))));
            } else {
                System.out.println((Integer.parseInt(concurrency)));
            }

        } catch (NumberFormatException var3) {
            throw new IllegalArgumentException("Invalid concurrency value [" + concurrency + "]: only single fixed integer (e.g. \"5\") and minimum-maximum combo (e.g. \"3-5\") supported.");
        }
    }
}
