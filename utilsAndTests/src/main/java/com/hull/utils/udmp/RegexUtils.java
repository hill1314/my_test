package com.hull.utils.udmp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
	public static String getRegText(Pattern pattern,String s){
		Matcher m = pattern.matcher(s);
		String code = null;
		if(m.find()){
			code = m.group();
		}
		return code;
	}
	
	public static boolean checkRegText(Pattern pattern,String s){
		Matcher m = pattern.matcher(s);
		return m.matches();
	}
	
	/**
	 * 匹配
	 * @param content 匹配的字符串
	 * @param regex 正则表达式
	 * @return
	 */
	public static List<String> matcher(String content, String regex) {
		List<String> list = new ArrayList<String>();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(content);
		while (m.find()) {
			String a = m.group(0);
			list.add(a);
		}
		return list;
	}
	
	/**
	 * 过滤掉非数字和-的字符串
	 * @param content
	 * @return
	 */
	public static String filterUnNumber(String content) {
        return filter(content, "[^-0-9.]");
    }
	
	/**
	 * 根据正则表达式过滤
	 * @param content
	 * @param regex
	 * @return
	 */
	public static String filter(String content, String regex) {
		return content.replaceAll(regex, "");
    }
	
	/**
	 * 匹配
	 * 
	 * @param str
	 *            匹配的字符串
	 * @param reg
	 *            正则表达式
	 * @return
	 */
	public static String matcher2String(String str, String reg) {
		List<String> list = matcher(str, reg);
		if(list!=null && list.size()>0)
			return list.get(0);
		else{
			return null;
		}
	}
}
