package com.example.firstspringboot.now.account.util;

import lombok.Data;

/**
 * @author ipaynow0802.
 * @date 2019/3/14 20:27.
 * @Description modify   by  zhaoQiPeng   2019/04/22
 */
@Data
public class ApiRespDto {
    /**
     * API系统的响应状态
     */
    private String returnCode;
    /**
     * API 系统的返回信息
     */
    private String returnMsg;
    /**
     * API 系统错误码
     */
    private String errorCode;
    /**
     * API 系统错误信息
     */
    private String errorMsg;
    /**
     * 业务响应数据(密文，AES加密)
     */
    private String encryptData;
    /**
     * 业务响应数据签名
     */
    private String signature;

    public String getReturnCode() {
        return returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getEncryptData() {
        return encryptData;
    }

    public String getSignature() {
        return signature;
    }
}
