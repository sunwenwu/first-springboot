package com.example.firstspringboot.common.bean;

import com.alibaba.fastjson.JSONObject;
import com.example.firstspringboot.common.demo.Annotation.ExceptioFiledAannotation;
import lombok.Data;

/**
 * @author: sunwenwu
 * @Date: 2019/7/22 15：52
 * @Description:
 */
@Data
public class AannotationBeanDemo {

    @ExceptioFiledAannotation
    private String userName;

    @ExceptioFiledAannotation
    private String sex;

    @ExceptioFiledAannotation
    private Integer age;


    public static void main(String[] args) {
        AannotationBeanDemo demo = new AannotationBeanDemo();

        demo.setUserName("haha");

        String s = JSONObject.toJSONString(demo);

        System.out.println(s);


        AannotationBeanDemo demo2 = JSONObject.parseObject(s.replace("userName", "sex"), AannotationBeanDemo.class);

        System.out.println(JSONObject.toJSONString(demo2));
    }
}
