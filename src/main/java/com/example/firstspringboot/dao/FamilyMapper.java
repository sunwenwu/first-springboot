package com.example.firstspringboot.dao;


import com.example.firstspringboot.common.vo.Family;

public interface FamilyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Family record);

    int insertSelective(Family record);

    Family selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Family record);

    int updateByPrimaryKey(Family record);

    Family selectByFamilyIdForLock(String familyId);

}