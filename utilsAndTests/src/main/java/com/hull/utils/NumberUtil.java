package com.sitech.crmtpd.common.util;

import com.sitech.crmtpd.common.constant.UtilConstant;
import com.sitech.crmtpd.common.exception.SystemException;

import java.text.DecimalFormat;
import java.text.Format;

public class NumberUtil {
    /**
     * 將字符串转换成long
     * <p/>
     * Long.ValueOf(String) ,参数String表示，指定 String 的值的 Long 对象。
     * <p/>
     * <p/>
     * 该参数被解释为表示一个有符号的十进制 long，该值与用该参数作为参数的 parseLong(java.lang.String) 方法得到的值非常相似。
     * <p/>
     * <p/>
     * 只是最后被转换为一个Long的包装类。
     * <p/>
     *
     * @param s 字符串
     * @return l 转换的long
     */
    public static Long longValueOf(String s) {
        Long l = 0l;
        try {
            l = Long.valueOf(s);
        } catch (NumberFormatException e2) {
            throw new SystemException(UtilConstant.MODEL_CODE_UTIL, e2, "200001", s, "数字");
        }
        return l;
    }

    /**
     * 將字符串转换成doubleValueOf
     * <p/>
     * doubleValueOf.doubleValueOf(String) ,参数String表示，指定 String 的值的 doubleValueOf 对象。
     * <p/>
     * <p/>
     * 该参数被解释为表示一个有符号的十进制 long，该值与用该参数作为参数的 parseLong(java.lang.String) 方法得到的值非常相似。
     * <p/>
     * <p/>
     * 只是最后被转换为一个Long的包装类。
     * <p/>
     *
     * @param s 字符串
     * @return l 转换的long
     */
    public static Double doubleValueOf(String s) {
        Double l = 0.00;
        try {
            l = Double.valueOf(s);
        } catch (NumberFormatException e2) {
            throw new SystemException(UtilConstant.MODEL_CODE_UTIL, e2, "200001", s, "数字");
        }
        return l;
    }

    /**
     * 字符型转换成Long型
     * <p/>
     * Long.ParseLong(String)方法，将 string 参数解析为有符号十进制 long，字符串中的字符必须都是十进制数字。
     * <p/>
     *
     * @param str 字符串
     * @return Long 转换后的Long型数字
     */
    public static Long parseLong(String str) {
        Long l = 0l;
        try {
            l = Long.parseLong(str);
        } catch (NumberFormatException e2) {
            throw new SystemException(UtilConstant.MODEL_CODE_UTIL, e2, "200001", str, "数字");
        }
        return l;
    }

    /**
     * 將字符串转换成Int
     * <p/>
     * Integer.valueOf把String型转换成Integer对象，是针对包装类而言
     * <p/>
     *
     * @param s 字符串
     * @return n 转换的Int
     */
    public static int intValueOf(String s) {
        int n = 0;
        try {
            n = Integer.valueOf(s);
        } catch (NumberFormatException e2) {
            throw new SystemException(UtilConstant.MODEL_CODE_UTIL, e2, "200001", s, "数字");
        }
        return n;
    }

    /**
     * 对象类型转换为Double数字类型
     *
     * @param obj 对象
     * @return 返回值
     */
    public static Double getDoubleFromObj(Object obj) {
        if (obj == null) {
            return 0.00;
        } else {
            return NumberUtil.doubleValueOf(obj.toString());
        }
    }


    /**
     * 对象类型转换为int数字类型
     *
     * @param obj 对象
     * @return 返回值
     */
    public static int getIntFromObj(Object obj) {
        if (StringUtil.isEmptyOrNull(obj)) {
            return 0;
        } else {
            return NumberUtil.intParseInt(obj.toString());
        }
    }

    /**
     * 对象类型转换为long数字类型
     *
     * @param obj 对象
     * @return 返回值
     */
    public static long getLongFromObj(Object obj) {
        if (StringUtil.isEmptyOrNull(obj)) {
            return 0;
        } else {
            return NumberUtil.longValueOf(obj.toString());
        }
    }



    /**
     * 將字符串转换成Int
     * <p/>
     * Integer.parseInt()把String型转换成Int型，是针对变量而言
     * <p/>
     *
     * @param s 字符串
     * @return n 转换的Int
     */
    public static int intParseInt(String s) {
        int n = 0;
        try {
            n = Integer.parseInt(s);
        } catch (NumberFormatException e2) {
            throw new SystemException(UtilConstant.MODEL_CODE_UTIL, e2, "200001", s, "数字");
        }
        return n;
    }

    /**
     * 将double格式的数字格式化为含两位小数的字符串
     *
     * @param money double数值
     * @return 两位小数的字符串
     */
    public static String formatDouble(Double money) {
        if (money == null) {
            return "0.00";
        } else {
            Format format = new DecimalFormat("#0.00");
            return format.format(money);
        }
    }
    /**
     * 比较两个long值大小并返回要求的值
     * @author gaocy 创建 2013-9-26
     * @param a , b俩long值； bigFlg：true-获取大值；false-获取小值
     * @return 或大或小的值
     */
    public static long compLongSize(long a, long b, boolean bigFlg) {
    	if (bigFlg) {
    		return a >= b ? a : b;
    	} else {
    		return a >= b ? b : a;
    	}
    }
}
