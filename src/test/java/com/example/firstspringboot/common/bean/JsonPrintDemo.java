package com.example.firstspringboot.common.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @Author :sunwenwu
 * @Date : 2019/9/10 17:32
 * @Description :
 */
@Setter
@Getter
@ToString(exclude = {"id"})
public class JsonPrintDemo {

    @JSONField(serialize=false)
    private Long id;

    private String name;

    private String address;


    public static void main(String[] args) {
        JsonPrintDemo jpd = new JsonPrintDemo();

        jpd.setId(1L);
        jpd.setName("haha");
        jpd.setAddress("beijing");


        String s = JSON.toJSONString(jpd);


        System.out.println(s);
        System.out.println(jpd.toString());

    }
}
