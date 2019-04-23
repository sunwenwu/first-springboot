package com.example.firstspringboot.service;

import com.example.firstspringboot.common.JsonUtil;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.*;

/**
 * @author: sunwenwu
 * @Date: 2019/4/23 16：03
 * @Description:
 */
public class KaOrderTest2 {

  /**
   * ka二期 开单： 1、取最新的检查为基准 2、校验开单物料的数量是否足够 3、拆单 3.1 判断物料是否超过 送货上限 ，超过的需要单独拆单 3.2 剩下的物料同一仓别的合并为同一次开单
   *
   * <p>4、生成同一批次处理 5、调用接口开单
   *
   * <p>1.同一物料的不同的仓库送货上限是否一样 2.物料一个仓别充足，是否会返回多个仓别
   *
   * <p>甲仓： A：50 B：60 C：30 乙仓： A：60
   *
   * <p>KA单需求物料 A：80 B：40 C：20
   *
   * <p>A：上限40
   *
   * <p>1、甲A 40 2、甲A 10 ，甲B 40，甲C 20 3、乙A 30
   *
   * @param args
   */
  public static void main(String[] args) {

      //准备开单的数据
      List<PreProductVO> preData = getPreData();

      //检查的数据
      List<CheckProductVO> checkData = getCheckData();

      List<WarehouseProdLimitVO> limitData = getLimitData();

      //统计预开单 区分仓别物料数据
      Map<String,Integer> countPreProdMap = new HashMap<>();

      //统计预开单 区分仓别物料行号数据
      Map<String,Integer> countWarehousePreProdMap = new HashMap<>();

      //将预开单数据 按仓别物料行号维度 统计(原始)
      Map<String,PreProductVO> countWarehousePreProdVOMap = new HashMap<>();

      //统计送检返回 区分仓别物料数据
      Map<String,Integer> countCheckWarehousePreProdMap = new HashMap<>();

      //统计送货上下限 区分仓别物料数据
      Map<String,WarehouseProdLimitVO> countLimitWarehousePreProdMap = new HashMap<>();

      //需要单独拆单的商品 超过最大送货限制
      Map<String,NeedSingleOpenOrderProdVO> needSplitOrderPrudMap = new HashMap<>();

      //最终开单数据
      List<OrderVO> openOrderData = new ArrayList<>();


      preData.forEach(ppv -> {

          String wareProdTypeKey = ppv.getWarehouseType() + ":" + ppv.getProductNo();

          //统计物料维度的数量
          Integer prodNum = countPreProdMap.get(wareProdTypeKey);

          if (prodNum == null) {
              countPreProdMap.put(wareProdTypeKey,ppv.getProdNum());
          } else {
              countPreProdMap.put(wareProdTypeKey,ppv.getProdNum() + prodNum);
          }


          //统计仓库维度每种物料的数量
          String wareProdTypeLineKey = ppv.getWarehouseType() + ":" + ppv.getProductNo() + ":"+ppv.getOrderLineNo();

          Integer warehouseProdNum = countPreProdMap.get(wareProdTypeLineKey);

          if (warehouseProdNum == null) {
              countWarehousePreProdMap.put(wareProdTypeLineKey,ppv.getProdNum());
          } else {
              countWarehousePreProdMap.put(wareProdTypeLineKey,ppv.getProdNum() + warehouseProdNum);
          }

          countWarehousePreProdVOMap.put(wareProdTypeLineKey,ppv);

      });

      checkData.forEach( cd -> {
          //统计仓库维度每种物料的数量
          String wareProdTypeLineKey = cd.getWarehouseType() + ":" + cd.getProductNo() + ":"+cd.getOrderLineNo();

          Integer warehouseProdNum = countCheckWarehousePreProdMap.get(wareProdTypeLineKey);

          if (warehouseProdNum == null) {
              countCheckWarehousePreProdMap.put(wareProdTypeLineKey,cd.getInvNum());
          } else {
              countCheckWarehousePreProdMap.put(wareProdTypeLineKey,cd.getInvNum() + warehouseProdNum);
          }
      });


      limitData.forEach( cd -> {
          //统计仓库维度每种物料的送货上下限数量
          String wareProdTypeKey = cd.getWarehouseType() + ":" + cd.getProductNo();

          countLimitWarehousePreProdMap.put(wareProdTypeKey,cd);
      });



      //TODO 校验库存、商品的上下限、 原始订单剩余数量校验（未做）

      Set<Map.Entry<String, Integer>> entries = countWarehousePreProdMap.entrySet();

      for (Map.Entry<String, Integer> preProdentry :entries) {
          Integer preNum = preProdentry.getValue();//预开单物料数量


          Integer allPreNum = countPreProdMap.get(preProdentry.getKey().substring(0, preProdentry.getKey().lastIndexOf(":")));

          Integer checkNum = countCheckWarehousePreProdMap.get(preProdentry.getKey());
          WarehouseProdLimitVO warehouseProdLimitVO = countLimitWarehousePreProdMap.get(preProdentry.getKey().substring(0,preProdentry.getKey().lastIndexOf(":")));

          if (preNum <= 0) {
              throw new RuntimeException(MessageFormat.format("开单物料数量不能为0：{0}",preProdentry.getKey()));
          }

          if (checkNum == null) {
              throw new RuntimeException(MessageFormat.format("该仓别物料没有对应的送检数据：{0}",preProdentry.getKey()));
          }

          if ((allPreNum > preNum ? allPreNum : preNum) > checkNum) {
              throw new RuntimeException(MessageFormat.format("该仓别物料库存不足：{0},需要开单数：{1}，剩余数：{2}",preProdentry.getKey(),preNum,checkNum));
          }

          if (warehouseProdLimitVO != null) {
              if (warehouseProdLimitVO.getMinNum() != null && warehouseProdLimitVO.getMinNum() > preNum) {
                  throw new RuntimeException(MessageFormat.format("开单物料数量最小起送量：{0},当前数量：{1}",warehouseProdLimitVO.getMinNum(),preNum));
              }
              if (warehouseProdLimitVO.getMaxNum() != null && preNum > warehouseProdLimitVO.getMaxNum()) {
                  String[] keys = preProdentry.getKey().split(":");
                  NeedSingleOpenOrderProdVO openOrderProdVO = new NeedSingleOpenOrderProdVO();
                  openOrderProdVO.setOpenTimes(preNum / warehouseProdLimitVO.getMaxNum());
                  openOrderProdVO.setSurplusNum(preNum % warehouseProdLimitVO.getMaxNum());
                  openOrderProdVO.setWarehouseType(keys[0]);
                  openOrderProdVO.setProductNo(keys[1]);
                  openOrderProdVO.setOrderLineNo(keys[2]);
                  openOrderProdVO.setMaxNum(warehouseProdLimitVO.getMaxNum());
                  needSplitOrderPrudMap.put(preProdentry.getKey(),openOrderProdVO);
              }
          }
      }
      System.out.println("需要开单数据：---->"+ JsonUtil.toJSONString(needSplitOrderPrudMap));

      //TODO 开单操作  countWarehousePreProdVOMap

      if (!needSplitOrderPrudMap.isEmpty()) {
          for (Map.Entry<String, NeedSingleOpenOrderProdVO> preProdentry : needSplitOrderPrudMap.entrySet()) {
              NeedSingleOpenOrderProdVO singleOpenOrderProdVO = preProdentry.getValue();

              PreProductVO preProductVO = countWarehousePreProdVOMap.get(preProdentry.getKey());

              for (int i =1 ;i <= singleOpenOrderProdVO.getOpenTimes() ; i++) {
                  OrderVO openOrder = new OrderVO();
                  openOrder.setOpenOrderNo(getOpenOrderNo());

                  OrderVOItem orderVOItem = new OrderVOItem();
                  BeanUtils.copyProperties(preProductVO,orderVOItem);
                  orderVOItem.setProdNum(singleOpenOrderProdVO.getMaxNum());
                  openOrder.setOrderVOItems(Arrays.asList(orderVOItem));

                  openOrderData.add(openOrder);
              }

              //还有剩余数量
              if (singleOpenOrderProdVO.getSurplusNum() > 0) {
                  //覆盖原来的数量，将单独开单以后得数量扣除
                  preProductVO.setProdNum(singleOpenOrderProdVO.getSurplusNum());
              } else {
                  preData.remove(preProductVO);
              }
          }
      }

      //如果没有正好都被单独开单
      if (!CollectionUtils.isEmpty(preData)) {

          //剩下单独同一仓别的开为一个单

          Map<String,List<PreProductVO>> countWarehouseMap = new HashMap<>();

          preData.forEach(finalPreData -> {
              String warehouseType = finalPreData.getWarehouseType();

              List<PreProductVO> preProductVOS = countWarehouseMap.get(warehouseType);

              if (!CollectionUtils.isEmpty(preProductVOS)) {
                  preProductVOS.add(finalPreData);
              } else {
                  List<PreProductVO> productVOList = new ArrayList<>();
                  productVOList.add(finalPreData);
                  countWarehouseMap.put(warehouseType,productVOList);
              }
          });

          if (!countWarehouseMap.isEmpty()) {
              for (Map.Entry<String, List<PreProductVO>> preProdentry : countWarehouseMap.entrySet()) {
                  OrderVO openOrder = new OrderVO();
                  openOrder.setOpenOrderNo(getOpenOrderNo());

                  List<OrderVOItem> orderVOItems = new ArrayList<>();

                  for (PreProductVO ppv:preProdentry.getValue()) {
                      OrderVOItem orderVOItem = new OrderVOItem();
                      BeanUtils.copyProperties(ppv,orderVOItem);
                      orderVOItems.add(orderVOItem);
                  }
                  openOrder.setOrderVOItems(orderVOItems);

                  openOrderData.add(openOrder);
              }
          }
      }


      System.out.println("开单数据：---->"+ JsonUtil.toJSONString(openOrderData));
  }

    private static List<PreProductVO> getPreData() {
        PreProductVO vo = new PreProductVO();
        vo.setOrderNo("D1111");
        vo.setOrderLineNo("L1");
        vo.setProdNum(10);
        vo.setProductNo("P_A1111");
        vo.setWarehouseType("甲");

        PreProductVO vo2 = new PreProductVO();
        vo2.setOrderNo("D1111");
        vo2.setOrderLineNo("L2");
        vo2.setProdNum(11);
        vo2.setProductNo("P_B1111");
        vo2.setWarehouseType("甲");

        PreProductVO vo3 = new PreProductVO();
        vo3.setOrderNo("D1111");
        vo3.setOrderLineNo("L2");
        vo3.setProdNum(30);
        vo3.setProductNo("P_B1111");
        vo3.setWarehouseType("乙");

        PreProductVO vo4 = new PreProductVO();
        vo4.setOrderNo("D1111");
        vo4.setOrderLineNo("L66");
        vo4.setProdNum(11);
        vo4.setProductNo("P_B1111");
        vo4.setWarehouseType("甲");

        PreProductVO vo5 = new PreProductVO();
        vo5.setOrderNo("D1111");
        vo5.setOrderLineNo("L66");
        vo5.setProdNum(30);
        vo5.setProductNo("P_B1111");
        vo5.setWarehouseType("乙");

        List<PreProductVO> preData = new ArrayList<>();
        preData.add(vo);
        preData.add(vo2);
        preData.add(vo3);
        preData.add(vo4);
        preData.add(vo5);

        return preData;
    }


    private static List<CheckProductVO> getCheckData() {
        CheckProductVO vo = new CheckProductVO();

        vo.setOrderLineNo("L1");
        vo.setInvNum(10);
        vo.setProductNo("P_A1111");
        vo.setWarehouseType("甲");

        CheckProductVO vo2 = new CheckProductVO();
        vo2.setOrderLineNo("L2");
        vo2.setInvNum(50);
        vo2.setProductNo("P_B1111");
        vo2.setWarehouseType("甲");

        CheckProductVO vo3 = new CheckProductVO();
        vo3.setOrderLineNo("L2");
        vo3.setInvNum(60);
        vo3.setProductNo("P_B1111");
        vo3.setWarehouseType("乙");

        CheckProductVO vo4 = new CheckProductVO();
        vo4.setOrderLineNo("L66");
        vo4.setInvNum(50);
        vo4.setProductNo("P_B1111");
        vo4.setWarehouseType("甲");

        CheckProductVO vo5 = new CheckProductVO();
        vo5.setOrderLineNo("L66");
        vo5.setInvNum(60);
        vo5.setProductNo("P_B1111");
        vo5.setWarehouseType("乙");

        List<CheckProductVO> preData = new ArrayList<>();
        preData.add(vo);
        preData.add(vo2);
        preData.add(vo3);
        preData.add(vo4);
        preData.add(vo5);

        return preData;
    }

    private static List<WarehouseProdLimitVO> getLimitData() {
        WarehouseProdLimitVO vo = new WarehouseProdLimitVO();

        vo.setProductNo("P_A1111");
        vo.setWarehouseType("甲");
        vo.setMinNum(0);
        vo.setMaxNum(100);

        WarehouseProdLimitVO vo2 = new WarehouseProdLimitVO();

        vo2.setProductNo("P_B1111");
        vo2.setWarehouseType("甲");
        vo2.setMinNum(0);
        vo2.setMaxNum(10);

        WarehouseProdLimitVO vo3 = new WarehouseProdLimitVO();

        vo3.setProductNo("P_B1111");
        vo3.setWarehouseType("乙");
        vo3.setMinNum(0);
        vo3.setMaxNum(100);

        List<WarehouseProdLimitVO> preData = new ArrayList<>();
        preData.add(vo);
        preData.add(vo2);
        preData.add(vo3);

        return preData;
    }



    @Data
    static class OrderVO {

      //开单号
      private String openOrderNo;

      private List<OrderVOItem> orderVOItems;

    }

    @Data
    static class OrderVOItem {

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

    /**
     * 预开单数据
     */
    @Data
    static class PreProductVO {

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

    /**
     * 检查数据
     */
    @Data
    static class CheckProductVO {

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

    @Data
    static class WarehouseProdLimitVO {
        //仓别
        private String warehouseType;

        //物料编码
        private String productNo;

        //物料最小数量
        private Integer minNum;

        //物料最大数量
        private Integer maxNum;
    }

    @Data
    static class NeedSingleOpenOrderProdVO {
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

    public static String getOpenOrderNo(){
        return "OON_"+System.currentTimeMillis()+new Random().nextInt(10000);
    }

}
