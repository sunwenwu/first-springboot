package com.example.firstspringboot.common.demo.other.test;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;

import java.util.HashMap;
import java.util.Map;

public class JsonMapTest {
    public static void main(String[] args) {

        Map<String,String> map = new HashMap<>();
        map.put("cc","33");
        map.put("aa","11");
        map.put("bb","22");

        String s = JSONObject.toJSONString(map);

        System.out.println(s);

        Map map1 = JSONObject.parseObject(s, Map.class);

        System.out.println(map1.get("aa"));

        String signCheckContentV1 = AlipaySignature.getSignCheckContentV1(map);

        System.out.println(signCheckContentV1);
    }
}
