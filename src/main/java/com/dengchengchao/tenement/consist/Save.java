package com.dengchengchao.tenement.consist;

import com.dengchengchao.tenement.utils.ProUtils;

import java.io.File;

/**
 * @Author dengchengchao
 * @Time 2018/5/4
 * @Description
 */
public class Save {

    public  static final String DIR_NAME= ProUtils.get("writer.dirpath")+ File.separator+ "tenement";

    public  static final String DIR_NAME_HISTORY=DIR_NAME+File.separator+"history.txt";

    public static final String SVAE_POSTFIX=".html";
}
