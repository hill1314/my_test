package com.hull.utils.git;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.math.NumberUtils;


public class CodeTrans {
	
	/**
	 * . 转为get方法 
	 * @title
	 * @description 
	 * @date 2016年10月27日
	 * @param fcode
	 * @return
	 */
	public static String inverseGet(String fcode){
		StringBuffer result =new StringBuffer();
		if(fcode.contains(".")){
			String[] strs = fcode.split("\\.");
			String vName = inverse_2J(strs[0].trim());
			String vMethod = strs[1].trim();
			if(vName.equals("0")){	//0.00 这种情况 不用转义
				return fcode;
			}
			//变量转为小写
			result.append(String.valueOf(Character.toLowerCase(vName.charAt(0))) + vName.substring(1, vName.length()));
			//get方法
			StringBuffer msb = new StringBuffer();
			if(vMethod.contains("_")){ 		//表字段转为Java字段
				msb.append(inverse_2J(vMethod));
			}else{ //转为大写
				msb.append(String.valueOf(Character.toUpperCase(vMethod.charAt(0))) + vMethod.substring(1, strs[1].length()));
			}
			result.append(".get"+msb.toString()+"()");
		}else{
			result.append(inverse_2J(fcode));
		}
//		System.out.println(result.toString());
		return result.toString();
	} 
	
	public static String inverse_2J(String fcode){
		StringBuffer result =new StringBuffer();
		if(fcode.contains("_")){ 		//表字段转为Java字段
			String[] ms = fcode.split("_");
			for(int i=0;i<ms.length;i++){
				String m = ms[i];
				result.append(String.valueOf(Character.toUpperCase(m.charAt(0))) + m.substring(1, m.length()).toLowerCase());
			}
		}else{
			result.append(fcode);
		}
		return result.toString();
	}
	
	/**
	 * . 转为set方法 
	 * @title
	 * @description 
	 * @date 2016年10月27日
	 * @param fcode
	 * @return
	 */
	public static String inverseSet(String fcode){
		StringBuffer result =new StringBuffer();
		if(fcode.contains(".")){
			String[] strs = fcode.split("\\.");
			String vName = inverse_2J(strs[0].trim());
			String vMethod = strs[1].trim();
			//变量首字母转为小写
			result.append(String.valueOf(Character.toLowerCase(vName.charAt(0))) + vName.substring(1, vName.length()));
			//get方法
			StringBuffer msb = new StringBuffer();
			if(vMethod.contains("_")){ 		//表字段转为Java字段
				msb.append(inverse_2J(vMethod));
			}else{ //转为大写
				msb.append(String.valueOf(Character.toUpperCase(vMethod.charAt(0))) + vMethod.substring(1, strs[1].length()));
			}
			result.append(".set"+msb.toString()+"()");
		}
//		System.out.println(result.toString());
		return result.toString();
	} 
	
	/**
	 * = 赋值 set
	 * @title
	 * @description 
	 * @date 2016年10月27日
	 * @param fcode
	 * @return
	 */
	public static String inverseVal(String fcode){
		StringBuffer result =new StringBuffer();
		
		
		String note = "";
		for(int i=0;i<fcode.length();i++){
			if(isChinese(fcode.charAt(i))){
				note = fcode.substring(i,fcode.length());
				fcode = fcode.substring(0,i);
				break;
			}
		}
		
		
		if (fcode.contains("==") || fcode.contains("!=") || fcode.contains("<")|| fcode.contains(">")
				|| fcode.contains("<=") || fcode.contains(">=") ) {
			if(fcode.contains("if") && fcode.contains("(") && fcode.contains(")") && !fcode.substring(fcode.indexOf("(")+1, fcode.indexOf(")")).equals("")){
				String eb = fcode.substring(fcode.indexOf("(")+1, fcode.indexOf(")"));
				String ea = inverseEquel(eb);
				fcode = fcode.replace(eb, ea);
			}else{
				fcode = inverseEquel(fcode);
			}
			result.append(fcode);
		} else if (fcode.contains("=")) {
			String[] strs = fcode.split("=");
			String sStr = strs[0].trim();
			String vStr = strs[1].trim();
			String content = "";
			if (vStr.contains("//")) {
				content = vStr.substring(vStr.indexOf("//"), vStr.length());
			}
			if (vStr.contains(";")) {
				vStr = vStr.substring(0, vStr.indexOf(";"));
			}

			sStr = inverseSet(sStr.trim());
			if (vStr.contains(".")) {
				vStr = inverseGet(vStr.trim());
			}
			result.append(sStr.substring(0, sStr.length() - 1));
			result.append(vStr + ");");
			result.append(content);
		} else if(fcode.contains("strcpy")){
				
			fcode = fcode.substring(fcode.indexOf("(")+1,fcode.indexOf(")"));
			String[] strs = fcode.split(",");
			strs[0] = inverseSet(strs[0]);
			strs[1] = inverseGet(strs[1]);
			result.append(strs[0].substring(0, strs[0].length()-1));
			result.append(strs[1]+");");			
			
		}else if(fcode.contains(",")){
			String[] strs = fcode.split(",");
			for(int i=0;i<strs.length;i++){
				if(i!=0 && i!=strs.length) 	result.append(",");
				result.append(inverseGet(strs[i].trim()));
			}
		}else if(fcode.contains("+")){
			String[] strs = fcode.split("+");
			for(int i=0;i<strs.length;i++){
				if(i>=0){
					result.append(".add(");
				}
				result.append(inverseGet(strs[i].trim())+")");
				
			}
		}else {
			fcode = inverseGet(fcode.trim());
			result.append(fcode);
		}
		//& 返回值转换  
		
//		System.out.println(result.toString());
		result.append(" //"+note);
		
		return result.toString();
	}
	
	/**
	 * ，中文字符转换  ’转为“  If Else 转换  干掉 rpsCod
	 * @title
	 * @description
	 * 
	 * @param fcode
	 * @return
	 */
	public static String inverseC2E(String fcode){

		fcode = fcode.replace("，", ",");
		fcode = fcode.replace("’", "\"");
		fcode = fcode.replace("‘", "\"");
		fcode = fcode.replace("'", "\"");
		fcode = fcode.replace("“", "\"");
		fcode = fcode.replace("”", "\"");
		fcode = fcode.replace("If", "if");
		fcode = fcode.replace("Else", "else");
//		System.out.println(fcode);
		return fcode;
	}
	
	
	public static String inverseEquel(String fcode){
		StringBuffer result =new StringBuffer();

		if(fcode.contains("==")){
			String[] strs = fcode.split("==");
			String bs = strs[0].trim();
			String as = strs[1].trim();
			if(as.contains("\"") && (NumberUtils.isNumber(as.replace("\"", "")) || as.replace("\"", "").equals(""))){
				result.append(as+".equals("+inverseGet(bs)+")");
			}else{
				result.append(inverseGet(bs)+".equals("+inverseGet(as)+")");
			}
		}else if(fcode.contains("!=")){
			String[] strs = fcode.split("!=");
			String bs = strs[0].trim();
			String as = strs[1].trim();
			if(as.contains("\"") && NumberUtils.isNumber(as.replace("\"", ""))){
				result.append("!"+as+".equals("+inverseGet(bs)+")");
			}else{
				result.append("!"+inverseGet(bs)+".equals("+inverseGet(as)+")");
			}
		}else if(fcode.contains("<=")){
			String[] strs = fcode.split("<=");
			String bs = strs[0].trim();
			String as = strs[1].trim();
			result.append(inverseGet(bs)+"<="+inverseGet(as));
		}else if(fcode.contains(">=")){
			String[] strs = fcode.split(">=");
			String bs = strs[0].trim();
			String as = strs[1].trim();
			result.append(inverseGet(bs)+">="+inverseGet(as));
		}else if(fcode.contains("<")){
			String[] strs = fcode.split("<");
			String bs = strs[0].trim();
			String as = strs[1].trim();
			result.append(inverseGet(bs)+"<"+inverseGet(as));
		}else if(fcode.contains(">")){
			String[] strs = fcode.split(">");
			String bs = strs[0].trim();
			String as = strs[1].trim();
			result.append(inverseGet(bs)+">"+inverseGet(as));
		}
		
		return result.toString();
	}
		
	/**
	 * 读取文件中的内容进行处理
	 * @title
	 * @description
	 * 
	 * @param fileName
	 * @return
	 */
	public static String readFile(String fileName){
		StringBuffer result =new StringBuffer();

		File file = new File(fileName);
		BufferedReader reader = null;
		InputStreamReader isr;
		try {
			isr = new InputStreamReader(new FileInputStream(file), "GBK");
			reader = new BufferedReader(isr);
			String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
            	tempString = inverseC2E(tempString.trim());
            	result.append(inverseVal(tempString)+"\r");
            }
			
            reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result.toString();
	}

	public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION ) {
            return true;
        }
        return false;
    }
	
	public static String add(String fcode){
		StringBuffer result =new StringBuffer();

		if(fcode.contains("+")){
			String[] strs = fcode.split("+");
			for(int i=0;i<strs.length;i++){
				if(i>=0){
					result.append(".add(");
				}
				result.append(inverseGet(strs[i].trim())+")");
				
			}
		}
		return result.toString();
	}
	
	
	public static void main(String[] args) {
		String methodStr = readFile("d://code.txt");
		System.out.println(methodStr);
		
	}

	
}
