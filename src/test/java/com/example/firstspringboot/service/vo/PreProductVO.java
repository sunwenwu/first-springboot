package com.example.firstspringboot.service.vo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

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

  public static void main(String[] args) {
      int kk = 1;
      String data = dateToString(new Date(), null);


      for (int i = 1;i<=3;i++) {

      System.out.println("---------"+getOpenOrderNo2(data,kk));
      kk++;

      }
    //
  }

    public static String getOpenOrderNo2(String openDate,Integer serialNumber){
        return "S"+openDate+String.format("%06d", serialNumber);
    }

    public static String dateToString(Date date, String format){
        if(date == null){
            return  "";
        }
        if(StringUtils.isBlank(format)) {
            format="yyyyMMddHHmmssSSS";
        }
        return new SimpleDateFormat(format).format(date);
    }
}
