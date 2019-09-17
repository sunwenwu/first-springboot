package com.example.firstspringboot.common.utils;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @Author :sunwenwu
 * @Date : 2019/9/16 18:47
 * @Description :
 */
public class JsonUtil {

    /**
     *
     * 驼峰转大写/下划线
     * @param param
     * @return
     */
    public static String toUC(String param) {
        Pattern p = Pattern.compile("[A-Z]");
        if (param == null || param.equals("")) {
            return "";
        }
        StringBuilder builder = new StringBuilder(param);
        Matcher mc = p.matcher(param);
        int i = 0;
        while (mc.find()) {
            builder.replace(mc.start() + i, mc.end() + i, "_"
                    + mc.group().toLowerCase());
            i++;
        }

        if ('_' == builder.charAt(0)) {
            builder.deleteCharAt(0);
        }
        return builder.toString().toUpperCase();
    }
    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
     * 例如：HELLO_WORLD->HelloWorld
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String camelName(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String camels[] = name.split("_");
        for (String camel :  camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    public static String camelName2(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        }
        // 用下划线将原始字符串分割
        String camels[] = name.split("_");
        for (String camel :  camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * 驼峰格式转大写
     * @param o1
     * @return
     */
    public static JSONObject low2Up(JSONObject o1){
        JSONObject o2=new JSONObject();
        Iterator it = o1.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            Object object = o1.get(key);
//                System.out.println(object.getClass().toString());
            if(object.getClass().toString().endsWith("String")){
                o2.put(toUC(key), object);
            }else if(object.getClass().toString().endsWith("JSONObject")){
                o2.put(toUC(key), low2Up((JSONObject)object));
            }else if(object.getClass().toString().endsWith("JSONArray")){
                o2.put(toUC(key), low2Array(o1.getJSONArray(key)));
            }else{
                o2.put(toUC(key), object);
            }
        }
        return o2;
    }

    public static JSONArray low2Array(JSONArray o1){
        JSONArray o2 = new JSONArray();
        for (int i = 0; i < o1.size(); i++) {
            Object jArray=o1.getJSONObject(i);
            if(jArray.getClass().toString().endsWith("JSONObject")){
                o2.add(low2Up((JSONObject)jArray));
            }else if(jArray.getClass().toString().endsWith("JSONArray")){
                o2.add(low2Array((JSONArray)jArray));
            }
        }
        return o2;
    }

    /**
     * 大写 转驼峰格式
     * @param o1
     * @return
     */
    public static JSONObject up2Low(JSONObject o1){
        JSONObject o2=new JSONObject();
        Iterator it = o1.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            Object object = o1.get(key);

            if(object.getClass().toString().endsWith("String")){
                o2.put(camelName(key), object);
            }else if(object.getClass().toString().endsWith("JSONObject")){
                o2.put(camelName(key), up2Low((JSONObject)object));
            }else if(object.getClass().toString().endsWith("JSONArray")){
                o2.put(camelName(key), upArray(o1.getJSONArray(key)));
            }else{
                o2.put(camelName(key), object);
            }
        }
        return o2;
    }
    public static JSONArray upArray(JSONArray o1){
        JSONArray o2 = new JSONArray();
        for (int i = 0; i < o1.size(); i++) {
            Object object = o1.get(i);
            if(object.getClass().toString().endsWith("String")
                    || object.getClass().toString().endsWith("Integer")
                    || object.getClass().toString().endsWith("Double")
                    || object.getClass().toString().endsWith("BigDecimal")){
                o2.add(object);
            }else{
                Object jArray=o1.getJSONObject(i);
                if(jArray.getClass().toString().endsWith("JSONObject")){
                    o2.add(up2Low((JSONObject)jArray));
                }else if(jArray.getClass().toString().endsWith("JSONArray")){
                    o2.add(upArray((JSONArray)jArray));
                }
            }
        }
        return o2;
    }


    public static void main(String[] args) {
        JSONObject js1 = new JSONObject();
        JSONObject js2 = new JSONObject();
        JSONObject js3 = new JSONObject();

        js1.put("sun_wen_wu","test");
        js1.put("ai_hao","篮球");
        js1.put("aBc","篮球");
        js2.put("j_array2","元素2");
        js3.put("j_array3","元素3");

        JSONArray ja = new JSONArray();
        ja.add(js2);
        ja.add(js3);

        js1.put("Array",ja);

        System.out.println(js1.toJSONString());

        JSONObject jsonObject = up2Low(js1);

        System.out.println(jsonObject.toJSONString());

    }
}
