package com.dengchengchao.tenement.service;

import com.dengchengchao.tenement.consist.Save;
import com.dengchengchao.tenement.database.Crawler;
import com.dengchengchao.tenement.database.HistoricalData;
import com.dengchengchao.tenement.database.impl.CrawlerDouBan;
import com.dengchengchao.tenement.database.impl.CrawlerDouBanAPI;
import com.dengchengchao.tenement.database.impl.HistoricalDataFileImpl;
import com.dengchengchao.tenement.domain.CrawInfo;
import com.dengchengchao.tenement.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @Author dengchengchao
 * @Time 2018/5/6
 * @Description
 */
public class PushService extends Thread {

    /**
     * 记录已经推送了的消息，记录在本地文件中，方便下次启动服务器继续过滤文件.
     */
    private HistoricalData historicalData = new HistoricalDataFileImpl();


    /**
     * 记录已经推送了的消息，记录在内存中，方便快速查询.
     */
    private List<String> sendForwardCrawler = historicalData.getHistoricalData();


    /**
     * 爬虫组件
     */
    private Crawler crawler = new CrawlerDouBanAPI();

    /**
     * 消息推送组件
     */
    private Message message = new MessageHtmlFileImpl();

    static {
        FileUtils.makeDir(Save.DIR_NAME);
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void run() {
        printHead();
        while (true) {
            logger.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            /**
             * 获取url列表
             */
            List<String> urls = StringUtils.semicolonWordToList(ProUtils.get("douban.urls"));
            LogUtils.infoList(logger, urls, "URLS INFO: ");



            /**
             * 获取关键词列表
             */
            List<List<String>> keywords = StringUtils.andSymbolWordToList(ProUtils.get("douban.keyword"));
            LogUtils.infoListWithList(logger, keywords, "KEYWORDS INFO: ");


            /**
             * 启动爬虫
             */
            List<CrawInfo> crawInfos = crawler.getInfo(urls);
            logger.info("Crawler Start.........");
            logger.info(String.format("Crawl SIZE:%d", crawInfos.size()));

            for (CrawInfo crawInfo : crawInfos) {
                for (List<String> keywordList : keywords) {
                    //符合制定规则的 && 以前没有推送过的
                    if (isValidCrawlerInfo(keywordList, crawInfo.getTitle()) &&
                            !sendForwardCrawler.contains(crawInfo.getTitle())) {

                        //记录到已推送的消息订阅，过滤
                        sendForwardCrawler.add(crawInfo.getTitle());

                        //保存历史记录文件
                        historicalData.setHistoricalData(crawInfo.getTitle());

                        //推送消息到本地
                        sendMessage(crawInfo,keywordList.toString());
                        logger.info(keywordList.toString()+":");
                        logger.info(crawInfo.toString());
                    }
                }
            }
            WatiUtils.wait(Integer.valueOf(ProUtils.get("crawler.interval")) * 60);
        }
    }


    private void printHead() {
        String head = "\n" +
                "  __                                              __   \n" +
                "_/  |_  ____   ____   ____   _____   ____   _____/  |_ \n" +
                "\\   __\\/ __ \\ /    \\_/ __ \\ /     \\_/ __ \\ /    \\   __\\\n" +
                " |  | \\  ___/|   |  \\  ___/|  Y Y  \\  ___/|   |  \\  |  \n" +
                " |__|  \\___  >___|  /\\___  >__|_|  /\\___  >___|  /__|  \n" +
                "           \\/     \\/     \\/      \\/     \\/     \\/     ";
        logger.info(head);
    }


    /**
     * 推送信息
     */
    private void sendMessage(CrawInfo crawInfo, String fileName) {
        message.send(crawInfo.toString() + "\r\n", fileName);
    }


    /**
     * 从配置文件中获取过滤词汇，以分号分割
     */
    private boolean isValidCrawlerInfo(List<String> keywords, String title) {
        if (null != keywords) {
            for (String keyword : keywords) {
                if (title.contains(keyword)) {
                    return true;
                }
            }
            return false;
        }
        //没做任何配置的话，返回全部
        return true;

    }

}
