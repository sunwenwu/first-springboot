package com.example.firstspringboot.now.http.exception;


/**
 * @Author :sunwenwu
 * @Date : 2021/1/15
 * @Description : http自定义异常
 */
public class IpnHttpProcessException extends RuntimeException {
    private static final long serialVersionUID = -2749168865492921426L;

    public IpnHttpProcessException(Exception e) {
        super(e);
    }

    /**
     * @param msg 消息
     */
    public IpnHttpProcessException(String msg) {
        super(msg);
    }

    /**
     * @param message 异常消息
     * @param e       异常
     */
    public IpnHttpProcessException(String message, Exception e) {
        super(message, e);
    }

}
