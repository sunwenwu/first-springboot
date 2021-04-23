package com.example.firstspringboot.now.account.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * http 常用类
 *
 * @author zhaoQiPeng
 */
public class HttpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);
    private static CloseableHttpClient closeableHttpClient;

    static {
        // 创建httpClient连接池
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        httpClientConnectionManager.setMaxTotal(1000);
        httpClientConnectionManager.setDefaultMaxPerRoute(100);
        // 在提交请求之前 测试连接是否可用
        httpClientConnectionManager.setValidateAfterInactivity(5000);

        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();

        // 客户端和服务器建立连接的timeout
        requestConfigBuilder.setConnectTimeout(30000);
        // 从连接池获取连接的timeout
        requestConfigBuilder.setConnectionRequestTimeout(30000);
        // 连接建立后，request没有回应的timeout(设置读取超时)
        requestConfigBuilder.setSocketTimeout(30000);

        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        clientBuilder.setDefaultRequestConfig(requestConfigBuilder.build());
        clientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
        // 设置HTTPClient连接池
        clientBuilder.setConnectionManager(httpClientConnectionManager);
        closeableHttpClient = clientBuilder.build();
    }

    public static String httpPostWithJson(JSONObject jsonObj, String url) throws Exception {

      //  LOGGER.info("收到发送POST请求，请求URL：{}  请求数据：{}  ", url, jsonObj.toJSONString());
        long starHttp = System.currentTimeMillis();

        CloseableHttpResponse response = null;
        String result = "";

        try {

            HttpPost post = new HttpPost(url);
            // 构造消息头
            post.setHeader("Content-type", "application/json; charset=utf-8");
            // 构建消息实体
            StringEntity entity = new StringEntity(jsonObj.toJSONString());
            entity.setContentEncoding("UTF-8");
            // 发送Json格式的数据请求
            entity.setContentType("application/json");
            post.setEntity(entity);

            response = closeableHttpClient.execute(post);

            //判断返回状态是否为 200
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                LOGGER.error("HTTP-POST 请求失败，statusCode：{}", statusCode);

            } else {

                HttpEntity httpEntity = response.getEntity();
                if (httpEntity != null) {
                    InputStream in = httpEntity.getContent();
                    StringBuffer stringBuffer = new StringBuffer();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    in.close();
                    result = stringBuffer.toString().trim();
                }


            }

        } catch (Exception e) {
            LOGGER.error("HttpUtil-->httpPostWithJson，处理 Http-Post请求发生异常, 请求URL：{}  请求数据：{}  error:{}  ", url, jsonObj.toJSONString(), e);
            throw e;

        } finally {

            if (response != null) {
                response.close();
            }

        }

        LOGGER.info("http-post 请求成功，  耗时：{}  result:{} ", (System.currentTimeMillis() - starHttp), result);
        return result;
    }


}
