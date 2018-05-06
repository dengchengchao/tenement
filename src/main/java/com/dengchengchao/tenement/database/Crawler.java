package com.dengchengchao.tenement.database;

import com.dengchengchao.tenement.domain.CrawInfo;

import java.util.List;
import java.util.Map;

/**
 * @Author dengchengchao
 * @Time 2018/5/5
 * @Description
 */
public interface Crawler {

    /**
     * 获取信息列表
     * @param urls  url列表
     * @return      爬虫信息POJO
     */
    List<CrawInfo> getInfo(List<String> urls);
}
