package com.example.firstspringboot.now.http;

import com.example.firstspringboot.now.http.builder.IpnHCB;
import com.example.firstspringboot.now.http.builder.common.IpnHttpConfig;
import com.example.firstspringboot.now.http.builder.common.IpnHttpMethods;
import com.example.firstspringboot.now.http.builder.common.IpnUtils;
import com.example.firstspringboot.now.http.exception.IpnHttpProcessException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author sunwenwu
 * @Date 2021/01/07
 * @Description 使用HttpClient模拟发送（http/https）请求
 */

public class IpnHttpClientUtil {

    //默认采用的http协议的HttpClient对象
    private static HttpClient client4HTTP;

    //默认采用的https协议的HttpClient对象
    private static HttpClient client4HTTPS;

    static {
        try {
            client4HTTP = IpnHCB.custom().build();
            client4HTTPS = IpnHCB.custom().ssl().build();
        } catch (IpnHttpProcessException e) {
            IpnUtils.errorException("创建https协议的HttpClient对象出错：{}", e);
        }
    }

    /**
     * 判定是否开启连接池、及url是http还是https <br>
     * 如果已开启连接池，则自动调用build方法，从连接池中获取client对象<br>
     * 否则，直接返回相应的默认client对象<br>
     *
     * @param config 请求参数配置
     * @throws IpnHttpProcessException http处理异常
     */
    private static void create(IpnHttpConfig config) throws IpnHttpProcessException {
        if (config.client() == null) {//如果为空，设为默认client对象
            if (config.url().toLowerCase().startsWith("https://")) {
                config.client(client4HTTPS);
            } else {
                config.client(client4HTTP);
            }
        }
    }

    //-----------华----丽----分----割----线--------------
    //-----------华----丽----分----割----线--------------
    //-----------华----丽----分----割----线--------------

    /**
     * 以Get方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param headers  请求头信息
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static String get(HttpClient client, String url, Header[] headers, HttpContext context, String encoding) throws IpnHttpProcessException {
        return get(IpnHttpConfig.custom().client(client).url(url).headers(headers).context(context).encoding(encoding));
    }

    /**
     * 以Get方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return 返回结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static String get(IpnHttpConfig config) throws IpnHttpProcessException {
        return send(config.method(IpnHttpMethods.GET));
    }

    /**
     * 以Post方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param headers  请求头信息
     * @param parasMap 请求参数
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static String post(HttpClient client, String url, Header[] headers, Map<String, Object> parasMap, HttpContext context, String encoding) throws IpnHttpProcessException {
        return post(IpnHttpConfig.custom().client(client).url(url).headers(headers).map(parasMap).context(context).encoding(encoding));
    }

    /**
     * 以Post方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static String post(IpnHttpConfig config) throws IpnHttpProcessException {
        return send(config.method(IpnHttpMethods.POST));
    }

    /**
     * 以Put方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param parasMap 请求参数
     * @param headers  请求头信息
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static String put(HttpClient client, String url, Map<String, Object> parasMap, Header[] headers, HttpContext context, String encoding) throws IpnHttpProcessException {
        return put(IpnHttpConfig.custom().client(client).url(url).headers(headers).map(parasMap).context(context).encoding(encoding));
    }

    /**
     * 以Put方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static String put(IpnHttpConfig config) throws IpnHttpProcessException {
        return send(config.method(IpnHttpMethods.PUT));
    }

    /**
     * 以Delete方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param headers  请求头信息
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static String delete(HttpClient client, String url, Header[] headers, HttpContext context, String encoding) throws IpnHttpProcessException {
        return delete(IpnHttpConfig.custom().client(client).url(url).headers(headers).context(context).encoding(encoding));
    }

    /**
     * 以Delete方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static String delete(IpnHttpConfig config) throws IpnHttpProcessException {
        return send(config.method(IpnHttpMethods.DELETE));
    }

    /**
     * 以Patch方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param parasMap 请求参数
     * @param headers  请求头信息
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static String patch(HttpClient client, String url, Map<String, Object> parasMap, Header[] headers, HttpContext context, String encoding) throws IpnHttpProcessException {
        return patch(IpnHttpConfig.custom().client(client).url(url).headers(headers).map(parasMap).context(context).encoding(encoding));
    }

    /**
     * 以Patch方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static String patch(IpnHttpConfig config) throws IpnHttpProcessException {
        return send(config.method(IpnHttpMethods.PATCH));
    }

    /**
     * 以Head方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param headers  请求头信息
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static String head(HttpClient client, String url, Header[] headers, HttpContext context, String encoding) throws IpnHttpProcessException {
        return head(IpnHttpConfig.custom().client(client).url(url).headers(headers).context(context).encoding(encoding));
    }

    /**
     * 以Head方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static String head(IpnHttpConfig config) throws IpnHttpProcessException {
        return send(config.method(IpnHttpMethods.HEAD));
    }

    /**
     * 以Options方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param headers  请求头信息
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static String options(HttpClient client, String url, Header[] headers, HttpContext context, String encoding) throws IpnHttpProcessException {
        return options(IpnHttpConfig.custom().client(client).url(url).headers(headers).context(context).encoding(encoding));
    }

    /**
     * 以Options方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static String options(IpnHttpConfig config) throws IpnHttpProcessException {
        return send(config.method(IpnHttpMethods.OPTIONS));
    }

    /**
     * 以Trace方式，请求资源或服务
     *
     * @param client   client对象
     * @param url      资源地址
     * @param headers  请求头信息
     * @param context  http上下文，用于cookie操作
     * @param encoding 编码
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static String trace(HttpClient client, String url, Header[] headers, HttpContext context, String encoding) throws IpnHttpProcessException {
        return trace(IpnHttpConfig.custom().client(client).url(url).headers(headers).context(context).encoding(encoding));
    }

    /**
     * 以Trace方式，请求资源或服务
     *
     * @param config 请求参数配置
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static String trace(IpnHttpConfig config) throws IpnHttpProcessException {
        return send(config.method(IpnHttpMethods.TRACE));
    }

    /**
     * 下载文件
     *
     * @param client  client对象
     * @param url     资源地址
     * @param headers 请求头信息
     * @param context http上下文，用于cookie操作
     * @param out     输出流
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static OutputStream down(HttpClient client, String url, Header[] headers, HttpContext context, OutputStream out) throws IpnHttpProcessException {
        return down(IpnHttpConfig.custom().client(client).url(url).headers(headers).context(context).out(out));
    }

    /**
     * 下载文件
     *
     * @param config 请求参数配置
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static OutputStream down(IpnHttpConfig config) throws IpnHttpProcessException {
        if (config.method() == null) {
            config.method(IpnHttpMethods.GET);
        }
        return fmt2Stream(execute(config), config.out());
    }

    /**
     * 上传文件
     *
     * @param client  client对象
     * @param url     资源地址
     * @param headers 请求头信息
     * @param context http上下文，用于cookie操作
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static String upload(HttpClient client, String url, Header[] headers, HttpContext context) throws IpnHttpProcessException {
        return upload(IpnHttpConfig.custom().client(client).url(url).headers(headers).context(context));
    }

    /**
     * 上传文件
     *
     * @param config 请求参数配置
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static String upload(IpnHttpConfig config) throws IpnHttpProcessException {
        if (config.method() != IpnHttpMethods.POST && config.method() != IpnHttpMethods.PUT) {
            config.method(IpnHttpMethods.POST);
        }
        return send(config);
    }

    /**
     * 查看资源链接情况，返回状态码
     *
     * @param client  client对象
     * @param url     资源地址
     * @param headers 请求头信息
     * @param context http上下文，用于cookie操作
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static int status(HttpClient client, String url, Header[] headers, HttpContext context, IpnHttpMethods method) throws IpnHttpProcessException {
        return status(IpnHttpConfig.custom().client(client).url(url).headers(headers).context(context).method(method));
    }

    /**
     * 查看资源链接情况，返回状态码
     *
     * @param config 请求参数配置
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static int status(IpnHttpConfig config) throws IpnHttpProcessException {
        return fmt2Int(execute(config));
    }

    //-----------华----丽----分----割----线--------------
    //-----------华----丽----分----割----线--------------
    //-----------华----丽----分----割----线--------------

    /**
     * 请求资源或服务
     *
     * @param config 请求参数配置
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    public static String send(IpnHttpConfig config) throws IpnHttpProcessException {
        return fmt2String(execute(config), config.outenc());
    }


    /**
     * 请求资源或服务
     *
     * @param config 请求参数配置
     * @return 返回HttpResponse对象
     * @throws IpnHttpProcessException http处理异常
     */
    private static HttpResponse execute(IpnHttpConfig config) throws IpnHttpProcessException {
        create(config);//获取链接
        HttpResponse resp = null;

        try {
            //创建请求对象
            HttpRequestBase request = getRequest(config.url(), config.method());

            //设置超时
            request.setConfig(config.requestConfig());

            //设置header信息
            request.setHeaders(config.headers());

            //判断是否支持设置entity(仅HttpPost、HttpPut、HttpPatch支持)
            if (HttpEntityEnclosingRequestBase.class.isAssignableFrom(request.getClass())) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();

                if (request.getClass() == HttpGet.class) {
                    //检测url中是否存在参数
                    //注：只有get请求，才自动截取url中的参数，post等其他方式，不再截取
                    config.url(IpnUtils.checkHasParas(config.url(), nvps, config.inenc()));
                }

                //装填参数
                HttpEntity entity = IpnUtils.map2HttpEntity(nvps, config.map(), config.inenc());

                //设置参数到请求对象中
                ((HttpEntityEnclosingRequestBase) request).setEntity(entity);

                IpnUtils.info("请求地址：" + config.url());
                if (nvps.size() > 0) {
                    IpnUtils.info("请求参数：" + nvps.toString());
                }
                if (config.json() != null) {
                    IpnUtils.info("请求参数：" + config.json());
                }
            } else {
                int idx = config.url().indexOf("?");
                IpnUtils.info("请求地址：" + config.url().substring(0, (idx > 0 ? idx : config.url().length())));
                if (idx > 0) {
                    IpnUtils.info("请求参数：" + config.url().substring(idx + 1));
                }
            }
            //执行请求操作，并拿到结果（同步阻塞）
            resp = (config.context() == null) ? config.client().execute(request) : config.client().execute(request, config.context());

            if (config.isReturnRespHeaders()) {
                //获取所有response的header信息
                config.headers(resp.getAllHeaders());
            }

            //获取结果实体
            return resp;

        } catch (IOException e) {
            throw new IpnHttpProcessException(e);
        }
    }

    //-----------华----丽----分----割----线--------------
    //-----------华----丽----分----割----线--------------
    //-----------华----丽----分----割----线--------------

    /**
     * 转化为字符串
     *
     * @param resp     响应对象
     * @param encoding 编码
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    private static String fmt2String(HttpResponse resp, String encoding) throws IpnHttpProcessException {
        String body = "";
        try {
            if (resp.getEntity() != null) {
                // 按指定编码转换结果实体为String类型
                body = EntityUtils.toString(resp.getEntity(), encoding);
                IpnUtils.info(body);
            } else {//有可能是head请求
                body = resp.getStatusLine().toString();
            }
            EntityUtils.consume(resp.getEntity());
        } catch (IOException e) {
            throw new IpnHttpProcessException(e);
        } finally {
            close(resp);
        }
        return body;
    }

    /**
     * 转化为数字
     *
     * @param resp 响应对象
     * @return 返回处理结果
     * @throws IpnHttpProcessException http处理异常
     */
    private static int fmt2Int(HttpResponse resp) throws IpnHttpProcessException {
        int statusCode;
        try {
            statusCode = resp.getStatusLine().getStatusCode();
            EntityUtils.consume(resp.getEntity());
        } catch (IOException e) {
            throw new IpnHttpProcessException(e);
        } finally {
            close(resp);
        }
        return statusCode;
    }

    /**
     * 转化为流
     *
     * @param resp 响应对象
     * @param out  输出流
     * @return 返回输出流
     * @throws IpnHttpProcessException http处理异常
     */
    public static OutputStream fmt2Stream(HttpResponse resp, OutputStream out) throws IpnHttpProcessException {
        try {
            resp.getEntity().writeTo(out);
            EntityUtils.consume(resp.getEntity());
        } catch (IOException e) {
            throw new IpnHttpProcessException(e);
        } finally {
            close(resp);
        }
        return out;
    }

    /**
     * 根据请求方法名，获取request对象
     *
     * @param url    资源地址
     * @param method 请求方式
     * @return 返回Http处理request基类
     */
    private static HttpRequestBase getRequest(String url, IpnHttpMethods method) {
        HttpRequestBase request = null;
        switch (method.getCode()) {
            case 0:// HttpGet
                request = new HttpGet(url);
                break;
            case 1:// HttpPost
                request = new HttpPost(url);
                break;
            case 2:// HttpHead
                request = new HttpHead(url);
                break;
            case 3:// HttpPut
                request = new HttpPut(url);
                break;
            case 4:// HttpDelete
                request = new HttpDelete(url);
                break;
            case 5:// HttpTrace
                request = new HttpTrace(url);
                break;
            case 6:// HttpPatch
                request = new HttpPatch(url);
                break;
            case 7:// HttpOptions
                request = new HttpOptions(url);
                break;
            default:
                request = new HttpPost(url);
                break;
        }
        return request;
    }

    /**
     * 尝试关闭response
     *
     * @param resp HttpResponse对象
     */
    private static void close(HttpResponse resp) {
        try {
            if (resp == null) return;
            //如果CloseableHttpResponse 是resp的父类，则支持关闭
            if (CloseableHttpResponse.class.isAssignableFrom(resp.getClass())) {
                ((CloseableHttpResponse) resp).close();
            }
        } catch (IOException e) {
            IpnUtils.exception(e);
        } finally {
            IpnHttpConfig.clearThreadLocal();
        }
    }
}