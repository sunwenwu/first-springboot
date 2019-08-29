package com.example.firstspringboot.controller;

import com.example.firstspringboot.dao.TbCartMapper;
import com.example.firstspringboot.service.TbCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: sunwenwu
 * @Date: 2018/10/17 11：44
 * @Description:
 */
@RestController
@RequestMapping
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    TbCartService tbCartService;


    @Autowired
    private TbCartMapper tbCartMapper;

    //访问/hello或者/hi任何一个地址，都会返回一样ss的结果
    @RequestMapping(value = {"/hello","/hi"},method = RequestMethod.GET)
    public String say(){
        logger.info("log test...");
        return "hi you!!!";
    }

    @ResponseBody
    @RequestMapping(value = {"/getTao"},method = RequestMethod.GET)
    public Object getTao(){
        logger.info("log test...");
        return "log test...";
//        return tbCartService.selectByPrimaryKey(4L);
    }

    @ResponseBody
    @RequestMapping(value = {"/getTao1"},method = RequestMethod.GET)
    public Object getTao2(){
        logger.info("log test...");
        return "log test111...";
//        return tbCartMapper.selectByPrimaryKey(5L);
    }



    @ResponseBody
    @RequestMapping(value = {"/getReader"},method = RequestMethod.POST)
    public Object getReader(HttpServletRequest request){
        logger.info("log getReader...");
        return getRequestBodyString(request);
    }


    @ResponseBody
    @RequestMapping(value = {"/getMap"},method = RequestMethod.POST)
    public Object getMap(HttpServletRequest request){

        logger.info("log getMap...");
        return request.getParameterMap();
    }


    public static String getRequestBodyString(HttpServletRequest request) {
        String reportContent = "";
        try {
            BufferedReader reader = request.getReader();
            StringBuilder reportBuilder = new StringBuilder();
            String tempStr = "";
            while ((tempStr = reader.readLine()) != null) {
                reportBuilder.append(tempStr);
            }
            reportContent = reportBuilder.toString();
        } catch (IOException e) {
            logger.error("获取异常", e);
        }
        logger.info("获取请求信息为{}", reportContent);
        return reportContent;
    }


    @ResponseBody
    @RequestMapping(value = {"/getAliTest"},method = RequestMethod.POST)
    public Object getAliTest(HttpServletRequest request){
        Map<String,String> resultMap2 = new HashMap<>();

        /*Map<String, String[]> resultMap = request.getParameterMap();
        for (Iterator iter = resultMap.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) resultMap.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            resultMap2.put(name, valueStr);
        }*/

        String requestBodyString = getRequestBodyString(request);

        System.out.println(requestBodyString);

        return resultMap2;
    }
}

