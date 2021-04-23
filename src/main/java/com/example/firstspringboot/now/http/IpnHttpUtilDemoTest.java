package com.example.firstspringboot.now.http;

import com.alibaba.fastjson.JSON;
import com.example.firstspringboot.now.http.builder.IpnHCB;
import com.example.firstspringboot.now.http.builder.common.IpnHttpConfig;
import com.example.firstspringboot.now.http.builder.common.IpnHttpHeader;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author :sunwenwu
 * @Date : 2021/1/7 11:28
 * @Description :
 */
@Slf4j
public class IpnHttpUtilDemoTest {

    private static int timeout = 1000 * 7;

    public static CloseableHttpClient httpClient = null;

    static {
        httpClient = IpnHCB.custom()
//                .sslpv(IpnSSLs.SSLProtocolVersion.TLSv1_2)  //设置TLS 协议版本  【需在ssl()方法调用前设置】
                .ignoreSSLCheck()    //忽略 SSL 校验,证书域名合法性忽略  【需在ssl()方法调用前设置】
                .ssl()   // 使用ssl安全连接
                .pool(200, 20)  //设置连接池
//                .openMonitorThread() //开启http连接监控，定时清理空闲连接  【pool()方法调用后设置，否则无效】
                .retry(5) //失败重试次数
//                .setKeepAliveTimeout(5000)  //连接存活时间
                .build();
    }

    public static void main(String[] args) throws Exception {

          get();

//        postJson();

//        postForm();

//        down();

//        upload();

    }


    public static void get() throws Exception {

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(timeout)
                .setConnectTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();


        String token = "eu5U717mK7ie1AaND58HFxBxZauKSnkBxPZegszdIxa_-3nu55AWvYxjRxAKZ0mWdWQTsQ3RBXiwjJa9qx93yu7uE5iXTsouPI7qhEmruGnYAUFB2_Eghqaa2E3B4AwmBz92Ka3eoGLOCvI-s_1ILOEUx0xwJ2M2GdVY00kB2saaP99-0Afy_eXp4EuIAI84zXM9qj33-3Z5r1zsN9WuFkeB_Cs";

        String url = "https://iamuat.crm.mcdonalds.com.cn:8080/api/v1.0.0/user/authbymeddyid?meddy_id=MEDDY903963184022994018";

        Header[] headers = IpnHttpHeader.custom()
                .contentType(IpnHttpHeader.HeaderConsts.APP_FORM_JSON)
                .other("access_token", token) //自定义头
                .build();

//        String url = "https://m-zbank-acct.cibfintech.com/";
//        String url = "https://fiora.suisuijiang.com/";
//        String url = "https://miniprogramuat.crm.mcdonalds.com.cn:8092";

/*
        IpnHttpClientUtil.get(IpnHttpConfig.custom().url(url));
        IpnHttpClientUtil.get(IpnHttpConfig.custom().url(url).client(IpnHCB.custom().build()));

        */

        String html = IpnHttpClientUtil.get(
                IpnHttpConfig.custom()
                        .url(url)
//                .timeout(requestConfig)  // 使用自定义的 requestConfig
                        .timeout(5000)           // 传入时间长度，创建的requestConfig  的连接时间、响应时间都为该值
                        .headers(headers)       //自定义请求头信息
                        .client(httpClient));

        System.out.println(html);

        System.out.println("================");
        System.out.println("================");
        System.out.println("================");


    }


    public static void postJson() throws Exception {

        System.out.println("--------json格式数据 post请求-------");

        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("appId", "158469309221453");
        reqMap.put("version", "1.0.0");
        reqMap.put("funcode", "ACCB013");

        Map<String, Object> reqMapData = new HashMap<>();
        reqMapData.put("orgReqId", "791327767684865");
        reqMapData.put("accountId", "1118711909945020");
        reqMapData.put("reqId", "174214247899");
        reqMapData.put("transType", "W01");
        reqMapData.put("elecReceiptType", "2");

        reqMap.put("data", JSON.toJSONString(reqMapData));

        String postUrl = "http://192.168.105.30:18008/api/biz";

        IpnHttpConfig client = IpnHttpConfig.custom()
                .url(postUrl)
                .json(JSON.toJSONString(reqMap))
//                .headers(headers)
//                .encoding("UTF-8")
                .client(httpClient);

        String post = IpnHttpClientUtil.post(client);

        System.out.println(post);


        System.out.println("--------json格式数据 post请求---结束----");

    }

    public static void postForm() throws Exception {

        System.out.println("--------Form 表单格式数据 post请求-------");

        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("userName", "admin");
        reqMap.put("password", "123456");


        String postUrl = "http://acc-test.ipaynow.cn/xxl-job-admin/login";

        Header[] headers = IpnHttpHeader.custom()
                //自定义头
                .other("sw", "1223")
                .build();

        IpnHttpConfig config = IpnHttpConfig.custom()
                .url(postUrl)
                .form(reqMap)
//                .returnRespHeaders() //返回响应头信息，通过config.headers() 获取返回
//                .encoding("UTF-8")
                .client(httpClient);

        String post = IpnHttpClientUtil.post(config);

        System.out.println(post);


        for (Header header : config.headers()) {
            System.out.println("key:" + header.getName() + "    =====      value:" + header.getValue());
        }


        System.out.println("--------Form格式数据 post请求---结束----");

    }


    public static void down() {
        try {
            System.out.println("--------下载测试-------");

            String url = "https://file.test.cibfintech.com/group1/M03/1E/E8/CmAFGV_QmUGAb8X6AAJ-Klc8Urg922.pdf";

            FileOutputStream outPutStream = new FileOutputStream(new File("d://httptest//zb_test.pdf"));
            IpnHttpClientUtil.down(IpnHttpConfig.custom()
                    .url(url)
//                                                .client(httpClient)
                    .out(outPutStream));
            outPutStream.flush();
            outPutStream.close();

            System.out.println("--------下载测试 结束-------");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void upload() {


        try {

            System.out.println("--------上传测试-------");


            String url = "http://127.0.0.1:8087/batch/upload";

            String filePath = "C:\\Users\\Ipaynow-112\\Desktop\\测试包\\test.xlsx";

            Map<String, Object> map = new HashMap<>();
            map.put("test", "上传测试 ");


            IpnHttpConfig httpConfig = IpnHttpConfig.custom()
                    .url(url)
                    .encoding("UTF-8")
                    //.files(filePaths)，如果服务器端有验证input 的name值，则请传递第二个参数，如果上传失败，则尝试第三个参数设置为true
                    .files(filePath, "file", true)
                    .map(map)
//                    .client(httpClient)
                    ;

            String upload = IpnHttpClientUtil.upload(httpConfig);

            System.out.println(upload);

            System.out.println("--------上传测试 结束-------");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
