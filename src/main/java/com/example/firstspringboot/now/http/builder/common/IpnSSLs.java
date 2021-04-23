package com.example.firstspringboot.now.http.builder.common;


import com.example.firstspringboot.now.http.exception.IpnHttpProcessException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * 设置ssl
 *
 * @version 1.0
 */
public class IpnSSLs {

    private static final SSLHandler simpleVerifier = new SSLHandler();
    private static SSLSocketFactory sslFactory;
    private static SSLConnectionSocketFactory sslConnFactory;
    private static IpnSSLs sslutil = new IpnSSLs();
    private SSLContext sc;

    public static IpnSSLs getInstance() {
        return sslutil;
    }

    public static IpnSSLs custom() {
        return new IpnSSLs();
    }

    // 重写X509TrustManager类的三个方法,信任服务器证书
    private static class SSLHandler implements X509TrustManager, HostnameVerifier {

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
            //return null;
        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
                                       String authType) throws CertificateException {
        }

        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
                                       String authType) throws CertificateException {
        }

        //信任任何域名
        @Override
        public boolean verify(String paramString, SSLSession paramSSLSession) {
            return true;
        }
    }

    ;

    // 信任主机
    public static HostnameVerifier getVerifier() {
        return simpleVerifier;
    }


    public synchronized SSLConnectionSocketFactory getSSLCONNUnSF(SSLProtocolVersion sslpv) throws IpnHttpProcessException {
        if (sslConnFactory != null)
            return sslConnFactory;
        try {
            SSLContext sc = getSSLContext(sslpv);
            sc.init(null, new TrustManager[]{simpleVerifier}, new SecureRandom());
            sslConnFactory = new SSLConnectionSocketFactory(sc, simpleVerifier);

        } catch (KeyManagementException e) {
            throw new IpnHttpProcessException(e);
        }
        return sslConnFactory;
    }

    public synchronized SSLConnectionSocketFactory getSSLCONNSF(SSLProtocolVersion sslpv) throws IpnHttpProcessException {
        if (sslConnFactory != null)
            return sslConnFactory;
        try {
            SSLContexts.createDefault();
            SSLContext sc = getSSLContext(sslpv);
            sc.init((KeyManager[]) null, (TrustManager[]) null, (SecureRandom) null);

            sslConnFactory = new SSLConnectionSocketFactory(sc);
        } catch (Exception e) {
            throw new IpnHttpProcessException(e);
        }
        return sslConnFactory;
    }


    public IpnSSLs customSSL(String keyStorePath, String keyStorepass) throws IpnHttpProcessException {
        FileInputStream instream = null;
        KeyStore trustStore = null;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            instream = new FileInputStream(new File(keyStorePath));
            trustStore.load(instream, keyStorepass.toCharArray());
            // 相信自己的CA和所有自签名的证书
            sc = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
        } catch (KeyManagementException e) {
            throw new IpnHttpProcessException(e);
        } catch (KeyStoreException e) {
            throw new IpnHttpProcessException(e);
        } catch (FileNotFoundException e) {
            throw new IpnHttpProcessException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new IpnHttpProcessException(e);
        } catch (CertificateException e) {
            throw new IpnHttpProcessException(e);
        } catch (IOException e) {
            throw new IpnHttpProcessException(e);
        } finally {
            try {
                instream.close();
            } catch (IOException e) {
            }
        }
        return this;
    }

    public SSLContext getSSLContext(SSLProtocolVersion sslpv) throws IpnHttpProcessException {
        try {
            if (sc == null) {
                sc = SSLContext.getInstance(sslpv.getName());
            }
            return sc;
        } catch (NoSuchAlgorithmException e) {
            throw new IpnHttpProcessException(e);
        }
    }

    /**
     * The SSL protocol version (SSLv3, TLSv1, TLSv1.1, TLSv1.2)
     *
     * @version 1.0
     */
    public enum SSLProtocolVersion {
        SSL("SSL"),
        SSLv3("SSLv3"),
        TLSv1("TLSv1"),
        TLSv1_1("TLSv1.1"),
        TLSv1_2("TLSv1.2"),
        TLSv1_3("TLSv1.3"),
        ;

        private String name;

        SSLProtocolVersion(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public static SSLProtocolVersion find(String name) {
            for (SSLProtocolVersion pv : SSLProtocolVersion.values()) {
                if (pv.getName().toUpperCase().equals(name.toUpperCase())) {
                    return pv;
                }
            }
            throw new RuntimeException("未支持当前ssl版本号：" + name);
        }

    }
}