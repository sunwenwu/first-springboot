package com.example.firstspringboot.common.bean;

/**
 * @author: sunwenwu
 * @Date: 2018/11/2 14：40
 * @Description:
 */
public class EnumTest {
    public static void main(String[] args) {
        DemoEnum.AA.test();
        System.out.println("-----");
        int k = 0;
        int n = -12;
        n = -n;
        k++;
        System.out.println("第"+k+"种"+n);



        n =  (0xffffffff ^ n) + 1;
        k++;
        System.out.println("第"+k+"种"+n);


        n =  ~--n;
        k++;
        System.out.println("第"+k+"种"+n);




    }
}
