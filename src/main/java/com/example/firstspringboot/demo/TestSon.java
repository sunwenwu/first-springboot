package com.example.firstspringboot.demo;

/**
 * @Author :sunwenwu
 * @Date : 2021/2/26 11:57
 * @Description :
 */
public class TestSon extends TestFu{

  /*  @Override
    public void public1(){
        System.out.println("public2...");
    }*/

    @Override
    protected void protected1(){
        System.out.println("protected2...");
    }

    private void private1(){
        System.out.println("private2...");
    }


    public static void main(String[] args) {

        TestSon tt = new TestSon();

        tt.public1();
        tt.protected1();
        tt.private1();

    }
}
