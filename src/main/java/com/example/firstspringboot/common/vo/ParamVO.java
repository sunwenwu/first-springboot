package com.example.firstspringboot.common.vo;

import com.example.firstspringboot.common.aop.HandleField;
import lombok.Data;

/**
 * @Author :sunwenwu
 * @Date : 2019/11/11 18:54
 * @Description :
 */
@Data
public class ParamVO {

    @HandleField
    private String InputParam;
}
