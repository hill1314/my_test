package com.hull.utils.git;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil2 {

    public static void readExcel(String srcPath, String resultFile, String importFile) {
        try {
            Workbook wb = WorkbookFactory.create(new File(importFile)); 
            //获取Excel中的某一个数据表..也可以通过Sheet名称来获取,即Workbook.getSheet("定制对账文件")  
            Sheet sheet = wb.getSheetAt(0); 
            Row row = null; 
            //获取Excel的总行数:Sheet.getLastRowNum()+1(需要+1)  
            String sequenceName = "";
            String sequenceNameBak = "";
            String methodStr = "";
            int num = 1;
            
            HashMap<String,ArrayList<String>> sequenceMap = new HashMap<String,ArrayList<String>>();
            ArrayList<String> methodList = new ArrayList<String>();
            
            for(int i=sheet.getFirstRowNum(); i<sheet.getLastRowNum()+1; i++){ 
                //获取数据表里面的某一行  
                row = sheet.getRow(i); 
                //获取Excel的总列数:Row.getLastCellNum()(不用+1)  
                Cell cell = row.getCell(0);
                if(cell!=null){                	
                	sequenceName = cell.getStringCellValue();
                }
                if(sequenceName.equals("")){
                	sequenceName = sequenceNameBak;
                }
                if(!sequenceNameBak.equals(sequenceName)){
                	num = 1;
                }
                //方法名
                methodStr = row.getCell(1).getStringCellValue(); 
                String methodName = splitMethod(methodStr);
                //描述
                Cell descCell = row.getCell(2);
                String chineseDesc = "";
                if(descCell!=null){
                	chineseDesc = descCell.getStringCellValue(); 
                }
                
                //解析方法中的参数
                HashMap<String,ArrayList<String>> paraMap = getMethodParas(methodStr);
                
                //导出前先检查方法参数是否一致
//                checkMethod(srcPath, num, sequenceName, methodName,paraMap);
                //导出到excel
                transform(srcPath, num, sequenceName,chineseDesc, methodName, resultFile,paraMap);
                
                num++;
                sequenceNameBak = sequenceName;
                
            } 
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
	public static String splitMethod(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder(s.length());
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

//            if (cn.com.git.udmp.common.utils.StringUtils.isAlphanumeric(String.valueOf(c))) {
//                sb.append(c);
//            }else {
//                return sb.toString();
//            }
        }

        return sb.toString();
    }
    
   
    public static String splitChinese(String s) {
        boolean flag = false;
        if (s == null) {
            return null;
        }
        
        StringBuilder sb = new StringBuilder(s.length());
       
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(flag)
            {
                sb.append(c);
            }else if (isChinese(c)) {
                flag = true;
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
    
   
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION ) {
            return true;
        }
        return false;
    }


    /**
     * @title  在接口列表中 找到指定的方法名，解析其参数信息
     * @description 在接口列表中 找到指定的方法名，解析其参数信息
     * @param srcPath
     * @param line
     * @param sequenceName
     * @param chineseDesc
     * @param methodName
     * @param resultFile
     * @param paraMap
     */
    private static void transform(String srcPath, int line, String sequenceName,String chineseDesc, String methodName,
            String resultFile, HashMap<String, ArrayList<String>> paraMap) {
        try {
        	
            boolean flag = true;
            int lineNum = 0;
            FileInputStream fs = new FileInputStream(resultFile); // 获取d://test.xls
            Workbook wb =  WorkbookFactory.create(fs);
            Sheet sheet = wb.getSheetAt(0); // 获取到工作表，因为一个excel可能有多个工作表
            Row row = sheet.getRow(0); // 获取第一行（excel中的行默认从0开始，所以这就是为什么，一个excel必须有字段列头），即，字段列头，便于赋值
            
            FileOutputStream out = new FileOutputStream(resultFile); // 向d://test.xls中写数据
            String filedir = srcPath;
            File file = new File(filedir);
            if (file.isDirectory()) {
                String[] list = file.list();
                list = insert(list, "AtomicUtils.java");
                list = insert(list, "BizUtils.java");
                for (String fileName : list) {
                    if (fileName.equals("impl")) {
                        continue;
                    }
                    String className = fileName.split("\\.")[0];
                    Class clazz;
                    if (className.equals("AtomicUtils") || className.equals("BizUtils")) {
                        clazz = Class.forName("cn.com.git.ncl.aplus.utils." + className);
                    } else {
                        clazz = Class.forName("cn.com.git.ncl.aplus.modules.service." + className);
                    }
                    Method[] methods = clazz.getMethods();
                    for (Method method : methods) {
                        if (methodName.toLowerCase().equals(method.getName().toLowerCase())) {
                            flag = false;
                            Parameter[] parameters = method.getParameters();
                            int i = 1;
                            
                            if(parameters.length!=paraMap.get("reqParas").size()){
                            	System.out.println("**************************************");
                                System.out.println("序列 "+sequenceName+" 中 "+methodName+" 方法参数与实际不一致：");
                            	System.out.println("实际方法中的参数："+Arrays.asList(parameters).toString());
                            	System.out.println("序列中的参数："+paraMap.get("reqParas").toString());
                            	System.out.println("**************************************");
                            	
                            	row = sheet.createRow((short) (sheet.getLastRowNum() + 1)); // 在现有行号后追加数据
                                row.createCell(0).setCellValue(sequenceName); // 序列名
                                row.createCell(1).setCellValue(line); // 方法序号
                                row.createCell(2).setCellValue(methodName); //  方法名
                                row.createCell(3).setCellValue(chineseDesc); // 方法描述
                            	
                            	continue;
                            }
                            
                            if(parameters.length==0){
                            	row = sheet.createRow((short) (sheet.getLastRowNum() + 1)); // 在现有行号后追加数据
                                row.createCell(0).setCellValue(sequenceName); // 序列名
                                row.createCell(1).setCellValue(line); // 方法序号
                                row.createCell(2).setCellValue(methodName); //  方法名
                                row.createCell(3).setCellValue(chineseDesc); // 方法描述
                                row.createCell(4).setCellValue(""); 			// 参数序号
                                row.createCell(5).setCellValue("");
                                row.createCell(6).setCellValue("");
                                row.createCell(7).setCellValue("");
                                row.createCell(8).setCellValue(className); // 接口类名
                                String respParas = paraMap.get("respParas").size()>0?paraMap.get("respParas").toString():"";
                                row.createCell(9).setCellValue(respParas); // 出参
                            }
                            for (Parameter p : parameters) {
                                row = sheet.createRow((short) (sheet.getLastRowNum() + 1)); // 在现有行号后追加数据
                                row.createCell(0).setCellValue(sequenceName); // 序列名
                                row.createCell(1).setCellValue(line); // 方法序号
                                row.createCell(2).setCellValue(methodName); //  方法名
                                row.createCell(3).setCellValue(chineseDesc); // 方法描述
                                row.createCell(4).setCellValue(i); 			// 参数序号
                                row.createCell(5).setCellValue(p.getType().getSimpleName()); // 参数类型
                                row.createCell(6).setCellValue(p.getName()); // 参数名
                                //辨别数字常量的类型
                                String reqPara = paraMap.get("reqParas").get(i-1);
                                if(NumberUtils.isDigits(reqPara)){
//                                	switch (p.getType().getSimpleName()){
//                                		case "String": reqPara = "@"+reqPara; break;
//                                		case "Long": reqPara = "#"+reqPara; break;
//                                		case "long": reqPara = "#"+reqPara; break;
//                                		case "BigDecimal": reqPara = "$"+reqPara; break;
//                                		default : 
//                                	}
                                	
                                	reqPara = "@"+reqPara;
                                }
                              //【调用核算引擎进行账务处理】  特殊处理
                            	if(methodName.equals("fnDealTbSupTranEvt") && p.getType().getSimpleName().equals("List")){
                            		reqPara = "evtList";
                            	}
                                
                                row.createCell(7).setCellValue(reqPara); // 参数来源
                                row.createCell(8).setCellValue(className); // 接口类名
                                String respParas = paraMap.get("respParas").size()>0?paraMap.get("respParas").toString():"";
                                row.createCell(9).setCellValue(respParas); // 出参
                                
                                System.out.println(sequenceName+"--"+line+"--"+methodName+"--"+chineseDesc+"--"+i+"--"+p.getType().getSimpleName()
                                		+"--"+p.getName()+"--"+paraMap.get("reqParas").get(i-1)+"--"+className+"--"+respParas);
                                i++;
                            }
                            
                        }else{
                            lineNum = line - 1;
                        }
                    }
                }
            }
            if(flag)
            {
                System.out.println("**************************************");
                System.out.println(sequenceName+"缺少的方法："+methodName+ " 行号："+lineNum);
                System.out.println("**************************************");
            }
            out.flush();
            wb.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private static boolean checkMethod(String srcPath, int line, String sequenceName, String methodName, HashMap<String, ArrayList<String>> paraMap) {
    	 boolean flag = true;
    	
    	try {
           
            int lineNum = 0;

//            if("fnPrpTbCalPayPlanPeri".equals(methodName)){
//            	System.out.println("this");
//            }
            
            String filedir = srcPath;
            File file = new File(filedir);
            if (file.isDirectory()) {
                String[] list = file.list();
                list = insert(list, "AtomicUtils.java");
                list = insert(list, "BizUtils.java");
                for (String fileName : list) {
                    if (fileName.equals("impl")) {
                        continue;
                    }
                    String className = fileName.split("\\.")[0];
                    Class clazz;
                    if (className.equals("AtomicUtils") || className.equals("BizUtils")) {
                        clazz = Class.forName("cn.com.git.ncl.aplus.utils." + className);
                    } else {
                        clazz = Class.forName("cn.com.git.ncl.aplus.modules.service." + className);
                    }
                                        
                    Method[] methods = clazz.getMethods();
                    for (Method method : methods) {
                        if (methodName.toLowerCase().equals(method.getName().toLowerCase())) {
                            flag = false;
                            Parameter[] parameters = method.getParameters();
                            
                            if(parameters.length!=paraMap.get("reqParas").size()){
                                System.out.println("**************************************");
                                System.out.println("序列 "+sequenceName+" 中 "+methodName+" 方法参数与实际不一致："+method.getName());
                            	System.out.println("实际方法中的参数："+Arrays.asList(parameters).toString());
                            	System.out.println("序列中的参数："+paraMap.get("reqParas").toString());
                            	System.out.println("**************************************");
                            }
                            
                        }else{
                            lineNum = line - 1;
                        }
                    }
                }
            }
            if(flag)
            {
//                System.out.println("**************************************");
                System.out.println("缺少的方法："+methodName+ "  sequenceName:"+ sequenceName +" 行号："+lineNum);
//                System.out.println("缺少的方法："+methodName);
//                System.out.println("**************************************");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return flag;
    }

    
    private static String[] insert(String[] arr, String str) {
        int size = arr.length;
        String[] tmp = new String[size + 1];
        System.arraycopy(arr, 0, tmp, 0, size);
        tmp[size] = str;
        return tmp;
    }

    
    /**
	 * 中文字符转换 英文字符
	 * @title
	 * @description
	 * 
	 * @param fcode
	 * @return
	 */
	public static String inverseC2E(String fcode){

		fcode = fcode.replace("：", ":");
		fcode = fcode.replace("，", ",");
		fcode = fcode.replace("’", "\"");
		fcode = fcode.replace("‘", "\"");
		fcode = fcode.replace("'", "\"");
		fcode = fcode.replace("“", "\"");
		fcode = fcode.replace("”", "\"");
		fcode = fcode.replace("（", "(");
		fcode = fcode.replace("）", ")");
		
		return fcode;
	}
	
	
	public static String strangStr(String fcode){
		StringBuffer buffer = new StringBuffer();
		if(fcode.contains("{")){
			String[] strs = fcode.split("}");
			for(int i=0;i<strs.length;i++){
				strs[i] = inverseGet(strs[i]).replace(" ", "");	//去空格
				buffer.append(strs[i]+"}");
			}
		}
		return buffer.toString();
	}
	/**
	 * @title	解析出入参数
	 * @description
	 * 
	 * @param methodStr
	 * @return
	 */
    public static HashMap<String,ArrayList<String>> getMethodParas(String methodStr){
    	HashMap<String,ArrayList<String>> paraMap = new HashMap<String,ArrayList<String>>();
    	methodStr = inverseC2E(methodStr);
    	methodStr = methodStr.substring(methodStr.indexOf("(")+1,methodStr.indexOf(")"));
    	String[] paras = methodStr.split(",");
    	ArrayList<String> reqParas = new ArrayList<String>();
    	ArrayList<String> respParas = new ArrayList<String>();
    	for(int i=0;i<paras.length;i++){
    		String para = paras[i].trim().replace(" ", "");
    		if("rpsCod".equals(para) || "rpsCOd".equals(para)|| "rpcCod".equals(para)|| "rpsCode".equals(para) || "".equals(para)){
    			continue;
    		}
    		if(para.startsWith("&")){
    			respParas.add(para.substring(1, para.length()));	//出参
    		}else{
    			if(para.contains("{")){
    				para = strangStr(para);//{} zhuanhuan     				
    			}else{    				
    				para = String.valueOf(Character.toLowerCase(para.charAt(0))) + para.substring(1, para.length()); //变量首字母转为小写
    				para = inverseGet(para);	//设置为set格式
    				if(para.startsWith("\"") && para.endsWith("\"")){	//去掉双引号,并添加@
    					para = "@"+para.substring(1,para.length()-1);
    				}
    				if(para.startsWith("(")) para = para.substring(1,para.length());
    			}
    			reqParas.add(para);    	//入参		
    		}
    	}
    	paraMap.put("reqParas", reqParas);
    	paraMap.put("respParas", respParas);
    	return paraMap;
    }
    
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
		if(fcode.contains(".") && !fcode.contains("get")){
			String[] strs = fcode.split("\\.");
			String vName = strs[0].trim();
			String vMethod = strs[1].trim();
			if(vName.equals("0")){	//0.00 这种情况 不用转义
				return fcode;
			}
			//变量首字母小写
			result.append(String.valueOf(Character.toLowerCase(vName.charAt(0))) + vName.substring(1, vName.length()));
			//get方法
			StringBuffer msb = new StringBuffer();
			if(vMethod.contains("_")){ 		//表字段转为Java字段
				String[] ms = vMethod.split("_");
				for(int i=0;i<ms.length;i++){
					String m = ms[i];
					msb.append(String.valueOf(Character.toUpperCase(m.charAt(0))) + m.substring(1, m.length()));
				}
				vMethod = msb.toString();
			} 
			//属性首字母转为小写
			vMethod = String.valueOf(Character.toLowerCase(vMethod.charAt(0))) + vMethod.substring(1, vMethod.length());
			result.append("."+vMethod);
		}else{
			result.append(fcode);
		}
		return result.toString();
	}
	
    
    public static void main(String[] args) {
    	
    	String importFile = "C:/Users/hull/Desktop/import.xls";	//txt /xls
        String resultFile = "C:/Users/hull/Desktop/export.xls";	//excel
        String srcPath = "D:/workspace/aplus/aplus_atomic/src/main/java/cn/com/git/ncl/aplus/modules/service/";
//        String srcPath = "D:\workspace\aplus\aplus_atomic\src\main\java\cn\com\git\ncl\aplus\modules\service\impl";
        readExcel(srcPath, resultFile, importFile); //解析excel 中的序列配置，生成入表的excel格式
                
	}
}
