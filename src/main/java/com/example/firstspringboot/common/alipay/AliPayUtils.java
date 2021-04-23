package com.example.firstspringboot.common.alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;


public class AliPayUtils {

    public static void main(String[] args) {

        String APP_ID = "1111";
        String SIGN_TYPE = "RSA";
        String SIGN_TYPE2 = "RSA2";
        String SIGN_TYPE3 = "SHA256WithRSA";

        String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCAFfE/UjZjZw2nJlnKERewzhVgIq/ZjfeZd7wEjcpdoE/LzvK41qbRirGwz66OjoVRegVOFTRdP70B6cWbaikOEbZJJX/xaQ9U/Hpl4YvCfQIC51QQ9XPdfiVtElcewRla+/qMHx9tfouUdarLKDLdohrDT60Hj1RNhMRcd/JIpAC1oTgxL0bFuaUpv4eGf7UsLFToDWMSdL3xkHI097n/osOkIRDGuXOPiT41bcBPlt5I8pKZaVSyCwwTwlJkslydXuia5X4NTkN1UvnxBcmgR8VHoZmHF4ouhi1PFLDTGyj+rcosVx0DB+OLHsdybIOGmXDBBKfnJ2tvmPUKLatpAgMBAAECggEAbMm2OJDsLutFVlWlZ5Gy7HFtCFUNxJrm1Is0vy1q1rCldbZUw5dU4p6JI2KRoPT92ODphpNOAXF0aB6Uvtag/hkfGOtnyrPv2MAEeWni2ZJxVeQCq9C90z8kLIqqQi3CClwARmjcuZQEm1iBcsG2yjW3w3F1xfj/okOEwAEEYJrm7jqDT3jLXqFv6bpo4vrX4HvYZEFYP8fAX+giwWm2R+ehqHZKu62rpuRDnDgFpgb1baI8S5RpkOa28VoMl40wi7dYYP0yRu7TER/6nTOZc07nsjJ2UNS/M3FwdVn6Ods3d133vt1ULsu8X5leM7csBOJyUByc7Y2oVfX7E2/bAQKBgQDlL0Ydz27D2QJ6o8gjirb6cbAo+M37aI2s9r2hdoShy2fvhTf6j97shV4cXVzdnaO9BmNRMzjqWj+rL7X4NDBtc1nk7Yu/1+oZ3kg0oGy8T+nOkNI8+f/MBgTgQvnH0bA1EcFBKk/gxJ8w4oaQfCEKBdoWqjRtosuE0MGwGNDKyQKBgQCPEnhdW9LBvezSRTNFzpgzR1kEQfMzNdbulH215E/W1rUX3XmC9wCcdbNXcu7uUkkZUbhEGYX8VeOd0cfYI0gSi6UyN/N+lX01jXGLJ1ojSfRKQaIeJi2jlW6UFgX8dxHvh9jxRmcfMfy292WX38HqplyFVPmpJ8EkN9HJeSuLoQKBgEXKSIrJYQyIp+WavFZ+ZkwLlL5VTbxyUTpLhOGHSspOO59HrQ1DBHp3/nmawK3H1FW6fUH73CgZRWwmwj4L/ndF+ijpYXYRFPu9obVM6O/LUgKSPUNOSePiLVfxbUi5BPSULIpgjSSAG43yXVHgSttXftsKB58fROR0AZdTZzlpAoGAMsso5mQqH9Rw1nnw6RCeol3rMYLEd+LeapdXRhNHcQuLAW0ErcVJd0KmKB7Kytt1zkJf+/VfxyUrCSWoIIjuFn9zZ6/Lh55E9JVBwVh4PN0NmKtShgeh3li4CGUFPQt816BQGeCXzdBuWvrcG5459zeW01NGy5IslNIobbHSDuECgYAdLpspRfpKqGetsmOvA/5u/5rb16VBAqFEz5wxH5ws1XvQBUiwukzNuOka8M2PG2ZrXbSnpMVBqTOEFtp9LHdxhMZD95CUMajgip8W5axtC7OMqf9gwl6psE1Osle0UUxd1Ad4g5G81UB4/vri/j0SSsj9jisfZqYGzzdVfCtGBw==";
        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgBXxP1I2Y2cNpyZZyhEXsM4VYCKv2Y33mXe8BI3KXaBPy87yuNam0YqxsM+ujo6FUXoFThU0XT+9AenFm2opDhG2SSV/8WkPVPx6ZeGLwn0CAudUEPVz3X4lbRJXHsEZWvv6jB8fbX6LlHWqyygy3aIaw0+tB49UTYTEXHfySKQAtaE4MS9GxbmlKb+Hhn+1LCxU6A1jEnS98ZByNPe5/6LDpCEQxrlzj4k+NW3AT5beSPKSmWlUsgsME8JSZLJcnV7omuV+DU5DdVL58QXJoEfFR6GZhxeKLoYtTxSw0xso/q3KLFcdAwfjix7H    cmyDhplwwQSn5ydrb5j1Ci2raQIDAQAB";
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay11.com/gateway1.do", APP_ID, APP_PRIVATE_KEY, "json", AlipayConstants.CHARSET_UTF8, ALIPAY_PUBLIC_KEY, SIGN_TYPE2);
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo("12345");
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("https://mvnrepository.com/artifact/com.alipay.sdk/alipay-sdk-java/4.4.2.ALL");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。

            System.out.println("结果3："+JSONObject.toJSONString(response));

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}
