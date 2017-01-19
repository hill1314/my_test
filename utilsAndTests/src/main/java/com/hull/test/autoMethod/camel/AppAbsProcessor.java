/**
 * GIT Confidential
 * Licensed Materials - Property of GIT
 * Copyright (c) 1998-2016 Global InfoTech Corp. All Rights Reserved.
 * Global InfoTech, Inc. owns the copyright and other intellectual
 * property rights in this software.
 *
 * The source code for this program is not published.
 * Duplication or use of the Software is not permitted
 * except as expressly provided in a written agreement
 * between your company and Global InfoTech, Inc.
 */

package com.hull.test.autoMethod.camel;


import com.hull.test.autoMethod.cfg.ParamCfgModel;
import com.hull.utils.udmp.AtomicUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** 
 * @description 
 * @author git_man git_man@git.com.cn 
 * @date 2016年8月17日 上午11:09:47  
*/
@SuppressWarnings("all")
public abstract class AppAbsProcessor extends AbsProcessor{
    
    /* public static void main(String[] args) {
        TbSupLoanInfo tbSupLoanInfo = new TbSupLoanInfo();
        tbSupLoanInfo.setRcvDate("20160825");
        Map map = new HashMap();
        map.put("a", "222");
        map.put("tbSupLoanInfo", tbSupLoanInfo);
        
        String t  = checkAndGainParam(map, "tbSupLoanInfo.rcvDate");
        String s = checkAndGainParam(map, "a");
        System.out.println(t);
        System.out.println(s);
        System.out.println("@123".startsWith("@"));
        System.out.println("@123".substring(1));
    }*/
    /**
     * @title校验参数类型调用方法
     * @description
     *
     * @param sourceObj
     * @return 
    */
    @Override
    public final <T extends DataObject> T process(T sourceObj) {
        if(sourceObj instanceof Message){
        	Message message = (Message)sourceObj;
        	//校验格式化参数
            Map<String, Object> result = cast(message.getPayload(), Map.class);
            // execNum--方法执行顺序
            Integer execNum = getParamByKey(result,"execNum");

            System.out.println("序列执行方法信息：【"+execNum+"--"+this.getClass().getSimpleName()+"】");

            //序列方法参数映射
            Map<String, Object> paramMap = getMapByNum(result,execNum);
            
            
            //调用抽象方法--组件调用
            appProcess(result,paramMap);
            
            result.put("execNum", execNum+1);
            message.setPayload(result);
            sourceObj.setData(message.getData());
            return sourceObj;
        }else{
            throw new BizException("99999","序列参数类型错误");
        }
    }

    /**
     * @title
     * @description
     * 
     * @param sourceObj 
    */
    public abstract void appProcess(Map<String, Object> result,Map<String, Object> paramMap);
    
    /**
     * 
     * @title校验类型
     * @description
     * 
     * @param payload
     * @param clazz
     * @return
     */
    protected static <T> T cast(Object payload,Class<T> clazz){
        if(clazz.isAssignableFrom(payload.getClass())){
            return (T) payload;
        }else{
            System.out.println("原子交易参数payload转换异常");
            throw new BizException("99999","序列参数类型错误");
        }
    }
    
    /**
     * 
     * @title普通获取参数方法
     * @description
     * 
     * @param map
     * @param gainParamName
     * @return
     */
    protected static <T> T getParamByKey(Map map,String gainParamName){
        
        if(!map.containsKey(gainParamName)){
            System.out.println("缺少参数"+gainParamName);
            throw new BizException("11019");
        }
        
        
        return (T) map.get(gainParamName);
    }
  
    /**
     * 
     * @title普通获取某个位置序号的参数方法
     * @description
     * 
     * @param map
     * @param execNum
     * @return
     */
    protected static <T> T getMapByNum(Map map,Integer execNum){
        return getParamByKey(getParamByKey(map,"cfgModelMap"),String.valueOf(execNum));
    }
    
    /**
     * 
     * @title获取参数
     * @description
     * 
     * @param map参数集合
     * @param gainParamName需要获取的参数
     * @return
     */
    protected static <T> T checkAndGainParam(Map map,Object prmObj){
    	
    	//类型判断，必须为ParamCfgModel
    	if(!(prmObj instanceof ParamCfgModel)){
    		throw new BizException("11021");
    	}
    	
    	ParamCfgModel paramCfgModel = (ParamCfgModel)prmObj;
        //去空格
        String gainParamName = paramCfgModel.getPmCfCod().trim();
        
        if(gainParamName.startsWith("@")){//常量
        	String ParamTyp = paramCfgModel.getParamTyp().trim();
        	//string 字符串
            if("String".equals(ParamTyp) || "string".equals(ParamTyp)){
            	
            	return (T) gainParamName.substring(1);
            }else if("BigDecimal".equals(ParamTyp) || "bigDecimal".equals(ParamTyp)){//BigDecimal
            
            	return (T) new BigDecimal(gainParamName.substring(1));
            
            }else if("Long".equals(ParamTyp) || "long".equals(ParamTyp)){//Long
            
            	return (T) new Long(gainParamName.substring(1));
            }else{
            	System.out.println("参数类型配置异常"+ParamTyp);
                throw new BizException("12044");
            }
            
        }else if(gainParamName.contains("{")){
            //解析 ：{2:102:prnStruInfo.res_all_prn_bal},{6:209:stgAllStruItr.out_nor_bal_09+ StopItrStruAllStg.otd_out_nor_itr },{7:210:stgAllStruItr.out_dft_bal_10},{8:211: stgAllStruItr.out_pns_bal_11+ StopItrStruAllStg.otd_out_pns_itr }
            
            String[] strObjects = gainParamName.substring(1,gainParamName.length()-1).split("\\}\\{");
            for(String strObject:strObjects)
            {
                String[] strs = strObject.split("\\:");
                gainParamName = gainParamName.replace(strs[2], splitExpression(strs[2],map)+"");
            }
            return (T) gainParamName;
        }else if(gainParamName.contains("+")||gainParamName.contains("-")){//解析加减表达式
            
            return (T) splitExpression(gainParamName,map);
        }else if(gainParamName.indexOf(".")<0){
            
            if(!map.containsKey(gainParamName)){
                System.out.println("缺少参数"+gainParamName);
                throw new BizException("11019");
            }
            return (T) map.get(gainParamName);
        }else{
            String[] objField = gainParamName.split("\\.");
            if(!map.containsKey(objField[0])){
                System.out.println("缺少参数"+gainParamName);
                throw new BizException("11019");
            }
            Object obj = map.get(objField[0]);
            Class clazz = obj.getClass();
            
            try {
                Field field = clazz.getDeclaredField(objField[1]);
                field.setAccessible(true);
                if(null == field.get(obj)){
                	return null;
                }
                return (T) field.get(obj);
            } catch (Exception e) {
                System.out.println("参数获取异常"+gainParamName);
                throw new BizException("11021");
            }
        }
    }
    
    public static BigDecimal splitExpression(String s,Map<String,Object> map) {
        boolean flag = false;
        List<BigDecimal> fileds = new ArrayList<>();
        if (s == null) {
            return null;
        }
        
        StringBuilder sb = new StringBuilder(s.length());
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '+') {
                if(flag)
                {
                    fileds.add(new BigDecimal("-1").multiply(getFiled(sb.toString(),map)));
                }else
                {
                    fileds.add(getFiled(sb.toString(),map));
                }
                flag = false;
                sb = new StringBuilder();
            }else if(c == '-')
            {
                if(flag)
                {
                    fileds.add(new BigDecimal("-1").multiply(getFiled(sb.toString(),map)));
                }else
                {
                    if(!"".equals(sb.toString()))
                    {
                        fileds.add(getFiled(sb.toString(),map));
                    }
                }
                flag = true;
                sb = new StringBuilder();
            }else
            {
                sb.append(c);
            }
        }
        if(flag)
        {
            fileds.add(new BigDecimal("-1").multiply(getFiled(sb.toString(),map)));
        }else
        {
            fileds.add(getFiled(sb.toString(),map));
        }
        BigDecimal[] filedsArray = new BigDecimal[fileds.size()];
        
        return AtomicUtils.addAll(fileds.toArray(filedsArray));
    }
    
    public static <T> T getFiled(String gainParamName,Map<String,Object> map)
    {
        if(gainParamName.indexOf(".")<0){
            
            if(!map.containsKey(gainParamName)){
                System.out.println("缺少参数"+gainParamName);
                throw new BizException("11019");
            }
            return (T) map.get(gainParamName);
        }else
        {
            String[] objField = gainParamName.split("\\.");
            if(!map.containsKey(objField[0])){
                throw new BizException("11019");
            }
            Object obj = map.get(objField[0]);
            Class clazz = obj.getClass();
            
            try {
                Field field = clazz.getDeclaredField(objField[1]);
                field.setAccessible(true);
                return (T)field.get(obj);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BizException("11021");
            }
        }
    }
    

}
