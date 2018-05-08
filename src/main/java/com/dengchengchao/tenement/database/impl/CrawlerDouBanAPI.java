package com.dengchengchao.tenement.database.impl;

import com.alibaba.fastjson.JSON;
import com.dengchengchao.tenement.consist.DouBan;
import com.dengchengchao.tenement.database.Crawler;
import com.dengchengchao.tenement.domain.CrawInfo;

import com.dengchengchao.tenement.domain.DouBanInfo;
import com.dengchengchao.tenement.utils.HttpClientUtils;
import com.dengchengchao.tenement.utils.ProUtils;

import com.dengchengchao.tenement.utils.WatiUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.www.http.HttpClient;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author dengchengchao
 * @Time 2018/5/8
 * @Description
 */
public class CrawlerDouBanAPI implements Crawler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static String page = ProUtils.get("douban.pages");


    @Override
    public List<CrawInfo> getInfo(List<String> topics) {
        List<CrawInfo> crawInfos = new ArrayList<>();
        if (null == topics) return crawInfos;
        for (String topic : topics) {

            //间隔30s检测一个页面
            WatiUtils.wait(30);
            String url = DouBan.getDouBanAPIUrl(topic);
            int pagePer = Integer.valueOf(page);
            for (int i = 0; i < pagePer; i++) {
                //间隔10s请求一页
                WatiUtils.wait(10);
                crawInfos.addAll(getPerPageInfo(url, i * DouBan.PER_INDEX_PAGE));
            }
        }
        return crawInfos;
    }


    /**
     * 获取单页的信息
     */
    private List<CrawInfo> getPerPageInfo(String url, int index) {

        String response = HttpClientUtils.get(url, new ArrayList<NameValuePair>() {
            {
                add(new BasicNameValuePair("start", String.valueOf(index)));
            }
        });
        List<CrawInfo> result = JSON.parseObject(response, DouBanInfo.class).getTopics();
        if (null == result) {
            logger.error("ERROR:"+response);
            return new ArrayList<>();
        }
        return result;
    }


}
