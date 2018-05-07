package com.dengchengchao.tenement.domain;

/**
 * @Author dengchengchao
 * @Time 2018/5/5
 * @Description
 */
public class CrawInfo {

    /**
     * 链接
     */
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
        return String.format("<a href=\"%s\">%s</a> +<br/>",url,title);
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
