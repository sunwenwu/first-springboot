package com.example.firstspringboot.common.bean;

import lombok.Data;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * @author: sunwenwu
 * @Date: 2018/10/25 10：38
 * @Description:
 */
@Data
public class SonClass extends FatherClass {

    private String sonName;

    public void test2(){
        System.out.println("S....");
    }

    public static void main(String[] args) {
        SonClass f = new SonClass();
        f.setSonName("son");
        f.setFuName("father");
        f.run(f);

    System.out.println( DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"));
    }

    public void run (FatherClass fatherClass) {

        SonClass s = (SonClass) fatherClass;

        System.out.println(s.getFuName()+"======="+s.getSonName());
    }
}
