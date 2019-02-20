package com.example.firstspringboot.common.bean;


import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author: sunwenwu
 * @Date: 2018/11/6 14：46
 * @Description:
 */
public class Test222 {

    public static void main(String[] args) {

        Integer convert = convert("111", Integer.class);
        Long convert2 = convert("22222", Long.class);

        System.out.println(convert+"  "+convert.getClass().getName());
        System.out.println(convert2+"  "+convert2.getClass().getName());

    }

    private static  <T> T convert(String param, Class<T> clazz) {
        if (NumberUtils.isCreatable(param)) {
            if(Integer.class.isAssignableFrom(clazz)){
                return (T) NumberUtils.createInteger(param);
            }else if(Long.class.isAssignableFrom(clazz)){
                return (T) NumberUtils.createLong(param);
            }
        }
        return null;
    }
}
