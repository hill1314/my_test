package com.hull.utils.git;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterSql {

	/**
	 * 转换Javacode -》sql表名
	 * @param code
	 * @return
	 */
	public static String code2sql(String code){
		String resultStr;
		StringBuffer result = new StringBuffer();
		String pattern = "[A-Z]";
		// 创建 Pattern 对象
		Pattern r = Pattern.compile(pattern);

		int n = 0;
		for(int i=0;i<code.length();i++){
			// 现在创建 matcher 对象
			Matcher m = r.matcher(code.charAt(i)+"");
			if(m.find()){
				result.append(code.substring(n,i)+"_");
				n=i;
			}
		}
		result.append(code.substring(n,code.length()));
		if(result.toString().substring(0,1).equals("_")){
			resultStr =  result.toString().substring(1,result.length());
		}else{
			resultStr =  result.toString();
		}
		System.out.println(resultStr);
		return resultStr;
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

            	//过滤文本
//            	if(tempString.toUpperCase().contains("CREATE UNIQUE INDEX")){
//            		System.out.println(tempString);
//            	}

				//转换Javacode -》sql表名
				code2sql(tempString);
            }
			
            reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result.toString();
	}


	
	public static void main(String[] args) {
		String importFile = "C:/Users/hull/Desktop/changetables.sql";
		readFile(importFile);
	}

}
