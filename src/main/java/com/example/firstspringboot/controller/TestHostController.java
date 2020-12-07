package com.example.firstspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author :sunwenwu
 * @Date : 2020/8/20 14:54
 * @Description :
 */
@Controller
public class TestHostController {


    @GetMapping("/test")
    public String test(HttpServletRequest request){

        return request.getRequestURL() + request.getRequestURI();
    }
}
