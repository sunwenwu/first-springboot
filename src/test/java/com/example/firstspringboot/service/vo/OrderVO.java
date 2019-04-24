package com.example.firstspringboot.service.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: sunwenwu
 * @Date: 2019/4/24 10：32
 * @Description:
 */
@Data
public class OrderVO {
    //开单号
    private String openOrderNo;

    private List<OrderVOItem> orderVOItems;
}
