package com.example.firstspringboot.common.bean;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Author :sunwenwu
 * @Date : 2019/11/1 18:30
 * @Description :
 */
@Data
public class ValidDemoBean {

    @NotBlank(message = "fater不能为空")
    private String father;

}
