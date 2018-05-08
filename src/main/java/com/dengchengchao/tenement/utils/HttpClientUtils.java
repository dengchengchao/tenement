package com.dengchengchao.tenement.utils;

import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @Author dengchengchao
 * @Time 2018/5/5
 * @Description
 */
public class HttpClientUtils {


    /**
     * 发送post请求--用于接口接收的参数为JSON字符串
     *
     * @param url    请求地址
     * @param params json格式的字符串
     * @return
     */
    public static String postJson(String url, String params) throws Exception {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        /*
         * 发送json字符串，这两句需要设置
         */
        httpPost.addHeader("Content-type", "application/json; charset=utf-8");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setEntity(new StringEntity(params, "UTF-8"));
        HttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        return result;
    }

    /**
     * 发送post请求--用于接口接收的参数为键值对
     *
     * @param url            请求地址
     * @param nameValuePairs 键值对
     * @return
     */
    public static String post(String url, List<NameValuePair> nameValuePairs) throws Exception {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        HttpResponse response = httpClient.execute(httpPost);
        String result = null;
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity());
        }
        httpClient.close();
        return result;
    }


    /**
     * http get请求
     */
    public static String get(String url, List<NameValuePair> nameValuePairs) {
        String result = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            String sb = "";
            if (null != nameValuePairs) {
                for (NameValuePair nvp : nameValuePairs) {
                    sb += nvp.getName() + "=" + nvp.getValue() + "&";
                }
                sb = sb.substring(0, sb.length() - 1);
                sb = URLDecoder.decode(sb, "UTF-8");
            }

            HttpGet httpGet = new HttpGet(url + "?" + sb);
            HttpResponse response = httpClient.execute(httpGet);

            //if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity());
            // }
            httpClient.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


}


