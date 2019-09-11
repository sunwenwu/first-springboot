package com.example.firstspringboot.common.demo.Annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConvertAnnotation {

    String mapKey();
}
