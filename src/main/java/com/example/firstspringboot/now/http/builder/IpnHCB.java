package com.example.firstspringboot.now.http.builder;

import com.example.firstspringboot.now.http.builder.common.IpnSSLs;
import com.example.firstspringboot.now.http.exception.IpnHttpProcessException;
import com.example.firstspringboot.now.http.thread.IpnIdleConnectionMonitorThread;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

/**
 * @Author :sunwenwu
 * @Date : 2021/1/09
 * @Description : 自定义扩展 HttpClientBuilder
 */
public class IpnHCB extends HttpClientBuilder {

    public boolean isSetPool = false;//记录是否设置了连接池

    private boolean isSafeSsl = true;

    private IpnSSLs.SSLProtocolVersion sslpv = IpnSSLs.SSLProtocolVersion.SSL;//ssl 协议版本

    //用于配置ssl
    private IpnSSLs ssls = IpnSSLs.getInstance();

    private IpnHCB() {
    }

    public static IpnHCB custom() {
        return new IpnHCB();
    }

    //http连接连接池管理器
    private PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;

    //监控http连接池线程
    private IpnIdleConnectionMonitorThread monitorThread;

    /**
     * 开启监控连接池线程  【需要开启连接池才生效】
     * exeBetweenTime   默认值5000  连接检查间隔时间 ， 单位毫秒
     * maxIdleConnectionTime   默认值10000 连接最大空闲时间 ，单位毫秒
     */

    public IpnHCB openMonitorThread() {
        return openMonitorThread(0, 0);
    }

    public IpnHCB openMonitorThread(long exeBetweenTime, long maxIdleConnectionTime) {
        return openMonitorThread(new IpnIdleConnectionMonitorThread(poolingHttpClientConnectionManager, "Ipaynow-httpclient-", exeBetweenTime, maxIdleConnectionTime));
    }

    private IpnHCB openMonitorThread(IpnIdleConnectionMonitorThread monitorThread) {
        if (isSetPool) {
            this.monitorThread = monitorThread;
            monitorThread.start();
        }

        return this;
    }

    //关闭监控连接池线程
    public void closeMonitorThread() {
        if (monitorThread != null) {
            monitorThread.shutdown();
        }
    }


    public IpnHCB ignoreSSLCheck() {
        isSafeSsl = false;
        return this;
    }


    //设置连接最大存活时间
    public IpnHCB setKeepAliveTimeout(long keepAliveTimeout) {
        this.setKeepAliveStrategy((httpResponse, httpContext) -> {
            //长连接最大存活时间
            return keepAliveTimeout;
        });
        return this;
    }

    /**
     * 设置超时时间
     *
     * @param timeout 超市时间，单位-毫秒
     * @return 返回当前对象
     */
    @Deprecated
    public IpnHCB timeout(int timeout) {
        return timeout(timeout, true);
    }

    /**
     * 设置超时时间以及是否允许网页重定向（自动跳转 302）
     *
     * @param timeout        超时时间，单位-毫秒
     * @param redirectEnable 自动跳转
     * @return 返回当前对象
     */
    @Deprecated
    public IpnHCB timeout(int timeout, boolean redirectEnable) {
        // 配置请求的超时设置
        RequestConfig config = RequestConfig.custom()
                .setConnectionRequestTimeout(timeout)
                .setConnectTimeout(timeout)
                .setSocketTimeout(timeout)
                .setRedirectsEnabled(redirectEnable)
                .build();
        return (IpnHCB) this.setDefaultRequestConfig(config);
    }

    /**
     * 设置ssl安全链接
     *
     * @return 返回当前对象
     * @throws IpnHttpProcessException http处理异常
     */
    public IpnHCB ssl() throws IpnHttpProcessException {
        return (IpnHCB) this.setSSLSocketFactory(isSafeSsl ? ssls.getSSLCONNSF(sslpv) : ssls.getSSLCONNUnSF(sslpv));
    }


    /**
     * 设置自定义sslcontext
     *
     * @param keyStorePath 密钥库路径
     * @return 返回当前对象
     * @throws IpnHttpProcessException http处理异常
     */
    public IpnHCB ssl(String keyStorePath) throws IpnHttpProcessException {
        return ssl(keyStorePath, "nopassword");
    }

    /**
     * 设置自定义sslcontext
     *
     * @param keyStorePath 密钥库路径
     * @param keyStorepass 密钥库密码
     * @return 返回当前对象
     * @throws IpnHttpProcessException http处理异常
     */
    public IpnHCB ssl(String keyStorePath, String keyStorepass) throws IpnHttpProcessException {
        this.ssls = IpnSSLs.custom().customSSL(keyStorePath, keyStorepass);
        return ssl();
    }


    /**
     * 设置连接池（默认开启https）
     *
     * @param maxTotal           最大连接数
     * @param defaultMaxPerRoute 每个路由默认连接数
     * @return 返回当前对象
     * @throws IpnHttpProcessException http处理异常
     */
    public IpnHCB pool(int maxTotal, int defaultMaxPerRoute) throws IpnHttpProcessException {

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", isSafeSsl ? ssls.getSSLCONNSF(sslpv) : ssls.getSSLCONNUnSF(sslpv)).build();
        //设置连接池大小
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connManager.setMaxTotal(maxTotal);// Increase max total connection to $maxTotal
        connManager.setDefaultMaxPerRoute(defaultMaxPerRoute);// Increase default max connection per route to $defaultMaxPerRoute
        isSetPool = true;
        this.poolingHttpClientConnectionManager = connManager;
        return (IpnHCB) this.setConnectionManager(connManager);
    }

    /**
     * 设置代理
     *
     * @param hostOrIP 代理host或者ip
     * @param port     代理端口
     * @return 返回当前对象
     */
    public IpnHCB proxy(String hostOrIP, int port) {
        // 依次是代理地址，代理端口号，协议类型
        HttpHost proxy = new HttpHost(hostOrIP, port, "http");
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
        return (IpnHCB) this.setRoutePlanner(routePlanner);
    }

    /**
     * 重试（如果请求是幂等的，就再次尝试）
     *
     * @param tryTimes 重试次数
     * @return 返回当前对象
     */
    public IpnHCB retry(final int tryTimes) {
        return retry(tryTimes, false);
    }

    /**
     * 重试（如果请求是幂等的，就再次尝试）
     *
     * @param tryTimes               重试次数
     * @param retryWhenInterruptedIO 连接拒绝时，是否重试
     * @return 返回当前对象
     */
    public IpnHCB retry(final int tryTimes, final boolean retryWhenInterruptedIO) {
        // 请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= tryTimes) {// 如果已经重试了n次，就放弃
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                    return false;
                }
                if (exception instanceof InterruptedIOException) {// 超时
                    return retryWhenInterruptedIO;
                }
                if (exception instanceof UnknownHostException) {// 目标服务器不可达
                    return true;
                }
                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                    return false;
                }
                if (exception instanceof SSLException) {// SSL握手异常
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };
        this.setRetryHandler(httpRequestRetryHandler);
        return this;
    }

    /**
     * 设置ssl版本<br>
     * 如果您想要设置ssl版本，必须<b><span style="color:red">先调用此方法，再调用ssl方法</span><br>
     * 仅支持 SSLv3，TSLv1，TSLv1.1，TSLv1.2</b>
     *
     * @param sslpv 版本号
     * @return 返回当前对象
     */
    public IpnHCB sslpv(String sslpv) {
        return sslpv(IpnSSLs.SSLProtocolVersion.find(sslpv));
    }


    /**
     * 设置ssl版本<br>
     * 如果您想要设置ssl版本，必须<b>先调用此方法，再调用ssl方法<br>
     * 仅支持 SSLv3，TSLv1，TSLv1.1，TSLv1.2</b>
     *
     * @param sslpv 版本号
     * @return 返回当前对象
     */
    public IpnHCB sslpv(IpnSSLs.SSLProtocolVersion sslpv) {
        this.sslpv = sslpv;
        return this;
    }
}