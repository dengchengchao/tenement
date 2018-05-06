package com.dengchengchao.tenement.constist;

import com.dengchengchao.tenement.utils.ProUtils;

/**
 * @Author dengchengchao
 * @Time 2018/5/5
 * @Description
 */
public class VX {

    public static final String URL_UPLOAD_IMG="https://api.weixin.qq.com/cgi-bin/media/uploadimg";


    public static final String URL_ACCESS_TOKEN="https://api.weixin.qq.com/cgi-bin/token";


    public static final String APP_KEY= ProUtils.get("VX.appid");

    public static final String APP_SECRET=ProUtils.get("VX.secret");
}
