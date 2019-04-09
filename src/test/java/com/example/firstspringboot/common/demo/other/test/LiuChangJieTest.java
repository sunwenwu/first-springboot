package com.example.firstspringboot.common.demo.other.test;


import com.alibaba.fastjson.JSONObject;

/**
 * @author: sunwenwu
 * @Date: 2019/3/14 15：00
 * @Description:
 */
public class LiuChangJieTest {

  public static void main(String[] args) {
    String s = "{\"id\":2,\"name\":\"ha111ha\"}";
    Studentdd studentdd = get(s,Studentdd.class);
    System.out.println(studentdd);



    String s2 = "{\"id\":66,\"age\":\"100岁\"}";
    ManTest manTest = get(s2,ManTest.class);
    System.out.println(manTest);

  }


  public static <T> T get (String s,Class<T> clazz) {
    return JSONObject.parseObject(s,clazz);
  }




  static class Studentdd {
    private int id;
    private String name;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return "Studentdd{" +
              "id=" + id +
              ", name='" + name + '\'' +
              '}';
    }
  }

  static class ManTest {
    private int id;
    private String age;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getAge() {
      return age;
    }

    public void setAge(String age) {
      this.age = age;
    }

    @Override
    public String toString() {
      return "ManTest{" +
              "id=" + id +
              ", age='" + age + '\'' +
              '}';
    }
  }
}
