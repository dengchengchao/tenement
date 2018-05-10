package com.dengchengchao.tenement.database.impl;

import com.dengchengchao.tenement.consist.Save;
import com.dengchengchao.tenement.database.HistoricalData;
import com.dengchengchao.tenement.utils.FileUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author dengchengchao
 * @Time 2018/5/7
 * @Description
 */
public class HistoricalDataFileImpl implements HistoricalData {

    @Override
    public List<String> getHistoricalData() {
        List<String> resultList = new ArrayList<>();
        File file = new File(Save.DIR_NAME_HISTORY);
        if (file.exists()) {
            resultList = FileUtils.read(Save.DIR_NAME_HISTORY);
        }
        return resultList;

    }


    @Override
    public void setHistoricalData(String title) {
        FileUtils.write(Save.DIR_NAME_HISTORY, title+"\r\n",true);
    }
}
