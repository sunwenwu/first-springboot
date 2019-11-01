package com.example.firstspringboot.common.utils;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by admin on 2018/8/6.
 */
public class ValidataUtil {


    private static Validator validator = Validation.byProvider(HibernateValidator.class)
            .configure().failFast(true).buildValidatorFactory().getValidator();

    public static <T> String validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        // 抛出检验异常
        String errorMsg = "";
        if (constraintViolations.size() > 0) {
            errorMsg = constraintViolations.iterator().next().getMessage();
        }
        return errorMsg;
    }

}
