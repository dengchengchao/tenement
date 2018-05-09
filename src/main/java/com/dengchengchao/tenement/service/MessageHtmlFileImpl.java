package com.dengchengchao.tenement.service;

import com.dengchengchao.tenement.consist.Save;
import com.dengchengchao.tenement.utils.FileUtils;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

/**
 * @Author dengchengchao
 * @Time 2018/5/7
 * @Description
 */
public class MessageHtmlFileImpl implements Message {


    @Override
    public boolean send(String message,String fileName) {
        DateFormat dateFormat = DateFormat.getDateInstance();
        String dirName= Save.DIR_NAME + File.separator + dateFormat.format(new Date());
        FileUtils.makeDir(dirName);
        String path =dirName+File.separator+fileName+Save.SVAE_POSTFIX;
        FileUtils.write(path, message);
        return true;
    }
}
