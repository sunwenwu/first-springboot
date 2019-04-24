package com.example.firstspringboot.service.vo;

import lombok.Data;

/**
 * @author: sunwenwu
 * @Date: 2019/4/24 10：34
 * @Description: 检查数据
 */
@Data
public class CheckProductVO {

    //送检号
    private String checkNo;

    //订单行号
    private String orderLineNo;

    //物料编码
    private String productNo;

    //库存数量
    private Integer invNum;

    //仓别
    private String warehouseType;
}
