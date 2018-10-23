package com.example.firstspringboot.common.vo;

import org.springframework.context.ApplicationEvent;

/**
 * @author: sunwenwu
 * @Date: 2018/10/23 17：03
 * @Description:
 */
public class DemoEvent extends ApplicationEvent {

    private String code;
    private String msg;

    public DemoEvent(Object source) {
        super(source);
    }

    public DemoEvent(Object source, String code, String msg) {
        super(source);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
