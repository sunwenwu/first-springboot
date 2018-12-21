package com.example.firstspringboot.common.demo.other.test;

import com.example.firstspringboot.common.bean.OrderInfo;
import com.example.firstspringboot.common.bean.OrderStatusEnum;
import com.example.firstspringboot.common.bean.PayStatusEnum;

/**
 * @author: sunwenwu
 * @Date: 2018/12/18 16：05
 * @Description:
 */
public class OrderTest {

    public static void main(String[] args) {
        OrderInfo order = new OrderInfo();
        order.setOrderStatus(new Short("33"));
        order.setPayStatus(new Short("1"));
        if (order.getPayStatus() == PayStatusEnum.PAID.getStatusCode() && order.getOrderStatus() == OrderStatusEnum.WAIT_REFUND.getStatusCode()) {
            System.out.println("ok");
        }
    }
}
