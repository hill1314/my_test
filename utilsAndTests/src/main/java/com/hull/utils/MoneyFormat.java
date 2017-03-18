package com.hull.utils;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 将金额转换成汉字
 * <p>
 * @author sunhao 创建 2012-6-11
 * @version 1.0 Copyright(c) 北京思特奇信息技术股份有限公司
 */
public class MoneyFormat {
    public static final String MINUS = "负";
    
    public static final String ZERO = "零";

    public static final String ONE = "壹";

    public static final String TWO = "贰";

    public static final String THREE = "叁";

    public static final String FOUR = "肆";

    public static final String FIVE = "伍";

    public static final String SIX = "陆";

    public static final String SEVEN = "柒";

    public static final String EIGHT = "捌";

    public static final String NINE = "玖";

    public static final String DOT = ".";

    public static final String TEN = "拾";

    public static final String HUNDRED = "佰";

    public static final String THOUSAND = "仟";

    public static final String TEN_THOUSAND = "万";

    public static final String HUNDRED_MILLION = "亿";

    public static final String YUAN = "圆";

    public static final String JIAO = "角";

    public static final String FEN = "分";

    private static MoneyFormat moneyFormat;

    Map<String, String> chineseNumberMap = new HashMap<String, String>();// 存放0-9的数字和小数点对应的汉字

    Map<Integer, String> chineseMoneyPattern = new HashMap<Integer, String>();// 存放拾、佰、仟、万、亿

    NumberFormat numberFormat = NumberFormat.getInstance();

    private MoneyFormat() {
        numberFormat.setMaximumFractionDigits(4);// 设置最大的小数位数为4位
        numberFormat.setMinimumFractionDigits(2);// 设置最小的小数位数为2位
        numberFormat.setGroupingUsed(false);// 设置不启用分组

        chineseNumberMap.put("0", ZERO);
        chineseNumberMap.put("1", ONE);
        chineseNumberMap.put("2", TWO);
        chineseNumberMap.put("3", THREE);
        chineseNumberMap.put("4", FOUR);
        chineseNumberMap.put("5", FIVE);
        chineseNumberMap.put("6", SIX);
        chineseNumberMap.put("7", SEVEN);
        chineseNumberMap.put("8", EIGHT);
        chineseNumberMap.put("9", NINE);
        chineseNumberMap.put(".", DOT);
        chineseMoneyPattern.put(1, TEN);
        chineseMoneyPattern.put(2, HUNDRED);
        chineseMoneyPattern.put(3, THOUSAND);
        chineseMoneyPattern.put(4, TEN_THOUSAND);
        chineseMoneyPattern.put(5, TEN);
        chineseMoneyPattern.put(6, HUNDRED);
        chineseMoneyPattern.put(7, THOUSAND);
        chineseMoneyPattern.put(8, HUNDRED_MILLION);

    }

    public String format(String moneyStr) {
        checkPrecision(moneyStr);// 检查moneyStr小数部分的合法性
        String result = convertToChineseNumber(moneyStr);
        return addUnitsToChineseNumberString(result);
    }

    /* 加上人民币单位* */
    private String addUnitsToChineseNumberString(String moneyStr) {
        StringBuffer sb = new StringBuffer(moneyStr);
        sb.replace(sb.indexOf(DOT), sb.indexOf(DOT) + 1, YUAN);
        sb.insert(sb.length() - 1, JIAO);
        sb.insert(sb.length(), FEN);

        if (sb.indexOf("零角零分") != -1) {
            sb.replace(sb.indexOf("零角零分"), sb.length(), "整");
        } else if (sb.indexOf("零分") != -1) {
            sb.replace(sb.indexOf("零分"), sb.length(), "整");
        } else {
            if (sb.indexOf("零角") != -1) {
                sb.delete(sb.indexOf("零角"), sb.indexOf("零角") + 2);
            }
        }
        String result = sb.toString();
        if ("圆整".equals(result)) {
            result = ZERO + result;
        }
        return result;
    }

    /* 转换为人民币汉字大写形式* */
    private String convertToChineseNumber(String moneyStr) {
        String minus = "";
        if(moneyStr.indexOf("-") != -1){
            moneyStr = moneyStr.substring(1, moneyStr.length());
            minus = MINUS;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < moneyStr.length(); i++) {
            sb.append(chineseNumberMap.get(String.valueOf(moneyStr.charAt(i))));
        }
        int indexDot = moneyStr.indexOf(DOT);
        int moneyPatternCusor = 1;
        for (int i = indexDot - 1; i > 0; i--) {// 插入拾、佰、仟、万、亿
            sb.insert(i, chineseMoneyPattern.get(moneyPatternCusor));
            moneyPatternCusor = moneyPatternCusor == 8 ? 1 : moneyPatternCusor + 1;
        }
        String fractionPart = sb.substring(sb.indexOf(DOT));// 小数部分
        sb.delete(sb.indexOf(DOT), sb.length());// 删除小数部分
        while (sb.indexOf("零拾") != -1) {
            sb.replace(sb.indexOf("零拾"), sb.indexOf("零拾") + 2, ZERO);
        }
        while (sb.indexOf("零佰") != -1) {
            sb.replace(sb.indexOf("零佰"), sb.indexOf("零佰") + 2, ZERO);
        }
        while (sb.indexOf("零仟") != -1) {
            sb.replace(sb.indexOf("零仟"), sb.indexOf("零仟") + 2, ZERO);
        }
        while (sb.indexOf("零万") != -1) {
            sb.replace(sb.indexOf("零万"), sb.indexOf("零万") + 2, TEN_THOUSAND);
        }
        while (sb.indexOf("零亿") != -1) {
            sb.replace(sb.indexOf("零亿"), sb.indexOf("零亿") + 2, HUNDRED_MILLION);
        }
        while (sb.indexOf("零零") != -1) {
            sb.replace(sb.indexOf("零零"), sb.indexOf("零零") + 2, ZERO);
        }
        if (sb.indexOf(ZERO) == sb.length() - 1) {
            sb.delete(sb.indexOf(ZERO), sb.length());
        }
        sb.insert(0, minus);
        sb.append(fractionPart);
        return sb.toString();
    }

    /* 检查moneyStr小数部分的合法性* */
    private void checkPrecision(String moneyStr) {
        if (moneyStr.substring(moneyStr.indexOf(DOT) + 1, moneyStr.length()).length() > 2) {
            throw new RuntimeException(moneyStr + "小数部分大于2位");
        }

    }

    public String format(int moneyStr) {
        return format(numberFormat.format(moneyStr));
    }

    public String format(long moneyStr) {
        return format(numberFormat.format(moneyStr));
    }

    public String format(double moneyStr) {
        return format(numberFormat.format(moneyStr));
    }

    public String format(Number moneyStr) {
        return format(numberFormat.format(moneyStr));
    }

    public synchronized static MoneyFormat getInctance() {
        if (moneyFormat == null) {
            moneyFormat = new MoneyFormat();
        }
        return moneyFormat;
    }
}
