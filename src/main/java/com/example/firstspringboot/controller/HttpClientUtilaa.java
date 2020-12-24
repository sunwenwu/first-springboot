package com.example.firstspringboot.controller;


import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author sunwenwu
 */
public class HttpClientUtilaa {
    private static Logger LOGGER = LoggerFactory.getLogger(HttpClientUtilaa.class);
    private static final int SO_TIME_OUT = 5000;
    private static final int CONNECTION_TIME_OUT = 5000;

    public static final String CONTENT_TYPE_NAME = "Content-Type";
    public static final String DEFAULT_CHARSET = "UTF-8";

    //xml格式
    public static final String CONTENT_TYPE_XML = "text/xml;charset=utf-8";
    //JSON 格式
    public static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";


    private static final RequestConfig defaultRequestConfig = RequestConfig.custom()
            .setSocketTimeout(SO_TIME_OUT)
            .setConnectTimeout(CONNECTION_TIME_OUT).build();


    private static volatile CloseableHttpClient httpClient;

    static {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSLv3");

            sslContext.init(null,null,null);
//            sslContext = SSLContext.getDefault();

//            sslContext =  SSLContexts.createDefault();

        } catch (Exception e){

        }

        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
                NoopHostnameVerifier.INSTANCE);
/*

        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,new String[]{"TLSv1.1"},
                null,
                NoopHostnameVerifier.INSTANCE);
*/

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", sslsf)
                .build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        // 最大连接数
        cm.setMaxTotal(200);
        // 每条路由最大连接数
        cm.setDefaultMaxPerRoute(40);
        RequestConfig requestConfig = RequestConfig.custom()
                //最大连接超时时间
                .setConnectTimeout(CONNECTION_TIME_OUT)
                //最大响应超时时间
                .setSocketTimeout(SO_TIME_OUT)
                //最大获取连接超时时间
                .setConnectionRequestTimeout(CONNECTION_TIME_OUT)
                .build();
        httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .setSSLSocketFactory(sslsf)
                .setKeepAliveStrategy((httpResponse, httpContext) -> {
                    //长连接最大存活时间
                    return 10 * 1000;
                })
                .build();

    }


    public static void main(String[] args) {

        doGet("https://www.baidu.com");
    }

  /*  public static void main(String[] args) throws Exception{
        System.out.println(SSLContexts.createDefault().getProtocol());
        System.out.println(SSLContext.getInstance("SSL").getProtocol());
        System.out.println(SSLContext.getInstance("SSLv3").getProtocol());
        System.out.println(SSLContext.getInstance("TLSv1").getProtocol());
        System.out.println(SSLContext.getInstance("TLSv1.1").getProtocol());
        System.out.println(SSLContext.getInstance("TLSv1.2").getProtocol());
        System.out.println(SSLContext.getInstance("TLS").getProtocol());

    }*/

    /**
     * @param url  请求地址
     * @return
     */
    public static String doGet(String url)  {
        return doGet(url,null,null);
    }

    /**
     * @param url  请求地址
     * @param requestConfig 请求配置
     * @return
     */
    public static String doGet(String url,RequestConfig requestConfig)  {
        return doGet(url,null,requestConfig);
    }

    /**
     * @param url  请求地址
     * @param headParams  自定义请求头
     * @return
     */
    public static String doGet(String url,Map<String,String> headParams)  {
        return doGet(url,headParams,null);
    }

    /**
     * @param url  请求地址
     * @param headParams  自定义请求头
     * @param requestConfig 请求配置
     * @return
     */
    public static String doGet(String url, Map<String,String> headParams,RequestConfig requestConfig)  {

        String result = "";

        CloseableHttpResponse httpResponse = null;
        long startTime = System.currentTimeMillis();

        try {
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader(CONTENT_TYPE_NAME, CONTENT_TYPE_JSON);

            buildHttpReqHead(headParams, httpGet);

            httpGet.setConfig(requestConfig == null ? defaultRequestConfig : requestConfig);
            httpResponse = httpClient.execute(httpGet);
            result = EntityUtils.toString(httpResponse.getEntity(), DEFAULT_CHARSET);

        } catch (Exception e) {
            LOGGER.error("HttpClientUtil.doGet|http get request exception error:{} ", e.getMessage(), e);
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    LOGGER.error("【doGet】 method request exception IO error:{} ", e.getMessage(), e);
                }
            }
            long endTime = System.currentTimeMillis();
            LOGGER.info("HttpClientUtil.doGet 调用:{}, 耗时：{}", url, (endTime - startTime));
        }
        return result;
    }

    /**
     * @param url  请求地址
     * @param bodyData  默认JSON格式请求报文
     * @return
     */
    public static String doPostForJson(String url, String bodyData)  {
        return doPost(url,bodyData,null,CONTENT_TYPE_JSON,null);
    }

    /**
     * @param url  请求地址
     * @param bodyData  默认JSON格式请求报文
     * @param headParams  自定义请求头
     * @return
     */
    public static String doPostForJson(String url,Map<String,String> headParams, String bodyData)  {
        return doPost(url,bodyData,headParams,CONTENT_TYPE_JSON,null);
    }


    /**
     * @param url  请求地址
     * @param bodyData  默认JSON格式请求报文
     * @param requestConfig 请求配置
     * @return
     */
    public static String doPostForJson(String url, String bodyData,RequestConfig requestConfig)  {
        return doPost(url,bodyData,null,CONTENT_TYPE_JSON,requestConfig);
    }

    /**
     * @param url  请求地址
     * @param bodyData  默认JSON格式请求报文
     * @param headParams  自定义请求头
     * @param requestConfig 请求配置
     * @return
     */
    public static String doPostForJson(String url,Map<String,String> headParams, String bodyData,RequestConfig requestConfig)  {
        return doPost(url,bodyData,headParams,CONTENT_TYPE_JSON,requestConfig);
    }

    /**
     * @param url  请求地址
     * @param bodyData  默认XML格式请求报文
     * @return
     */
    public static String doPostForXml(String url, String bodyData)  {
        return doPost(url,bodyData,null,CONTENT_TYPE_XML,null);
    }

    /**
     * @param url  请求地址
     * @param bodyData  默认XML格式请求报文
     * @param requestConfig 请求配置
     * @return
     */
    public static String doPostForXml(String url, String bodyData,RequestConfig requestConfig)  {
        return doPost(url,bodyData,null,CONTENT_TYPE_XML,requestConfig);
    }

    /**
     * @param url  请求地址
     * @param bodyData  默认XML格式请求报文
     * @param headParams  自定义请求头
     * @return
     */
    public static String doPostForXml(String url,Map<String,String> headParams, String bodyData)  {
        return doPost(url,bodyData,headParams,CONTENT_TYPE_XML,null);
    }


    /**
     * @param url  请求地址
     * @param bodyData  默认XML格式请求报文
     * @param headParams  自定义请求头
     * @param requestConfig 请求配置
     * @return
     */
    public static String doPostForXml(String url,Map<String,String> headParams, String bodyData,RequestConfig requestConfig)  {
        return doPost(url,bodyData,headParams,CONTENT_TYPE_XML,requestConfig);
    }

    /**
     * @param url  请求地址
     * @param bodyData  默认JSON格式请求报文
     * @param headParams  自定义请求头
     * @param contentType 默认json格式
     * @param requestConfig 请求配置
     * @return
     */
    public static String doPost(String url, String bodyData, Map<String,String> headParams,String contentType,RequestConfig requestConfig) {

        long startTime = System.currentTimeMillis();
        String result = "";

        CloseableHttpResponse httpResponse = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader(CONTENT_TYPE_NAME, StringUtils.isBlank(contentType) ? CONTENT_TYPE_JSON : contentType);

            buildHttpReqHead(headParams, httpPost);

            httpPost.setConfig(requestConfig == null ? defaultRequestConfig : requestConfig);
            httpPost.setEntity(new StringEntity(bodyData, Charset.forName(DEFAULT_CHARSET)));

            httpResponse = httpClient.execute(httpPost);

            result = EntityUtils.toString(httpResponse.getEntity(), DEFAULT_CHARSET);

        } catch (Exception ex) {
            LOGGER.error("【doPost】 method request exception error:{} ", ex.getMessage(), ex);
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    LOGGER.error("【doPost】 method request exception IO error:{} ", e.getMessage(), e);
                }
            }
            long endTime = System.currentTimeMillis();
            LOGGER.info("HttpClientUtil.doPost 调用:{}, 耗时：{}", url, (endTime - startTime));
        }
        return result;
    }


    private static void buildHttpReqHead(Map<String, String> headParams, HttpRequestBase httpMethed) {
        if (headParams != null && !headParams.isEmpty()) {
            headParams.keySet().forEach(key ->{
                httpMethed.addHeader(key,headParams.get(key));
            });
        }
    }


}




