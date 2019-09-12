package com.example.firstspringboot.common.bean;

import com.example.firstspringboot.common.demo.Annotation.ConvertAnnotation;
import lombok.Data;

/**
 * @Author :sunwenwu
 * @Date : 2019/9/11 18:21
 * @Description :
 */
@Data
public class CovertDemo {

    @ConvertAnnotation(mapKey = "name_test")
    private String name;

    @ConvertAnnotation(mapKey = "address_test")
    private String address;

    @ConvertAnnotation(mapKey = "money_fen",isFen2Yuan = true)
    private Long money;

    @ConvertAnnotation(mapKey = "aihao",isSonFiled = true)
    private String aihao;

    @ConvertAnnotation(mapKey = "shengaooo",isSonFiled = true,isFen2Yuan = true)
    private Long shengao;

    @ConvertAnnotation(mapKey = "tizhong",isSonFiled = true)
    private Long tizhong;
}
