package com.dengchengchao.tenement.consist;

/**
 * @Author dengchengchao
 * @Time 2018/5/5
 * @Description
 */
public class DouBan {

    /**
     * get请求参数
     */
    public static final String START_INDEX = "start";


    /**
     * 获取response 的<table class="olt"> </table>的中间段
     */
    public static final String SUB_TABLE_STR_START = "<table class=\"olt\">";

    public static final String SUB_TABLE_STR_END = "</table>";


    /**
     * 豆瓣的每页显示个数为25
     */
    public static final int PER_INDEX_PAGE = 25;


    /**
     * 正则表达式 获取CrawlerInfo段
     */
    public static final String REGEX_CRAWLER = "<tr class=\"\">([\\s\\S]*?)</tr>";


    /**
     * 正则表达式 分别捕获href title time
     */
    public static final String REGEX_INFO = "<td class=\"title\">[\\s\\S]*?<a href=\"(.*?)\"[\\s\\S]*title=\"(.*?)\"[\\s\\S]*<td nowrap=\"nowrap\" class=\"time\">(.*)</td>";


    /**
     * 正则表达是 捕获time
     */
    public static final String REGEX_TIME = "<span class=\"color-green\">(.*?)</span>";
}
