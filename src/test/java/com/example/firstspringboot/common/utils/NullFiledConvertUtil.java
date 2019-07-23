package com.example.firstspringboot.common.utils;

import com.example.firstspringboot.common.demo.Annotation.ExceptioFiledAannotation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * @author: sunwenwu
 * @Date: 2019/7/22 16：14
 * @Description:
 */
public class NullFiledConvertUtil {

    private static Logger logger = LoggerFactory.getLogger(NullFiledConvertUtil.class);

    public static <T> void convert(T t){
        Field[] fields = t.getClass().getDeclaredFields();

        for (Field field:fields) {
            ExceptioFiledAannotation annotation = field.getAnnotation(ExceptioFiledAannotation.class);
            if (annotation != null) {

                field.setAccessible(true);

                if (String.class.isAssignableFrom(field.getType())) {
                    try {
                        Object o = field.get(t);
                        if (o == null || StringUtils.isEmpty((String)o)) {
                            field.set(t,"-1");
                        }
                    } catch (Exception e) {
                        logger.error("数据转换异常",e);
                    }
                }
            }
        }
    }
}
