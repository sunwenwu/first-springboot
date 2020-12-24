package com.example.firstspringboot.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author sunwenwu
 */
public class HttpClientUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);
    private static final int SO_TIME_OUT = 5000;
    private static final int CONNECTION_TIME_OUT = 5000;

    public static final String CONTENT_TYPE_NAME = "Content-Type";
    public static final String DEFAULT_CHARSET = "UTF-8";

    //xml格式
    public static final String CONTENT_TYPE_XML = "text/xml;charset=utf-8";
    //JSON格式
    public static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";
    //表单格式
    public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded;charset=UTF-8";

    private String reqUrl;
    private int socketTimeout;
    private int connectTimeout;

    private String contentType;
    private boolean isCheckSsl;

    private RequestConfig requestConfig;
    private Map<String,String> headParams;


    private static final RequestConfig defaultRequestConfig = RequestConfig.custom()
            .setSocketTimeout(SO_TIME_OUT)
            .setConnectTimeout(CONNECTION_TIME_OUT)
            .setConnectionRequestTimeout(CONNECTION_TIME_OUT)
            .build();


    public static void main(String[] args) throws Exception{
        SSLContext sslContext = SSLContexts.createDefault();

        System.out.println(sslContext.getProtocol());

        SSLContext aDefault = SSLContext.getDefault();
        System.out.println(aDefault.getProtocol());


    }


    private static volatile CloseableHttpClient httpClient;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // Increase max total connection to 200
        cm.setMaxTotal(200);
        // Increase default max connection per route to 20
        cm.setDefaultMaxPerRoute(50);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(SO_TIME_OUT)
                .setSocketTimeout(SO_TIME_OUT)
                .setConnectionRequestTimeout(CONNECTION_TIME_OUT)
                .build();
        httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .setKeepAliveStrategy((httpResponse, httpContext) -> {
                    // 连接存活时间 15秒
                    return 10 * 1000;
                })
                .build();


        new IdleConnectionMonitorThread(cm,"thread-httpClient").start();
    }

    private static volatile CloseableHttpClient httpMcdClient;

    static {
        SSLContext sslContext = SSLContexts.createDefault();

        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
                NoopHostnameVerifier.INSTANCE);

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", sslsf)
                .build();


        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);

        // Increase max total connection to 200
        cm.setMaxTotal(200);
        // Increase default max connection per route to 20
        cm.setDefaultMaxPerRoute(50);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(SO_TIME_OUT)
                .setSocketTimeout(SO_TIME_OUT)
                .setConnectionRequestTimeout(CONNECTION_TIME_OUT)
                .build();
        httpMcdClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .setSSLSocketFactory(sslsf)
                .setKeepAliveStrategy((httpResponse, httpContext) -> {
                    return 10 * 1000;
                })
                .build();

        new IdleConnectionMonitorThread(cm,"thread-httpClient").start();
    }

    //不校验 ssl 是否过期
    private static volatile CloseableHttpClient unSafeHttpClient;

    static {
        SSLConnectionSocketFactory sslsf = createSslConnectionSocketFactory();

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", new PlainConnectionSocketFactory())
                .register("https", sslsf)
                .build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(50);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(SO_TIME_OUT)
                .setSocketTimeout(SO_TIME_OUT)
                .setConnectionRequestTimeout(CONNECTION_TIME_OUT)
                .build();
        httpMcdClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .setSSLSocketFactory(sslsf)
                .setKeepAliveStrategy((httpResponse, httpContext) -> {
                    return 10 * 1000;
                })
                .build();

        new IdleConnectionMonitorThread(cm,"thread-unSafeHttpClient").start();
    }

    public static SSLConnectionSocketFactory createSslConnectionSocketFactory() {
        try {
            KeyStore truststore = KeyStore.getInstance(KeyStore.getDefaultType());
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(truststore, new TrustStrategy() {
                // 信任所有的证书
                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }

            }).build();
            return new SSLConnectionSocketFactory(sslContext,NoopHostnameVerifier.INSTANCE);
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            LOGGER.error(">>>> HttpClientUtil 的SSLConnectionSocketFactory方法失败 <<<<", e);
        }
        return null;
    }

    /**
     * @param url  请求地址
     * @return
     */
    public static String doGet(String url)  {
        return doGet(url,null,null);
    }

    /**
     * @param url  请求地址
     * @return
     */
    public static String doGet(String url,RequestConfig requestConfig)  {
        return doGet(url,null,requestConfig);
    }

    /**
     * @param url  请求地址
     * @return
     */
    public static String doGet(String url,Map<String,String> headParams)  {
        return doGet(url,headParams,null);
    }

    /**
     * @param url  请求地址
     * @param headParams  自定义请求头
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
                    LOGGER.error("【doPostForJson】 method request exception IO error:{} ", e.getMessage(), e);
                }
            }
            long endTime = System.currentTimeMillis();
            LOGGER.info("HttpClientUtil.doGetForJson 调用:{}, 耗时：{}", url, (endTime - startTime));
        }
        return result;
    }

    /**
     * @param url  请求地址
     * @param bodyData  json格式请求报文
     * @return
     */
    public static String doPostForJson(String url, String bodyData)  {
        return doPost(url,bodyData,null,CONTENT_TYPE_JSON);
    }

    /**
     * @param url  请求地址
     * @param bodyData  json格式请求报文
     * @return
     */
    public static String doPostForJson(String url,Map<String,String> headParams, String bodyData)  {
        return doPost(url,bodyData,headParams,CONTENT_TYPE_JSON);
    }

    /**
     * @param url  请求地址
     * @param bodyData  xml格式请求报文
     * @return
     */
    public static String doPostForXml(String url, String bodyData)  {
        return doPost(url,bodyData,null,CONTENT_TYPE_XML);
    }


    /**
     * @param url  请求地址
     * @param bodyData  默认JSON格式请求报文
     * @param headParams  自定义请求头
     * @param contentType 默认json格式
     * @return
     */
    public static String doPost(String url, String bodyData, Map<String,String> headParams,String contentType) {

        long startTime = System.currentTimeMillis();
        String result = "";

        CloseableHttpResponse httpResponse = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader(CONTENT_TYPE_NAME, StringUtils.isBlank(contentType) ? CONTENT_TYPE_JSON : contentType);

            buildHttpReqHead(headParams, httpPost);

            httpPost.setEntity(new StringEntity(bodyData, Charset.forName(DEFAULT_CHARSET)));

            httpResponse = httpMcdClient.execute(httpPost);

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


class IdleConnectionMonitorThread extends Thread {
    private final HttpClientConnectionManager connMgr;
    private volatile boolean shutdown;
    Logger logger = LoggerFactory.getLogger(IdleConnectionMonitorThread.class);

    public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr,String threadName) {
        super(threadName);
        this.connMgr = connMgr;
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                logger.info("HttpClientUtil-->定时清理失效http连接");
                synchronized (this) {
                    wait(5000);
                    connMgr.closeExpiredConnections();
                    connMgr.closeIdleConnections(10, TimeUnit.SECONDS);
                }
            }
        } catch (InterruptedException ex) {
            logger.error("unknown exception", ex);
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() {
        shutdown = true;
        synchronized (this) {
            notifyAll();
        }
    }
}