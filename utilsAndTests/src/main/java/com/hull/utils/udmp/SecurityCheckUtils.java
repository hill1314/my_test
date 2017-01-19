package com.hull.utils.udmp;

import java.util.regex.Pattern;

/**
 * 安全检查工具类
 * 包含基于正则表达式方式的XSS检查及放SQL注入攻击
 * 
 * @author Spring Cao
 *
 */
public class SecurityCheckUtils {

    /** Javascript key word array that need to check XSS */
    private static Pattern[] JAVASCRIPT_KEY_WORD_ARRAY = new Pattern[] { Pattern
        .compile(".*((<[\\x00]*(script|input|iframe|frame|a|img|object|applet)(\\s)+.*>)"
                + "|((http|https|ftp|telnet)://)|(<[\\x00]*script>)|(@import['\"]javascript:)).*") };
    
    private static Pattern[] SQL_INJECTION_PATTERNS = new Pattern[] { Pattern
        .compile("(/\\*.*\\*/.*=.*)|(('\\s{3}''\\s{3}')|(\\d+|.*'|\\))\\s*(and)\\s*'.*'\\s*=\\s*'.*\\s*('\\s*\\-\\-)?)"
                + "|((\\d+|.*'|\\));?\\s*(select|insert|update|delete|drop|truncate|union|declare|create|exec|having).*\\-\\-)"
                + "|(.*'\\s*(\\|\\||\\+)\\s*'.*'\\s*(\\|\\||\\+)\\s*'.*)") };
    
    /**
     * @description 检验输入字符串是否合法（不包含破坏js脚本信息）
     * @version
     * @title
     * @param value value类型为String，被校验字符串
     * @return 若字符串合法，返回true,否则返回false
    */
    public static boolean checkXss(String value) {
        if (value == null) {
            return true;
        }

        value = value.toLowerCase();
        for (int i = 0; i < JAVASCRIPT_KEY_WORD_ARRAY.length; i++) {
            if (JAVASCRIPT_KEY_WORD_ARRAY[i].matcher(value).find()) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * @description 检验输入字符串是否合法（不包含sql恶意注入信息）
     * @version
     * @title
     * @param value value类型为String，被校验字符串
     * @return  若字符串合法，返回true,否则返回false
    */
    public static boolean checkSqlInject(String value) {
        if (value == null) {
            return true;
        }

        value = value.toLowerCase();
        for (int i = 0; i < SQL_INJECTION_PATTERNS.length; i++) {
            if (SQL_INJECTION_PATTERNS[i].matcher(value).find()) {
                return false;
            }
        }
        return true;
    }
}
