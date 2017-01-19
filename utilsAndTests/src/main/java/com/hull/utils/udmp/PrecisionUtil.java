package com.hull.utils.udmp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/** 
 * @description 提供对浮点型数据运算的工具类
 * @author Spring Cao
 * @version v1.0
*/
public class PrecisionUtil {
    private static Logger logger = LoggerFactory.getLogger(PrecisionUtil.class);
    
    /**
     * 提供精确的加法计算
     * @param v1 类型为double,被加数
     * @param v2 类型为double,加数
     * @return 计算结果，类型为double
    */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法计算
     * @param v1 类型为double,被减数
     * @param v2 类型为double,减数
     * @return 计算结果，类型为double
    */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法计算
     * @param v1 类型为double,被乘数
     * @param v2 类型为double,乘数
     * @return 计算结果，类型为double
    */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * @param v1 类型为double,被除数
     * @param v2 类型为double,除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 计算结果两个参数的商，类型为double
    */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            logger.error("The scale must be a positive integer or zero");
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
