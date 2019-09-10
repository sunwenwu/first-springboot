package com.example.firstspringboot.common.alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.StringUtils;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class AliPayUtils {

    public static void main(String[] args) throws Exception{

//      run5();
        runQuery();
    }

    /**
     * 结果：alipay_sdk=alipay-sdk-java-4.4.2.ALL&app_id=2015120700927798&biz_content=%7B%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%22123456%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22App%E6%94%AF%E4%BB%98%E6%B5%8B%E8%AF%95Java%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=https%3A%2F%2Fwww.so.com&sign=Aey%2FhCd1y3kcfjSIXxfJFiKEUM9wO4kWOJqmYp0Zno95eDIJWTTvGfbnkpIdWj3ZHbGQkKIzOS6Wrv1etUwkLjHiHOIO3Op7BoumXnkmV72TKhTyhKf2Z8c2dPSa0f9D2Qp8eDWdb0kLgqGgrEYnXvi5dQVYcEqbVfBcczwcVPhxGUtCNvJM5alBmC1itNU%2BOH2Z4UiTi7vVVdCw%2F7YAzfSc%2BbLyOcNPZj4FlZgnnRtdaGG3Xur7ucBm2jKsSGGUW76O5NvpbQ%2FUW1dtst3l1XwAMYcbgdsnINsQCu47FAarPa%2FEqxWJ48Zo7lhsDUE%2BryLIeHFeVjVnYFd55jbGNQ%3D%3D&sign_type=RSA2&timestamp=2019-08-22+18%3A09%3A45&version=1.0
     * 结果3：alipay_sdk=alipay-sdk-java-4.4.2.ALL&app_id=1111&biz_content=%7B%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%2212345%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22App%E6%94%AF%E4%BB%98%E6%B5%8B%E8%AF%95Java%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=https%3A%2F%2Fmvnrepository.com%2Fartifact%2Fcom.alipay.sdk%2Falipay-sdk-java%2F4.4.2.ALL&sign=CnKF7ci0d75GUIVIbOFgeesANZJk0%2FgK7Twfr0B8gzFTnEp5RJ0%2Bnw%2Bq1FT6rkpLhCiYls6Ciw3qGbx%2ByvJG1Fc%2F1mpOmhmvLgRc07VO%2F27JyrQWBfgjb1S5DEafElC3X6kdCi5Fke5vPgvFzzh0jny3h7V3ZurP%2FkH5pl5dV6RRVoDWeRBLFmBZaxTsz%2F1K9hPwiItjMqfHYzoom9%2F9l5gjisZRvnM9Hp6FbGZTzQlo49dj3BfJDD%2FdP8lzxV9EraJOZ7aW5W%2Br17gqTYd%2BusxqXhh1QQgiW2gjKSRgFv2IfjUzLML7BCW2UOz%2F59%2Bt7o2SpSMj6o5cfHWGGb0PjQ%3D%3D&sign_type=RSA2&timestamp=2019-08-22+18%3A13%3A15&version=1.0
     */
    public static void run () {
        String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCUbscpo605LV8J7EQ4b68aebURF0wJB8c5qwyEGYGFZi8NbaXXInbS4CU+V5d0OqMGYUOJkZWatuxpuYg2Y7bo6PLbWJfoBnYs5egt2Xisilkt9kAPWd2kIHeEMbYk30hUcBxI3syvdKiB24kRw45p3CRsy10AUMJdxjiKxErSrJw3uURcjN7Utwt7EsGb/shk5TI0X3XNI8yLvPaO/tIN3IStUDXHc/4VzkNN3Pt0Di4q62mIMDmQu4U5dO/9qJR3rtipOh7iR459SGMm6fvbYnirog831A5yTqRPe2EXaRiI+oKQ+48ER12hQk7FlGXBHGWbMKsXDDxU6jN2jnoFAgMBAAECggEAa1Ywb8wM1+wXbfWM38LjvSGF7nYKZf1Z9DndMym+n9jk7bD83DXv905foFV3EHXWriZn0HOW2+qry0h/V9WDz6mk9Tl7oC4MByu6Wm2I9jyVgMcyS+eQf796rf3E2aUdiPIJgiibm8WglUdMN6xSr/qh3IqzFidQZr+Q48hO00kk1AkFORHjIwWDCW1ftzTVCAyGMigt7mukSt0SBjJ23XBbcP9A3M1HUvImO8/jwYXp+8FSekDhHsMru6DSXYlmHJXRDETwG61BkBKauCi6q9V+QON4zWAncHgNJ6ULJ8O63CxylhmbVgnlwMLeVUHCVgprwAiWjLtAslw6nX5YAQKBgQDN7qxSYOpPqttFOj+uZfjRK4uO7yaM6wMnl5sHLfw9kyGpwdaUdjnAxokGwFpe7lUu3CfpAsl4RPUzB0IjwO4GwwqDq0cfd8tV1X1WH1L0tjV1F6wfMX8jXobTfQyD1SKX7AcN55K1BJT6pzAbqcd0X3d/Lu05zYqSzdRM7JCK1QKBgQC4hU10/yPgSwAKn6JHvAx32IrTKJ+V9o8r1S8WxcLP2yUG/adXLuzzU3RKTkxWP6nliweHzhG8HLwQ3Omaoujb+ibwCF/qq35PfKSeV/MO/Eth1ifBFvbFponF7wdlDf4PvRAR8nY9er39oCOpuwAnW1o3b5xXaplFXtOgZLFqcQKBgQCq4Kpy7OJg2wh5qDzhTNIrVvPF1iHp+rSybLWCPqE4wtbDEwooRPXlRZi8Vm5mqWNbr+kaC2r79lR0VEiIfHXGfJ9D5KLutKuv/Ke1B+cBEhTHpa34cEzsH2rHINN6rSe4Ymz0F7z/od6xInvj6HyNzw3MJjdIqIqdCFhqwD2sRQKBgQCZs7iMr4bz66WSM90awgLkxcUGFb2xTlt+WKtL3dnkCJi0VRBSqC0Kl0a0Krqd0ZQ1N/VC452UQ59IsKwKeo9bxekvYtp7APVLQvlhSMOXhQNJ8PjWd8mBZLM45F4ViG1MMOG1AXIKzjZ6kS2D0dhT8F2a9eaWTp5z0YSZA6dhkQKBgAsI02IC3BygZbTiFdbgWvqiham8qJ5jH5QNI1RHCVYsHQBYrgmafhmo3iQkBqItWp3df/vnZFXcc5fULXNBj8UAZ6KNNHzNPn2tbWtlTaS2GCuCN7vPxVjGnm7pT8S52c3nZlBj79GJQrhM+i5+vlPOmxQH/dcUdEUvGmza28ht";
        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsYfjWGvbFQ89IplTye/+rVa8oZBvH6x+hQQYWF2ncoduvZSL0FezLN+hk9h9TnHAq5sOHomnr8Hz77peeqDUJPjJJLz+Nad+bRc5QcAPEREBMso8FvZOUYmb1ZoPE3heKTgzFW16cx0XPVTZc/fkOVc9zU4rSeDjbdpPc5bVIZ7Uk5uyjATi3UdmlhUEjZQb0LDGKauYbcJ8tPYgnWh+/1QWUV/0AiWvZSAo9chPLda2DctaEcFou/W2kA/HK60TNL1HzgMZSfShoXfomxJIaSOTwT0BeL7Gkw4zjD/AqfyiYCiwzOpNTLT6oX+FqmXwvJxar15kqggLL4Ke8tNI/QIDAQAB";

        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2015120700927798", APP_PRIVATE_KEY, "json", "UTF-8", ALIPAY_PUBLIC_KEY, "RSA2");
//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject("App支付测试Java");
        model.setOutTradeNo("123456");
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("https://www.so.com");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println("结果："+response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }


    public static void run2(){
        String APP_ID = "1111";
        String SIGN_TYPE = "RSA";
        String SIGN_TYPE2 = "RSA2";
        String SIGN_TYPE3 = "SHA256WithRSA";

        String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCAFfE/UjZjZw2nJlnKERewzhVgIq/ZjfeZd7wEjcpdoE/LzvK41qbRirGwz66OjoVRegVOFTRdP70B6cWbaikOEbZJJX/xaQ9U/Hpl4YvCfQIC51QQ9XPdfiVtElcewRla+/qMHx9tfouUdarLKDLdohrDT60Hj1RNhMRcd/JIpAC1oTgxL0bFuaUpv4eGf7UsLFToDWMSdL3xkHI097n/osOkIRDGuXOPiT41bcBPlt5I8pKZaVSyCwwTwlJkslydXuia5X4NTkN1UvnxBcmgR8VHoZmHF4ouhi1PFLDTGyj+rcosVx0DB+OLHsdybIOGmXDBBKfnJ2tvmPUKLatpAgMBAAECggEAbMm2OJDsLutFVlWlZ5Gy7HFtCFUNxJrm1Is0vy1q1rCldbZUw5dU4p6JI2KRoPT92ODphpNOAXF0aB6Uvtag/hkfGOtnyrPv2MAEeWni2ZJxVeQCq9C90z8kLIqqQi3CClwARmjcuZQEm1iBcsG2yjW3w3F1xfj/okOEwAEEYJrm7jqDT3jLXqFv6bpo4vrX4HvYZEFYP8fAX+giwWm2R+ehqHZKu62rpuRDnDgFpgb1baI8S5RpkOa28VoMl40wi7dYYP0yRu7TER/6nTOZc07nsjJ2UNS/M3FwdVn6Ods3d133vt1ULsu8X5leM7csBOJyUByc7Y2oVfX7E2/bAQKBgQDlL0Ydz27D2QJ6o8gjirb6cbAo+M37aI2s9r2hdoShy2fvhTf6j97shV4cXVzdnaO9BmNRMzjqWj+rL7X4NDBtc1nk7Yu/1+oZ3kg0oGy8T+nOkNI8+f/MBgTgQvnH0bA1EcFBKk/gxJ8w4oaQfCEKBdoWqjRtosuE0MGwGNDKyQKBgQCPEnhdW9LBvezSRTNFzpgzR1kEQfMzNdbulH215E/W1rUX3XmC9wCcdbNXcu7uUkkZUbhEGYX8VeOd0cfYI0gSi6UyN/N+lX01jXGLJ1ojSfRKQaIeJi2jlW6UFgX8dxHvh9jxRmcfMfy292WX38HqplyFVPmpJ8EkN9HJeSuLoQKBgEXKSIrJYQyIp+WavFZ+ZkwLlL5VTbxyUTpLhOGHSspOO59HrQ1DBHp3/nmawK3H1FW6fUH73CgZRWwmwj4L/ndF+ijpYXYRFPu9obVM6O/LUgKSPUNOSePiLVfxbUi5BPSULIpgjSSAG43yXVHgSttXftsKB58fROR0AZdTZzlpAoGAMsso5mQqH9Rw1nnw6RCeol3rMYLEd+LeapdXRhNHcQuLAW0ErcVJd0KmKB7Kytt1zkJf+/VfxyUrCSWoIIjuFn9zZ6/Lh55E9JVBwVh4PN0NmKtShgeh3li4CGUFPQt816BQGeCXzdBuWvrcG5459zeW01NGy5IslNIobbHSDuECgYAdLpspRfpKqGetsmOvA/5u/5rb16VBAqFEz5wxH5ws1XvQBUiwukzNuOka8M2PG2ZrXbSnpMVBqTOEFtp9LHdxhMZD95CUMajgip8W5axtC7OMqf9gwl6psE1Osle0UUxd1Ad4g5G81UB4/vri/j0SSsj9jisfZqYGzzdVfCtGBw==";
        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgBXxP1I2Y2cNpyZZyhEXsM4VYCKv2Y33mXe8BI3KXaBPy87yuNam0YqxsM+ujo6FUXoFThU0XT+9AenFm2opDhG2SSV/8WkPVPx6ZeGLwn0CAudUEPVz3X4lbRJXHsEZWvv6jB8fbX6LlHWqyygy3aIaw0+tB49UTYTEXHfySKQAtaE4MS9GxbmlKb+Hhn+1LCxU6A1jEnS98ZByNPe5/6LDpCEQxrlzj4k+NW3AT5beSPKSmWlUsgsME8JSZLJcnV7omuV+DU5DdVL58QXJoEfFR6GZhxeKLoYtTxSw0xso/q3KLFcdAwfjix7HcmyDhplwwQSn5ydrb5j1Ci2raQIDAQAB";
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APP_ID, APP_PRIVATE_KEY, "json", AlipayConstants.CHARSET_UTF8, ALIPAY_PUBLIC_KEY, SIGN_TYPE2);
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
            System.out.println("结果3："+response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }


    /**
     * 调用失败
     * {"alipay_trade_query_response":{"code":"40004","msg":"Business Failed","sub_code":"ACQ.TRADE_NOT_EXIST","sub_msg":"交易不存在","buyer_pay_amount":"0.00","invoice_amount":"0.00","out_trade_no":"201801364481792961421312","point_amount":"0.00","receipt_amount":"0.00"},"sign":"II+b38B8MliBEpKSgJzLSMiS2jPNxMpA4F9uS4Y4eLunLoh+XvvS6PeCZi3uqDbvi+R5QgCGkowvi/Na0V+GDz1gS3Y0TPBPiWpDqhdW96Zm0jCrWsPyZyqDBW7vF9QHCypmiBe6ZdnwMQkCRVbLOksA1RElYSodwv3s2FTvq1wVOYbIgrRaS8g/ORK8rECgopJIvmifbD9ta8N6joJ4HaJm9cr37ZWKACcavZVm/VoY3ohSkO2Yss7dbl/0mOVnDA3/TYyuHowsUbcryZxolqAURI4FRQgO81jHIF8v/tjY4xcr8jtoVJl7uSw0xHXEHeJ4JsuG53C/VttjqgOA7A=="}
     * @throws Exception
     */

    public static void runQuery() throws Exception{
        String appid = "2015120700927798";
        String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCUbscpo605LV8J7EQ4b68aebURF0wJB8c5qwyEGYGFZi8NbaXXInbS4CU+V5d0OqMGYUOJkZWatuxpuYg2Y7bo6PLbWJfoBnYs5egt2Xisilkt9kAPWd2kIHeEMbYk30hUcBxI3syvdKiB24kRw45p3CRsy10AUMJdxjiKxErSrJw3uURcjN7Utwt7EsGb/shk5TI0X3XNI8yLvPaO/tIN3IStUDXHc/4VzkNN3Pt0Di4q62mIMDmQu4U5dO/9qJR3rtipOh7iR459SGMm6fvbYnirog831A5yTqRPe2EXaRiI+oKQ+48ER12hQk7FlGXBHGWbMKsXDDxU6jN2jnoFAgMBAAECggEAa1Ywb8wM1+wXbfWM38LjvSGF7nYKZf1Z9DndMym+n9jk7bD83DXv905foFV3EHXWriZn0HOW2+qry0h/V9WDz6mk9Tl7oC4MByu6Wm2I9jyVgMcyS+eQf796rf3E2aUdiPIJgiibm8WglUdMN6xSr/qh3IqzFidQZr+Q48hO00kk1AkFORHjIwWDCW1ftzTVCAyGMigt7mukSt0SBjJ23XBbcP9A3M1HUvImO8/jwYXp+8FSekDhHsMru6DSXYlmHJXRDETwG61BkBKauCi6q9V+QON4zWAncHgNJ6ULJ8O63CxylhmbVgnlwMLeVUHCVgprwAiWjLtAslw6nX5YAQKBgQDN7qxSYOpPqttFOj+uZfjRK4uO7yaM6wMnl5sHLfw9kyGpwdaUdjnAxokGwFpe7lUu3CfpAsl4RPUzB0IjwO4GwwqDq0cfd8tV1X1WH1L0tjV1F6wfMX8jXobTfQyD1SKX7AcN55K1BJT6pzAbqcd0X3d/Lu05zYqSzdRM7JCK1QKBgQC4hU10/yPgSwAKn6JHvAx32IrTKJ+V9o8r1S8WxcLP2yUG/adXLuzzU3RKTkxWP6nliweHzhG8HLwQ3Omaoujb+ibwCF/qq35PfKSeV/MO/Eth1ifBFvbFponF7wdlDf4PvRAR8nY9er39oCOpuwAnW1o3b5xXaplFXtOgZLFqcQKBgQCq4Kpy7OJg2wh5qDzhTNIrVvPF1iHp+rSybLWCPqE4wtbDEwooRPXlRZi8Vm5mqWNbr+kaC2r79lR0VEiIfHXGfJ9D5KLutKuv/Ke1B+cBEhTHpa34cEzsH2rHINN6rSe4Ymz0F7z/od6xInvj6HyNzw3MJjdIqIqdCFhqwD2sRQKBgQCZs7iMr4bz66WSM90awgLkxcUGFb2xTlt+WKtL3dnkCJi0VRBSqC0Kl0a0Krqd0ZQ1N/VC452UQ59IsKwKeo9bxekvYtp7APVLQvlhSMOXhQNJ8PjWd8mBZLM45F4ViG1MMOG1AXIKzjZ6kS2D0dhT8F2a9eaWTp5z0YSZA6dhkQKBgAsI02IC3BygZbTiFdbgWvqiham8qJ5jH5QNI1RHCVYsHQBYrgmafhmo3iQkBqItWp3df/vnZFXcc5fULXNBj8UAZ6KNNHzNPn2tbWtlTaS2GCuCN7vPxVjGnm7pT8S52c3nZlBj79GJQrhM+i5+vlPOmxQH/dcUdEUvGmza28ht";

        String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsYfjWGvbFQ89IplTye/+rVa8oZBvH6x+hQQYWF2ncoduvZSL0FezLN+hk9h9TnHAq5sOHomnr8Hz77peeqDUJPjJJLz+Nad+bRc5QcAPEREBMso8FvZOUYmb1ZoPE3heKTgzFW16cx0XPVTZc/fkOVc9zU4rSeDjbdpPc5bVIZ7Uk5uyjATi3UdmlhUEjZQb0LDGKauYbcJ8tPYgnWh+/1QWUV/0AiWvZSAo9chPLda2DctaEcFou/W2kA/HK60TNL1HzgMZSfShoXfomxJIaSOTwT0BeL7Gkw4zjD/AqfyiYCiwzOpNTLT6oX+FqmXwvJxar15kqggLL4Ke8tNI/QIDAQAB";


        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",appid,APP_PRIVATE_KEY,"json","GBK",ALIPAY_PUBLIC_KEY,"RSA2");
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"201801364096081330446336\"}");
/*
        request.setBizContent("{" +
                "\"out_trade_no\":\"201801364481792961421312\"}");
*/
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }

        String body = response.getBody();

        System.out.println(body);
        JSONObject jsonObject = JSONObject.parseObject(response.getBody());
        String rspObj = jsonObject.getString("alipay_trade_query_response");

        System.out.println(response.getTradeStatus());
        Map<String,String> resultMap = JSONObject.parseObject(rspObj, Map.class);
        resultMap.put("sign",jsonObject.getString("sign"));

        boolean flag = AlipaySignature.rsaCheckV1(resultMap, ALIPAY_PUBLIC_KEY, "UTF-8","RSA2");

        System.out.println("验签结果："+flag);

    }

    public static void run3()throws Exception{
        String data = "{\"gmt_create\":\"2019-08-26 15:58:33\",\"charset\":\"UTF-8\",\"seller_email\":\"mds.online@cn.mcd.com\",\"subject\":\"测试订单\",\"sign\":\"qYSmfxGuR4by3yBpd9UF12hpEz58ITmdWLXiLH5EEeFma8hFJhTks2ReAenteYnL7Nw6Ee9K2d+mqd+J5jAiDp/qwW9fhPtjHvqznCfK0458o7jgwWRgJx4+h1S9MCMb7wON8P7hg1e5WeHEwDuMxWnzs6yZjpoLAzOddB5Vm5IGYkv9JlXQCG2DI8Ely1AYglNlzLMQQt6NS6dUm+mz/OAYmr5ZTD1UcIQnjL8BUQGNoAhTpqWgE4hXsNNoqoxaFrxcT9LOyPBZsEsx+ola2+cRmq/YMK2E2rQTQHKrCX+m1ivwxa5vjT4SMkdVMJ7kXJGGIWSyOk/U1jNPbCn5ow==\",\"buyer_id\":\"2088502971826745\",\"invoice_amount\":\"0.01\",\"notify_id\":\"2019082600222155834026740558735815\",\"fund_bill_list\":\"[{\\\"amount\\\":\\\"0.01\\\",\\\"fundChannel\\\":\\\"ALIPAYACCOUNT\\\"}]\",\"notify_type\":\"trade_status_sync\",\"trade_status\":\"TRADE_SUCCESS\",\"receipt_amount\":\"0.01\",\"app_id\":\"2015120700927798\",\"buyer_pay_amount\":\"0.01\",\"sign_type\":\"RSA2\",\"seller_id\":\"2088511432039190\",\"gmt_payment\":\"2019-08-26 15:58:34\",\"notify_time\":\"2019-08-26 15:58:34\",\"version\":\"1.0\",\"out_trade_no\":\"201801363393765660958720\",\"total_amount\":\"0.01\",\"trade_no\":\"2019082622001426740554386869\",\"auth_app_id\":\"2015120700927798\",\"buyer_logon_id\":\"158****6877\",\"point_amount\":\"0.00\"}";


//        String data2 = "{\"aa\":\"11\",\"bb\":\"22\"}";

        //获取支付宝回调信息
        String pubkey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsYfjWGvbFQ89IplTye/+rVa8oZBvH6x+hQQYWF2ncoduvZSL0FezLN+hk9h9TnHAq5sOHomnr8Hz77peeqDUJPjJJLz+Nad+bRc5QcAPEREBMso8FvZOUYmb1ZoPE3heKTgzFW16cx0XPVTZc/fkOVc9zU4rSeDjbdpPc5bVIZ7Uk5uyjATi3UdmlhUEjZQb0LDGKauYbcJ8tPYgnWh+/1QWUV/0AiWvZSAo9chPLda2DctaEcFou/W2kA/HK60TNL1HzgMZSfShoXfomxJIaSOTwT0BeL7Gkw4zjD/AqfyiYCiwzOpNTLT6oX+FqmXwvJxar15kqggLL4Ke8tNI/QIDAQAB";

        Map<String,String> resultMap = JSONObject.parseObject(data,Map.class);

        boolean flag = AlipaySignature.rsaCheckV1(resultMap, pubkey, AlipayConstants.CHARSET_UTF8,"RSA2");
        if (!flag) {
            System.out.println("支付宝APP通知验证签名失败");
        } else {
            System.out.println("------支付宝APP通知验证签名成功-----");
        }

        String appId = resultMap.get("app_id");

        String tradeStatus = resultMap.get("trade_status");
        String transId = resultMap.get("out_trade_no");
        String transactionId = resultMap.get("trade_no");

        System.out.println(appId);
        System.out.println(tradeStatus);
        System.out.println(transId);
        System.out.println(transactionId);

        System.out.println(resultMap);
    }

    public static void run5()throws Exception{


        String data2 = "{\"gmt_create\":\"2019-08-26 14:42:55\",\"charset\":\"UTF-8\",\"seller_email\":\"3229539827@qq.com\",\"subject\":\"鼠标\",\"sign\":\"jhJXwbLf0CdqEJjphvQE5Mf53+dlIDBq1Qfd5U8qRla9y5FNOU6zk357323rqTIh2cNgbxF7M8kBpxgF7DnG+ZhCdBxEGXZkwHKGW5EQWoaEQ02DtheuHbcs1Tbvj264Hn1KywZIWfk/d7zIwpiPFqERt58YZ7Oia5AZdNCswmE=\",\"buyer_id\":\"2088312092995983\",\"invoice_amount\":\"0.01\",\"notify_id\":\"2019082600222144255095980559758588\",\"fund_bill_list\":\"[{\\\"amount\\\":\\\"0.01\\\",\\\"fundChannel\\\":\\\"PCREDIT\\\"}]\",\"notify_type\":\"trade_status_sync\",\"trade_status\":\"TRADE_SUCCESS\",\"receipt_amount\":\"0.01\",\"app_id\":\"2016090701862402\",\"buyer_pay_amount\":\"0.01\",\"sign_type\":\"RSA\",\"seller_id\":\"2088521338099297\",\"gmt_payment\":\"2019-08-26 14:42:55\",\"notify_time\":\"2019-08-27 00:10:13\",\"version\":\"1.0\",\"out_trade_no\":\"c201001201908261442502429088\",\"total_amount\":\"0.01\",\"trade_no\":\"2019082622001495980505531051\",\"auth_app_id\":\"2016112303141038\",\"buyer_logon_id\":\"103***@qq.com\",\"point_amount\":\"0.00\"}";
//        String data2 = "{\"aa\":\"11\",\"bb\":\"22\"}";

        //获取支付宝回调信息
        String pubkey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsYfjWGvbFQ89IplTye/+rVa8oZBvH6x+hQQYWF2ncoduvZSL0FezLN+hk9h9TnHAq5sOHomnr8Hz77peeqDUJPjJJLz+Nad+bRc5QcAPEREBMso8FvZOUYmb1ZoPE3heKTgzFW16cx0XPVTZc/fkOVc9zU4rSeDjbdpPc5bVIZ7Uk5uyjATi3UdmlhUEjZQb0LDGKauYbcJ8tPYgnWh+/1QWUV/0AiWvZSAo9chPLda2DctaEcFou/W2kA/HK60TNL1HzgMZSfShoXfomxJIaSOTwT0BeL7Gkw4zjD/AqfyiYCiwzOpNTLT6oX+FqmXwvJxar15kqggLL4Ke8tNI/QIDAQAB";

        Map<String,String> resultMap = JSONObject.parseObject(data2,Map.class);

        boolean flag = AlipaySignature.rsaCheckV1(resultMap, pubkey, AlipayConstants.CHARSET_UTF8,"RSA2");
        if (!flag) {
            System.out.println("支付宝APP通知验证签名失败");
        } else {
            System.out.println("------支付宝APP通知验证签名成功-----");
        }

        String appId = resultMap.get("app_id");

        String tradeStatus = resultMap.get("trade_status");
        String transId = resultMap.get("out_trade_no");
        String transactionId = resultMap.get("trade_no");

        System.out.println(appId);
        System.out.println(tradeStatus);
        System.out.println(transId);
        System.out.println(transactionId);

        System.out.println(resultMap);
    }




    @Test
    public void jsonRun(){
        Map<String,String> resultMap2 = new HashMap<>();

        Map<String,String[]> resultMap = new HashMap<>();
        String [] s = {"11","22"};
        String [] s2 = {"66"};
        resultMap.put("aa",s);
        resultMap.put("bb",s2);

        for (Iterator iter = resultMap.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) resultMap.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            resultMap2.put(name, valueStr);
        }

        System.out.println(JSONObject.toJSONString(resultMap2));
    }



    @Test
    public void run4()throws Exception{
        String result = "<xml><return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<return_msg><![CDATA[OK]]></return_msg>\n" +
                "<appid><![CDATA[wxace271fb4fda0bc2]]></appid>\n" +
                "<mch_id><![CDATA[1513315631]]></mch_id>\n" +
                "<nonce_str><![CDATA[ADcxSVxsLvLcdEnw]]></nonce_str>\n" +
                "<sign><![CDATA[2B1D5D4993BDF29C8EE5A9C558699ACE]]></sign>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<prepay_id><![CDATA[wx26181254368751bed8ca78c31609595000]]></prepay_id>\n" +
                "<trade_type><![CDATA[APP]]></trade_type></xml>";
        JSONObject jsonObject = WXPayXmlUtil.xmlToJson(result);


        System.out.println(jsonObject.getString("sign"));
    }






}
