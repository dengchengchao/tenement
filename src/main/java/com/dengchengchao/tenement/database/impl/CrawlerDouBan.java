package com.dengchengchao.tenement.database.impl;
import com.dengchengchao.tenement.constist.DouBan;
import com.dengchengchao.tenement.database.Crawler;
import com.dengchengchao.tenement.domain.CrawInfo;
import com.dengchengchao.tenement.utils.HttpClientUtils;
import com.dengchengchao.tenement.utils.ProUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author dengchengchao
 * @Time 2018/5/5
 * @Description
 */
public class CrawlerDouBan implements Crawler {

    private Logger logger= LoggerFactory.getLogger(this.getClass());
    private static String page = ProUtils.get("douban.pages");

    public List<CrawInfo> getInfo(List<String> urls) {
        List<CrawInfo> crawInfos = new ArrayList<>();
        for (String url : urls) {
            int pagePer = Integer.valueOf(page);
            for (int i = 0; i < pagePer; i++) {
                crawInfos.addAll(getPerPageInfo(url, i*DouBan.PER_INDEX_PAGE));
            }
        }
        return crawInfos;
    }


    /**
     * 获取每页的信息
     */
    private List<CrawInfo> getPerPageInfo(String url, final int index) {
        List<NameValuePair> params = new ArrayList<NameValuePair>() {
            {
                add(new BasicNameValuePair(DouBan.START_INDEX, String.valueOf(index)));
            }
        };
        String result = HttpClientUtils.get(url, params);
        return filtResponse(result);
    }

    /**
     * 过滤掉其他的信息
     */
    private List<CrawInfo> filtResponse(String response) {

        List<CrawInfo> crawInfos = new ArrayList<>();
        String content = getTable(response);
        if (content.isEmpty()){
            logger.info("网页获取错误: "+response);
        }
        Matcher matcher = getTableRow(content);

        //将每个<tr></tr>中间的内容通过正则表达式提取出来
        while (matcher.find()) {
            String crawlerInfo = matcher.group();
            Pattern p = Pattern.compile(DouBan.REGEX_INFO);
            Matcher m = p.matcher(crawlerInfo);
            if (m.groupCount()<=0){
                logger.info("未获取到正确字段："+crawlerInfo);
            }
            while (m.find()) {
                crawInfos.add(new CrawInfo() {
                    {
                        setUrl(m.group(1));
                        setTitle(m.group(2));
                        //当前是为更新时间，我们需要获取发布时间
                        //第二次修改：逐个请求获取时间太慢，使用title过滤已推送的消息
                        // setTime(getRealTime(m.group(1)));
                    }
                });
            }
        }
        return crawInfos;
    }


    /**
     * 获取网页
     * <table class=\"olt\">
     * <p>
     * </table>
     * 中间的内容
     */
    private String getTable(String response) {
        if (null==response)return "";
        int endIndex = response.lastIndexOf(DouBan.SUB_TABLE_STR_END);
        int beginIndex = response.indexOf(DouBan.SUB_TABLE_STR_START);
        return endIndex > -1 && beginIndex > -1 ? response.substring(beginIndex, endIndex) : "";
    }


    /**
     * 获取
     * <table class=\"olt\">
     * <tr>
     * <p>
     * </tr>
     * </table>
     * 的内容
     */
    private Matcher getTableRow(String content) {
        Pattern pattern = Pattern.compile(DouBan.REGEX_CRAWLER);
        return pattern.matcher(content);
    }


    /**
     * 发送get请求，抓取文章时间
     *
     * 暂时不用，逐个网页请求太浪费时间，使用title过滤即可
     */
    private String getRealTime(String url) {
        String response = HttpClientUtils.get(url, null);
        Pattern p = Pattern.compile(DouBan.REGEX_TIME);
        Matcher m = p.matcher(response);
        return m.find() ? m.group(1) : "";
    }

}
