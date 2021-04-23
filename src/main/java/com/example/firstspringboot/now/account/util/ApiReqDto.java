package com.example.firstspringboot.now.account.util;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ipaynow0802.
 * @date 2019/3/14 11:54.
 * @Description modify  by  zhaoQiPeng
 */
@Data
public class ApiReqDto {



    /**
     * 功能码
     */
    @NotBlank(message = "功能码不可为空")
    private String funCode;

    /**
     * 应用编号
     */
    @NotBlank(message = "应用编号不可为空")

    private String appId;

    /**
     * 接口版本号
     */
    @NotBlank(message = "接口版本号不可为空")
    private String version;

    /**
     * 加密的AES秘钥
     */
    @NotBlank(message = "AES秘钥（密文）不可为空")
    private String encryptKey;

    /**
     * 加密的报文体
     */
    @NotBlank(message = "报文体（密文）不可为空")
    private String encryptData;

    /**
     * 签名
     */
    @NotBlank(message = "请求的签名不可为空")
    private String signature;


    public void setFunCode(String funCode) {
        this.funCode = funCode;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }

    public void setEncryptData(String encryptData) {
        this.encryptData = encryptData;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
