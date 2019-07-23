package com.example.firstspringboot.common.demo.Annotation;

import com.example.firstspringboot.common.bean.AannotationBeanDemo;
import com.example.firstspringboot.common.utils.NullFiledConvertUtil;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author: sunwenwu
 * @Date: 2019/7/22 15：53
 * @Description:
 */
public class ExceptioFiledAannotationTest {

  public static void main(String[] args) throws Exception{

      AannotationBeanDemo demo = new AannotationBeanDemo();
      demo.setUserName("");
      System.out.println("注解前："+demo);

      /*Field[] fields = AannotationBeanDemo.class.getDeclaredFields();


      for (Field field:fields) {
          ExceptioFiledAannotation annotation = field.getAnnotation(ExceptioFiledAannotation.class);
          if (annotation != null) {

                  field.setAccessible(true);

                  Object o = field.get(demo);

                  if (o == null) {
                      field.set(demo,"-1");
                  }

          }
      }*/

//      conver(demo);
    NullFiledConvertUtil.convert(demo);
    System.out.println("注解后1："+demo);
    //
  }


  public static <T> void conver(T t) throws Exception{
      Field[] fields = t.getClass().getDeclaredFields();

      for (Field field:fields) {
          ExceptioFiledAannotation annotation = field.getAnnotation(ExceptioFiledAannotation.class);
          if (annotation != null) {

              field.setAccessible(true);

              Object o = field.get(t);

              if (String.class.isAssignableFrom(field.getType()) && (o == null || StringUtils.isEmpty((String)o))) {
                  field.set(t,"-1");
              }

          }
      }
  }

}
