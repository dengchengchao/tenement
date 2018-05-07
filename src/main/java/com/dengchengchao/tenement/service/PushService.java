package com.dengchengchao.tenement.service;

import com.dengchengchao.tenement.consist.Save;
import com.dengchengchao.tenement.database.Crawler;
import com.dengchengchao.tenement.database.impl.CrawlerDouBan;
import com.dengchengchao.tenement.domain.CrawInfo;
import com.dengchengchao.tenement.utils.FileUtils;
import com.dengchengchao.tenement.utils.LogUtils;
import com.dengchengchao.tenement.utils.ProUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


/**
 * @Author dengchengchao
 * @Time 2018/5/6
 * @Description
 */
public class PushService extends Thread {

    /**
     * 记录已经推送了的消息，以Crawler的title为key.
     */
    private Map<String, Crawler> sendForwardCrawler = new HashMap<>();

    private Crawler crawler = new CrawlerDouBan();

    private Message message=new MessageFileImpl();
    static {
        FileUtils.makeDir(Save.DIR_NAME);
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void run() {
        printHead();
        while (true) {


            List<String> urls = semicolonWordToList(ProUtils.get("douban.urls"));
            LogUtils.infoList(logger, urls, "URLS INFO: ");
            List<CrawInfo> crawInfos = crawler.getInfo(urls);


            List<String> keywords = semicolonWordToList(ProUtils.get("douban.keyword"));
            LogUtils.infoList(logger, keywords, "KEYWORDS INFO: ");
            logger.info(String.format("Crawl SIZE:%d",crawInfos.size()));
            for (CrawInfo crawInfo : crawInfos) {
                //符合制定规则的 && 以前没有推送过的
                if (isValidCrawlerInfo(keywords, crawInfo.getTitle()) &&
                        !sendForwardCrawler.containsKey(crawInfo.getTitle())) {

                    sendForwardCrawler.put(crawInfo.getTitle(), crawler);
                    sendMessage(crawInfo);
                    logger.info(crawInfo.toString());
                }
            }
            wait(ProUtils.get("crawler.interval"));
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

    //等待一定时间再抓取
    private void wait(String minute) {
        int interval = Integer.valueOf(minute);
        try {
            Thread.sleep(interval * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param semicolonWord 以分号(不区分中英文)隔开的句子
     * @return 以分号分割开的List
     */
    private List<String> semicolonWordToList(String semicolonWord) {
        if (null != semicolonWord) {
            semicolonWord = semicolonWord.replace("；", ";");
            return Arrays.asList(semicolonWord.split(";"));
        }
        return null;
    }

    /**
     * 保存信息
     */
    private void sendMessage(CrawInfo crawInfo) {
        message.send(crawInfo.toString()+"\r\n");
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
