package com.dengchengchao.tenement.service;

import com.dengchengchao.tenement.consist.Save;
import com.dengchengchao.tenement.utils.FileUtils;
import com.dengchengchao.tenement.utils.ProUtils;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author dengchengchao
 * @Time 2018/5/7
 * @Description
 */
public class MessageHtmlFileImpl implements Message {

    private static String writePath = ProUtils.get("writer.dirpath");

    @Override
    public boolean send(String message, String fileName) {
        String data = DateFormat.getDateInstance().format(new Date());
        String dirName = Save.DIR_NAME + File.separator + data;
        FileUtils.makeDir(dirName);
        String path = dirName + File.separator + fileName + Save.SAVE_POSTFIX;

        //在第一创建文件的时候，将文件的Html路由更新到index中
        if (!FileUtils.exist(path)) {
            String addLine = String.format("<p><a href=\"%s\" target=\"_blank\">%s</a></p><br/>", Save.SINGLE_DIR_NAME + File.separator + data + File.separator + fileName + Save.SAVE_POSTFIX,
                    data + "/" + fileName);
            updateIndex(FileUtils.toHtmlPath(writePath) + "/" + Save.INDEX_NAME, addLine);
        }

        FileUtils.writeHtmlFile(path, message);
        return true;
    }

    /**
     * 自动更新index.html文件
     *
     * @param indexPath index.html的路径
     * @param addLine   需要添加的字符串
     */
    private void updateIndex(String indexPath, String addLine) {
        FileUtils.makeDir(indexPath);
        List<String> contentList = FileUtils.read(indexPath);
        if (!contentList.contains(addLine)) {
            FileUtils.writeHtmlFile(indexPath, addLine);
        }
    }


}

