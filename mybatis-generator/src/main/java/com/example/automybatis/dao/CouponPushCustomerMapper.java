package com.example.automybatis.dao;

import com.example.automybatis.model.CouponPushCustomer;

public interface CouponPushCustomerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CouponPushCustomer record);

    int insertSelective(CouponPushCustomer record);

    CouponPushCustomer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CouponPushCustomer record);

    int updateByPrimaryKey(CouponPushCustomer record);
}