package com.example.firstspringboot.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
* @Author: 0152
* @Description:家庭卡操作请求类
* @Date: 2020/6/2 18:35
*/
@Data
public class FamilyMemberReqDto implements Serializable {


    /**
     * 当前会员ID
     */
    private String userId;

    /**
     * 当前会员姓名
     */
    private String userName;


    /**
     * 家庭卡ID
     */
    private String familyId;


    /**
     * 副会员ID - 被邀请者
     */
    private String memberUserId;

    /**
     * 副会员ID - 被邀请者
     */
    private String memberUserName;


    /**
     * 是否置顶  Y是 N否
     */
    private String isTop;


    /**
     * 操作类型 create家庭卡创建、join邀请加入、remove移除、signOut退出
     */
    private String familyName;

}
