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
    public boolean send(String message) {
        DateFormat dateFormat = DateFormat.getDateInstance();
        String path = Save.DIR_NAME + File.separator + dateFormat.format(new Date()) + Save.SVAE_POSTFIX;
        FileUtils.write(path, message);
        return true;
    }
}
