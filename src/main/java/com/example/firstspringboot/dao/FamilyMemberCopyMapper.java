package com.example.firstspringboot.dao;


import com.example.firstspringboot.common.vo.FamilyMemberCopy;

public interface FamilyMemberCopyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FamilyMemberCopy record);

    int insertSelective(FamilyMemberCopy record);

    FamilyMemberCopy selectByPrimaryKey(Long id);

    FamilyMemberCopy selectByPrimaryKeySelective(FamilyMemberCopy record);

    int updateByPrimaryKeySelective(FamilyMemberCopy record);

    int updateByPrimaryKey(FamilyMemberCopy record);
}