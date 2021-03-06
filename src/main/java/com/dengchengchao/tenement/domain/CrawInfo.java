package com.dengchengchao.tenement.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Author dengchengchao
 * @Time 2018/5/5
 * @Description
 */
public class CrawInfo {

    /**
     * 链接
     */
    @JSONField(name = "share_url")
    private String url;



    /**
     * 题目
     */
    private String title;


    /**
     * 时间
     */
    private String time;


    public String toString(){
        return String.format("<p><a href=\"%s\" target=\"_blank\">%s</a></p><br/>",url,title);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
