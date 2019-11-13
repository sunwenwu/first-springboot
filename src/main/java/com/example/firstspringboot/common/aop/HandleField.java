package com.example.firstspringboot.common.aop;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HandleField {

    boolean encrypFieldName() default false;

    boolean decrypFieldName() default false;
}
