package com.example.firstspringboot.service;

import com.example.firstspringboot.service.vo.CheckProductVO;
import com.example.firstspringboot.service.vo.PreProductVO;
import com.example.firstspringboot.service.vo.WarehouseProdLimitVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: sunwenwu
 * @Date: 2019/4/24 10：38
 * @Description:
 */
public class DataUtils {

    public static List<PreProductVO> getPreData() {
        PreProductVO vo = new PreProductVO();
        vo.setOrderNo("D1111");
        vo.setOrderLineNo("L1");
        vo.setProdNum(10);
        vo.setProductNo("P_A1111");
        vo.setWarehouseType("甲");

        PreProductVO vo2 = new PreProductVO();
        vo2.setOrderNo("D1111");
        vo2.setOrderLineNo("L2");
        vo2.setProdNum(25);
        vo2.setProductNo("P_B1111");
        vo2.setWarehouseType("甲");

        PreProductVO vo3 = new PreProductVO();
        vo3.setOrderNo("D1111");
        vo3.setOrderLineNo("L2");
        vo3.setProdNum(30);
        vo3.setProductNo("P_B1111");
        vo3.setWarehouseType("乙");



        List<PreProductVO> preData = new ArrayList<>();
        preData.add(vo);
        preData.add(vo2);
        preData.add(vo3);


        PreProductVO vo4 = new PreProductVO();
        vo4.setOrderNo("D1111");
        vo4.setOrderLineNo("L66");
        vo4.setProdNum(25);
        vo4.setProductNo("P_B1111");
        vo4.setWarehouseType("甲");

        PreProductVO vo5 = new PreProductVO();
        vo5.setOrderNo("D1111");
        vo5.setOrderLineNo("L66");
        vo5.setProdNum(30);
        vo5.setProductNo("P_B1111");
        vo5.setWarehouseType("乙");
        preData.add(vo4);
        preData.add(vo5);

        return preData;
    }


    public static List<CheckProductVO> getCheckData() {

        List<CheckProductVO> preData = new ArrayList<>();

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

        preData.add(vo);
        preData.add(vo2);
        preData.add(vo3);

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
        preData.add(vo4);
        preData.add(vo5);

        return preData;
    }

    public static List<WarehouseProdLimitVO> getLimitData() {
        WarehouseProdLimitVO vo = new WarehouseProdLimitVO();

        vo.setProductNo("P_A1111");
        vo.setWarehouseType("甲");
        vo.setMinNum(0);
        vo.setMaxNum(20);

        WarehouseProdLimitVO vo2 = new WarehouseProdLimitVO();

        vo2.setProductNo("P_B1111");
        vo2.setWarehouseType("甲");
        vo2.setMinNum(0);
        vo2.setMaxNum(20);

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


    public static String getOpenOrderNo(){
        return "OON_"+System.currentTimeMillis()+new Random().nextInt(10000);
    }
}
