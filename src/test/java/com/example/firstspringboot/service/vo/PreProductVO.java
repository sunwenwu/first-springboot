package com.example.firstspringboot.service.vo;

import lombok.Data;

/**
 * @author: sunwenwu
 * @Date: 2019/4/24 10：34
 * @Description: 预开单数据
 */
@Data
public class PreProductVO {

    //订单号
    private String orderNo;

    //订单行号
    private String orderLineNo;

    //物料编码
    private String productNo;

    //物料数量
    private Integer prodNum;

    //仓别
    private String warehouseType;
}
