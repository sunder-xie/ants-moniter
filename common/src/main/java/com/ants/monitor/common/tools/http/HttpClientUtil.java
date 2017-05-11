package com.ants.monitor.common.tools.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by zxg on 15/9/5.
 * 17:53
 * no bug,以后改代码的哥们，祝你好运~！！
 */
@Slf4j
public class HttpClientUtil {

    private static final int CONNECT_TIME_OUT = 60 * 1000;
    private static final int READ_TIME_OUT = 60 * 1000;

    private static final String defaultCharset = "UTF-8";

    private static CloseableHttpClient httpclient;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(100);
        cm.setDefaultMaxPerRoute(10);

        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIME_OUT)
                .setSocketTimeout(READ_TIME_OUT).build();

        httpclient = HttpClients.custom()
                .setDefaultRequestConfig(defaultRequestConfig)
                .setConnectionManager(cm)
                .build();
    }



    public static HttpClientResult get(String url) {
        return get(url, null);
    }

    public static HttpClientResult get(String url, List<NameValuePair> nvpList) {
        try {
            if (!CollectionUtils.isEmpty(nvpList)) {
                String params = (URLEncodedUtils.format(nvpList, defaultCharset));
                url = url + "?" + params;
            }

            HttpGet request = new HttpGet(url);

            CloseableHttpResponse response = httpclient.execute(request);
            HttpClientResult hcResult = new HttpClientResult();
            try {
                hcResult.setStatus(response.getStatusLine().getStatusCode());

                log.info("http get url:" + url + ", status:" + hcResult.getStatus());

                if (hcResult.getStatus() != HttpStatus.SC_OK) {
                    return hcResult;
                }

                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    hcResult.setData(EntityUtils.toString(resEntity, defaultCharset));
                }
                return hcResult;

            } finally {
                response.close();
            }

        } catch (Exception e) {
            log.error("http get url:" + url, e);
        }

        return null;
    }

}
