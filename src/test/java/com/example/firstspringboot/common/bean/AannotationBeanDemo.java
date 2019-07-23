package com.example.firstspringboot.common.bean;

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
}
