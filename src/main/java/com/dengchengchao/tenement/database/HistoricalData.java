package com.dengchengchao.tenement.database;

import java.util.List;
import java.util.Map;

/**
 * @Author dengchengchao
 * @Time 2018/5/7
 * @Description 记录历史数据，免得每次重新启动都会重新抓取以前的消息
 */
public interface HistoricalData {

    /**
     * 获取历史记录
     * @return 每行信息
     */
    List<String> getHistoricalData();

    /**
     * 添加历史记录
     * @param title 信息
     */
    void setHistoricalData(String title);
}
