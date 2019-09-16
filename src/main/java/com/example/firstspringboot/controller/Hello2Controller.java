package com.example.firstspringboot.controller;

import com.example.firstspringboot.dao.TbCartMapper;
import com.example.firstspringboot.service.TbCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author: sunwenwu
 * @Date: 2018/10/17 11：44
 * @Description:
// */
@RestController
@RequestMapping
public class Hello2Controller {

    private static final Logger logger = LoggerFactory.getLogger(Hello2Controller.class);



    //都会返回一样ss的结果
    @RequestMapping(value = {"/callback"},method = RequestMethod.POST)
    public String callback(@RequestBody String reqStr){
        logger.info("log callback..."+reqStr);
        return "hi callback!!!";
    }
    //都会返回一样ss的结果
    @RequestMapping(value = {"/callback2"},method = RequestMethod.POST)
    public String callback2(HttpServletRequest request){
        String reqBodyStr = this.getRequestBodyString(request);


        logger.info("log callback2..."+reqBodyStr);
        return "hi callback2!!!";
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

}

