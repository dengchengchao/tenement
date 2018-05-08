package com.dengchengchao.tenement.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @Author dengchengchao
 * @Time 2018/5/8
 * @Description
 */
public class DouBanInfo {
    /**
     * 结果数
     */
    private int count;

    /**
     * 主题数组
     */
    @JSONField(name = "topics")
    private List<CrawInfo> topics;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CrawInfo> getTopics() {
        return topics;
    }

    public void setTopics(List<CrawInfo> topics) {
        this.topics = topics;
    }




}
