package com.example.firstspringboot.common.bean;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author :sunwenwu
 * @Date : 2019/9/10 17:32
 * @Description :
 */
@Data
public class NullFiledDemo {

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "地址不能为空")
    private String address;
}
