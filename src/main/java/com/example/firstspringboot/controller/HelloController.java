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


}

