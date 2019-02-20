package com.example.firstspringboot.demo;

import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: sunwenwu
 * @Date: 2019/1/2 18：06
 * @Description:
 */
public class Test {
    String name;

  public static void main(String[] args) {
      SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   /* System.out.println(sm.format(DateUtils.truncate(new Date(), Calendar.DATE)));
    System.out.println(sm.format(DateUtils.truncate(new Date(), Calendar.DATE)));
    //*/


      Date currentDate = new Date();
    /*  Date beginTime = DateUtils.truncate(currentDate, Calendar.DATE);
      Date yesterdayEndTime = DateUtils.addDays(currentDate, -1);
      Date yesterdayBeginTime = DateUtils.addDays(beginTime, -1);

    System.out.println(sm.format(currentDate));
    System.out.println(sm.format(beginTime));
    System.out.println(sm.format(yesterdayEndTime));
    System.out.println(sm.format(yesterdayBeginTime));*/


      Date startDate = getStartDate(currentDate);
      Date endDate = getEndDate(currentDate);

      int i = daysBetween(startDate, endDate)+1;

    System.out.println("天数差："+i);

      Date nextDayStart = getNextDay(startDate, -i);
      Date nextDayend = getNextDay(endDate, -i);

      System.out.println(sm.format(startDate));
      System.out.println(sm.format(endDate));
    System.out.println(sm.format(nextDayStart));
    System.out.println(sm.format(nextDayend));
  }

    public static Date getNextDay(Date date,int days) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(Calendar.DAY_OF_MONTH, days);
      date = calendar.getTime();
      return date;
   }


    public static final int daysBetween(Date early, Date late) {

        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        //设置时间为0时
        calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
        calst.set(java.util.Calendar.MINUTE, 0);
        calst.set(java.util.Calendar.SECOND, 0);
        caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
        caled.set(java.util.Calendar.MINUTE, 0);
        caled.set(java.util.Calendar.SECOND, 0);
        //得到两个日期相差的天数
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
                .getTime().getTime() / 1000)) / 3600 / 24;

        return days;
    }


    public static Date getStartDate(Date startDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date getEndDate(Date endDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);

        return calendar.getTime();
    }
}
