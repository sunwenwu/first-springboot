package com.example.firstspringboot.service.vo;

import lombok.Data;

/**
 * @author: sunwenwu
 * @Date: 2019/4/24 10：35
 * @Description:
 */
@Data
public class NeedSingleOpenOrderProdVO {

    //仓别
    private String warehouseType;

    //物料编码
    private String productNo;

    //订单行号
    private String orderLineNo;

    //开几次
    private Integer openTimes;

    //物料剩余数量
    private Integer surplusNum;

    //物料最大数量
    private Integer maxNum;
}
