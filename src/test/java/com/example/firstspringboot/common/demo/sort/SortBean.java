package com.example.firstspringboot.common.demo.sort;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.math.BigDecimal;
import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * @Author :sunwenwu
 * @Date : 2020/8/5 9:58
 * @Description :
 */
public class SortBean implements Comparable<SortBean> {


    private final Collator chinaSortUtil =  Collator.getInstance(Locale.CHINA);

    private String name;

    private BigDecimal balance;

    private Integer useTimes;

    //Y  置顶  N 非置顶
    private String topLogo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getUseTimes() {
        return useTimes;
    }

    public void setUseTimes(Integer useTimes) {
        this.useTimes = useTimes;
    }

    public String getTopLogo() {
        return topLogo;
    }

    public void setTopLogo(String topLogo) {
        this.topLogo = topLogo;
    }

    @Override
    public int compareTo(SortBean o) {

        int top =  topLogoSort(o);

        if (top != 0) {
            return top;
        }

        int balance = this.balance.compareTo(o.getBalance());

        if (balance != 0) {
            return balance;
        }

        int useTimes = this.getUseTimes().compareTo(o.getUseTimes());

        if (useTimes != 0) {
            return useTimes;
        }

        int chinaSort = chinaSortUtil.getCollationKey(this.getName()).compareTo(chinaSortUtil.getCollationKey(o.getName()));

        if (chinaSort != 0) {
            return chinaSort * -1;
        }

        return 0;
    }



    private int topLogoSort(SortBean o) {
        if(this.getTopLogo().equals(o.getTopLogo())){
            return 0;
        } else if ("Y".equals(this.getTopLogo())){
            return 1;
        } else {
            return -1;
        }
    }


    public static void main(String[] args) {


        SortBean s1 = new SortBean();
        s1.setBalance(new BigDecimal("100"));
        s1.setName("上海");
        s1.setUseTimes(10);
        s1.setTopLogo("Y");

        SortBean s2 = new SortBean();
        s2.setBalance(new BigDecimal("200"));
        s2.setName("成都");
        s2.setUseTimes(21);
        s2.setTopLogo("N");


        List<SortBean> sortBeans = Arrays.asList(s1, s2);


        System.out.println("排序前：======"+JSON.toJSONString(sortBeans));

        Collections.sort(sortBeans,Collections.reverseOrder());

        System.out.println("排序后：======"+JSON.toJSONString(sortBeans));


    }

}
