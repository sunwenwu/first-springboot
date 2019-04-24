package com.example.firstspringboot.service.vo;

import lombok.Data;

/**
 * @author: sunwenwu
 * @Date: 2019/4/24 10：35
 * @Description:
 */
@Data
public class WarehouseProdLimitVO {

    //仓别
    private String warehouseType;

    //物料编码
    private String productNo;

    //物料最小数量
    private Integer minNum;

    //物料最大数量
    private Integer maxNum;
}
