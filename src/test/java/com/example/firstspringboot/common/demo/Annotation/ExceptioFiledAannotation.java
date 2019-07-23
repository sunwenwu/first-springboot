package com.example.firstspringboot.common.demo.Annotation;

import java.lang.annotation.*;

/**
 * @author: sunwenwu
 * @Date: 2019/7/22 15：43
 * @Description: 指定需要校验异常的字段的权限
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExceptioFiledAannotation {
}
