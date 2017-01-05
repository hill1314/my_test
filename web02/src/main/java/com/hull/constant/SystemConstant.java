package com.hull.constant;


import com.hull.utils.PropertiesUtil;

/**
 * Created with IntelliJ IDEA.
 * User: linql
 * Date: 13-6-7
 * Time: 下午4:01
 */
public class SystemConstant {

    /**
     * 系统标识
     */
    public static final boolean IS_PRODUCTION_MODEL = PropertiesUtil.readBoolean("sys", "sys.productionmodel");
    /**
     * AES加密标识-爱施德
     */
    public static final String AES_PWD_ASD = ("aisidi");
}
