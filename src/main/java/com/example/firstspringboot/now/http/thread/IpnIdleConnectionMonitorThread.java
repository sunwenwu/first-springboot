package com.example.firstspringboot.now.http.thread;

import org.apache.http.conn.HttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author :sunwenwu
 * @Date : 2021/1/18
 * @Description : 定时清理httpclient 连接池线程类
 */
public class IpnIdleConnectionMonitorThread extends Thread {

    private final HttpClientConnectionManager connMgr;
    private volatile boolean shutdown;

    //连接检查间隔时间 ， 单位毫秒
    private long exeBetweenTime;

    //最大连接空闲时间 ，单位毫秒
    private long maxIdleConnectionTime;

    Logger logger = LoggerFactory.getLogger(IpnIdleConnectionMonitorThread.class);

    public IpnIdleConnectionMonitorThread(HttpClientConnectionManager connMgr, String threadName) {
        super(threadName);
        this.connMgr = connMgr;
    }

    public IpnIdleConnectionMonitorThread(HttpClientConnectionManager connMgr, String threadName, long exeBetweenTime, long maxIdleConnectionTime) {
        super(threadName);
        this.connMgr = connMgr;
        this.exeBetweenTime = exeBetweenTime;
        this.maxIdleConnectionTime = maxIdleConnectionTime;
    }

    @Override
    public void run() {
        try {
            while (!shutdown) {
                logger.info("IpnHttpClientUtil-->定时清理失效http连接");
                synchronized (this) {

                    wait(exeBetweenTime <= 0 ? 5000 : exeBetweenTime);
                    connMgr.closeExpiredConnections();
                    connMgr.closeIdleConnections(maxIdleConnectionTime <= 0 ? 10000 : maxIdleConnectionTime, TimeUnit.MILLISECONDS);
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
