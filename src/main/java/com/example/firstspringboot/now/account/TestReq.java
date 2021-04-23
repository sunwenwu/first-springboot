package com.example.firstspringboot.now.account;

import com.alibaba.fastjson.JSONObject;
import com.example.firstspringboot.now.account.util.*;

import java.util.Base64;

/**
 * @Author :sunwenwu
 * @Date : 2021/2/24 14:48
 * @Description :
 */
public class TestReq {

    //   现在支付公钥
      public static String  ipayNowPubRsaKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmCaj5kM8p+sgVNiU9Y8yrIuyH9rFKa4Wu/s8f16/2/T0hMaSgGklTNrjyQtUu0Rk8pZF5LhFukEqUg5S7hlBuBTZJP6YdZQQebufZkLse8JXzJ9HsQ2J3E/fUMMRTatqntUCJHW+dFnDAor52RBx3bHfpx+dS58UR3zi6Zn8daU6EzW7MkQLclup0V9KvY/hNaww41w+O4z/B2+gYb7N3wY0xWXOS74+v91QQTanVILX3t7R+SlYyPi77S0nn0IWI+VHZGsa6X42dbJ+5f7/wKLfWUMdr5/OfXGawW8K2e8dcsSyokIYYQvIPM50xRmDzMsdFp5TrxEMLce0pBWgIwIDAQAB";
    // 众邦商户私钥
    public static String  mchPriRsaKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCTlOtrT2OqaqUIzT9QmRHdmxQ5nA614baEIeyCg4anOFkVv1GwZ+/mXtsoei9x/l+95SoKiSg3mWPqDjER6gI/I74NczFTFteMW/Ee+Y1aXU5gfsZ2ITPdy7V+e3LtswlC2DwoCCPCIGDDo4znm+gOEGI2DKWfVH/wRhrDPDiMZD8Ro+gCEuusQ6UPZ0r6iKshs4soJ5GVeWgIJSH70YAgsmTZEO0SjC7w7b+PErdvvdTy5X5AfGo0oIzCHKWG79ugbLOwzm8h0HX4RouzC4JW/Z0u5Ps+x4DOydEkTt5WvPlboHAn18okwL25vYnJJdRY6d0H8kXdU2KNImiW3C+xAgMBAAECggEAGRA/cvgGmxiFcps4131HdXUy2B3SubzdfE8ulXO02I1PQ8w8GVNtgJg3bAZkLF3g8KUMoMFuXRLlzwgd+Ubens55Jlub0tqcPLAYoQQMp34VI3okRkeZQV+pMRPSDuBLAFkszjejqwJIrLEH6wWlQU4y/67iKc71815UiTee+uiA/GgjHiVvs1MSc3JRRJzcH2AAo9zVDD7hUn4nUGVS/1gHbRO/nszioYN4DsEpJPsem3cQELlctx97stRQHtvNktkdYntqTbwq6wzxHzh3GeVhluZ8gXMNy7cX5/apR6vLO2ctzOe9qtqZxEoQpjZGctmv1DGznMtMRt23XYU1KQKBgQDafgm+GusBRoMtQei6mGzURg+0iTI0wBC5Nh4DZAsMU8tZYlqbF6KLJnHQzgyikA5aPY5PsexR+yJSGWCyIWccWonn4fLXRXt972mp3DujPxZylSruf6AGKI+89cbI9hP6W5ITIbvX8tbTzjw2zwRBPlTzluouyPhXCcMNZFR7lwKBgQCs6pyc2txWk4F/JqyYaYMYrl8QuWvvL8ITBtmVCOzJKhQb3GUoMqe7eQM72Q3/BQhpB7zDvuC/qPNsPerxWHiuvk2D0xJROLFTUfaAT8KdlBjEp279D8JuNg3VdP5HjtI9KshGY5hp7IlvB1cSqQI7ccxITMsBFEnoXTDvYwC39wKBgBgls9fxy6UxcM/wp9Lvg5+WOqgePZE85NxDUd/YD6yGGkQ/rXnUi99LGWsVSqowt5Vblj3SkSaiVbGzIk+th2LH0tPw0dDe2f4aFphyQZkSIN37BGqlaj8j/nbIxvZw09ZohmVc5hDAwuNXuKnqCTNYuzTMqgcPqPqdlpQFUZzzAoGARev0+oSR1rx8//Kell8MMP3J2VRxbd4NB26cq2RZg5rtWLrNeOzK4Mc1ZI0H2NirJvWHAkx8S2RBUsLS71cMLfUsGSNP4+Id6ohZOleGZfwN6++ctN4bjD91ZpyRelQpdayIIdaCOkr45Bm+vv/ytCD3L0FGUaBnpnuQnyyUjM8CgYAFrqyOmiIWlzH9VhPYd6u45spbO5kbgvhq3Sz9A4fiOPWuJWBogWUrx43J2VfRBBVGw0s1Z18xM/l5L7hLMonnsiAsoDCXiIPai/DOCr9/P6LgdVk6v7lLatacGPT9RnyJFTLkLf9mbhU8HS+/g99et4qZzubPmIsCHSO085gITw==";



    public static void main(String[] args) {

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("reqId","202008111203012");
        jsonObject.put("bankId","103100000026");
        jsonObject.put("bankKeyWords","中关村");

        // 调用公共接口
        ConUse(jsonObject,"ACCP004");

    }

    public static   void   ConUse(JSONObject jsonObject,String funcode){

        //  jsonObject.put("appId","158469309221453"); //  众邦测试参数
        //   158469309221453
        jsonObject.put("appId","158469309221453");  //  亿联测试参数 155902897184670
        jsonObject.put("funCode",funcode);
        jsonObject.put("version","1.0.0");
        // 去除不参与加密部分
        String appId = jsonObject.getString("appId");
        String version = jsonObject.getString("version");
        String funCode = jsonObject.getString("funCode");
        System.out.println("json请求参数 "+jsonObject.toJSONString());
        jsonObject.remove("funCode");
        jsonObject.remove("version");
        jsonObject.remove("appId");

        // 获取AES 秘钥
        byte[] aesKey = AES.genarateRandomKey();

        String encryptData = new String();
        String encryptKey =  new String();
        String signature =   new String();
        String stringjson= jsonObject.toJSONString();
        System.out.println("请求参数字符串  "+stringjson);
        try{
            encryptData = Base64.getEncoder().encodeToString(AES.encrypt(stringjson.getBytes(AES.CHAR_ENCODING), aesKey));

            System.out.println("encryptData  "+encryptData);
            //  encryptKey = EncryptStrategyUtil.encrypt(password, ipayNowPubRsaKey, "rsa");

            encryptKey = Base64.getEncoder().encodeToString(RsaUtil.encrypt(RsaUtil.loadPublicKeyByStr(ipayNowPubRsaKey), aesKey));

            signature = RsaUtil.sign(mchPriRsaKey, jsonObject.toJSONString());
        }catch (Exception  e){
            System.out.println("发生异常：" + e.getMessage());
        }
        // 请求报文格式
        ApiReqDto apiReqDto = new ApiReqDto();
        apiReqDto.setAppId(appId);
        apiReqDto.setFunCode(funCode);
        apiReqDto.setVersion(version);
        apiReqDto.setEncryptData(encryptData);
        apiReqDto.setEncryptKey(encryptKey);
        apiReqDto.setSignature(signature);

        String  jsonStr = JSONObject.toJSONString(apiReqDto);
        JSONObject  reqJsonEncrypt  = JSONObject.parseObject(jsonStr);
        String  encryptResult = new String();
        // 发送请求
        try {
//            encryptResult = HttpUtil.httpPostWithJson(reqJsonEncrypt, "http://192.168.105.30:18023");
            encryptResult = HttpUtil.httpPostWithJson(reqJsonEncrypt, "https://acc-test.ipaynow.cn");
            //  返回参数
            ApiRespDto apiRespDto  =  JSONObject.parseObject(encryptResult,ApiRespDto.class);
            String encryptDataBack = apiRespDto.getEncryptData();
            //   String encykey=apiRespDto;

            // 返回的原未加密数据
            String originData="";  //  RsaUtil.decrypt(mchPriRsaKey,);
            originData = new String(AES.decrypt(Base64.getDecoder().decode(encryptDataBack),aesKey));
            System.out.println("原数据"+originData);
            String signatureBack = apiRespDto.getSignature();
            boolean flag = RsaUtil.verifySign(ipayNowPubRsaKey, signatureBack, originData);
            //   RsaUtil.decrypt(mchPriRsaKey,encryptKey);
            if (flag){
                System.out.println("验签成功");
            }else{
                System.out.println("验签失败");
            }
        }catch (Exception  e){
            System.out.println("发生异常：" + e.getMessage());
        }
    }
}
