package com.example.firstspringboot.common.vo;

import java.util.Date;

public class FamilyMemberCopy {
    private Long id;

    private String familyId;

    private String userId;

    private String joinStatus;

    private String owner;

    private Long useTimes;

    private String isTop;

    private Long useAmount;

    private Long rechargeAmout;

    private Date lastUseTime;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(String joinStatus) {
        this.joinStatus = joinStatus;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getUseTimes() {
        return useTimes;
    }

    public void setUseTimes(Long useTimes) {
        this.useTimes = useTimes;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public Long getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(Long useAmount) {
        this.useAmount = useAmount;
    }

    public Long getRechargeAmout() {
        return rechargeAmout;
    }

    public void setRechargeAmout(Long rechargeAmout) {
        this.rechargeAmout = rechargeAmout;
    }

    public Date getLastUseTime() {
        return lastUseTime;
    }

    public void setLastUseTime(Date lastUseTime) {
        this.lastUseTime = lastUseTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}