package com.example.firstspringboot.common.demo.ExceptionTest;

/**
 * @author: sunwenwu
 * @Date: 2018/11/24 18：13
 * @Description:
 */
public class MainTest {

    public static void main(String[] args) {

        try {
            test();
        }
        catch (BusinessException e){
            System.out.println("BussinessException");
        } catch (Exception e){
            System.out.println("exception");
        }

    }

    public static void test(){

        throw new BusinessException(1,"test");
    }

    private static void test2(){

        int i = 1/0;
    }
}
