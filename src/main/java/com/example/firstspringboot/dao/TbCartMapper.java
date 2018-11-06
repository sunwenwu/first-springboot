package com.example.firstspringboot.dao;

import com.example.firstspringboot.common.vo.TbCart;

public interface TbCartMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbCart record);

    int insertSelective(TbCart record);

    TbCart selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbCart record);

    int updateByPrimaryKey(TbCart record);
}