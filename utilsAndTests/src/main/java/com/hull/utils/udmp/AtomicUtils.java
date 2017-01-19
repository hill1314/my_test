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

package com.hull.utils.udmp;

import com.hull.test.autoMethod.camel.AtomicConstants;
import com.hull.test.autoMethod.camel.BizException;

import java.math.BigDecimal;
import java.text.ParseException;

/**
 * @description
 * @author git_man git_man@git.com.cn
 * @date 2016年8月11日 上午9:18:36
 */
public class AtomicUtils {

	/**
	 * 
	 * @title 比较二个数据是否相等
	 * @description 比较二个数据是否相等
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static boolean fnDiffTwoNum(Long num1, Long num2) {
		if (num1.longValue() == num2.longValue()) {
			throw new BizException("99999");
		}
		return true;
	}

	/**
	 * 
	 * @title 检查字符串是否为0或者1
	 * @description 检查字符串是否为0或者1
	 * 
	 * @param sObject
	 * @return
	 */
	public static boolean fnChkZeroOrOne(String sObject, String dec) {
		if (!"0".equals(sObject) && !"1".equals(sObject)) {
			throw new BizException("10010", dec + "必须为0或1");
		}
		return true;
	}

	/**
	 * 
	 * @title
	 * @description 检查字符串是否为1
	 * 
	 * @param sObject
	 * @param des
	 */
	public static void fnChkStringFlag(String sObject, String des) {
		if ("1".equals(sObject)) {
			throw new BizException("10010", des + "必须为0");
		}
	}

	/**
	 * 
	 * @title 检查字符串是否为0或者2
	 * @description 检查字符串是否为0或者2
	 * 
	 * @param sObject
	 * @param des
	 * @return
	 */
	public static boolean fnChkZeroOrTwo(String sObject, String des) {
		if (!"0".equals(sObject) && !"2".equals(sObject)) {
			throw new BizException("10010", des + "必须为0,2");
		}
		return true;
	}

	/**
	 * 
	 * @title 检查借据类型是否为1,2,3
	 * @description 检查借据类型是否为1,2,3
	 * 
	 * @param sDueType
	 * @return
	 */
	public static boolean fnChkDueType(String sDueType, String dec) {
		if (!"1".equals(sDueType) && !"2".equals(sDueType)
				&& !"3".equals(sDueType)) {
			throw new BizException("10010", dec + "必须为1,2,3");
		}
		return true;
	}

	/**
	 * 
	 * @title 检查借据类型是否为0,1,2
	 * @description 检查是否为0,1,2
	 * 
	 * @param sType
	 * @param dec
	 *            错误描述
	 * @return
	 */
	public static boolean fnChkZeroOneTwo(String sType, String dec) {
		if (!"0".equals(sType) && !"1".equals(sType) && !"2".equals(sType)) {
			throw new BizException("10010", dec + "必须为0,1,2");
		}
		return true;
	}
	
	/**
	 * 
	 * @title  判断 一个值是否在 某个范围内
	 * @description
	 * 
	 * @param target	要判断的值
	 * @param values   值域(逗号连接)
	 * @param errorDec 错误描述 
	 * @return
	 */
	public static boolean fnChkTargetIfInValues(String target,String values, String errorDec) {
		boolean flag = false;
		String[] list = values.split(",");
		for(String val : list){
			if(target.equals(val)){				
				flag = true;
				break;
			}
		};
		if (!flag) {
			throw new BizException("10010", errorDec);
		}
		return flag;
	}

	/**
	 * 
	 * @title 检查互斥性公共函数
	 * @description 检查参数（如节假日和宽限期）互斥性
	 * 
	 * @param graPerdFlg
	 * @param hldFlg
	 * @return
	 */
	public static boolean fnChkMutex(String graPerdFlg, String hldFlg) {
		int i;
		int j;
		if (graPerdFlg.length() == 1) {
			i = Integer.parseInt(graPerdFlg);
		} else {
			i = Integer.parseInt(graPerdFlg.substring(0, 1))
					+ Integer.parseInt(graPerdFlg.substring(1, 2));
		}
		if (hldFlg.length() == 1) {
			j = Integer.parseInt(hldFlg);
		} else {
			j = Integer.parseInt(hldFlg.substring(0, 1))
					+ Integer.parseInt(hldFlg.substring(1, 2));
		}

		if (i >= 1 && j >= 1) {
			throw new BizException("10011");
		}
		return true;
	}

	/**
	 * 
	 * @title 检查（节假日/宽限期）本金利息处理方式
	 * @description 检查（节假日/宽限期）本金利息处理方式
	 * 
	 * @param prnItrWay
	 * @return
	 */
	public static boolean fnChkPrnItrWay(String prnItrWay) {
		if (!"00".equals(prnItrWay) && !"01".equals(prnItrWay)
				&& !"10".equals(prnItrWay) && !"11".equals(prnItrWay)) {
			throw new BizException("10013");
		}
		return true;
	}

	/**
	 * 
	 * @title 比较二个字符串是否相同
	 * @description 判断二个字符串是否相同
	 * 
	 * @param objA
	 * @param objB
	 * @return
	 */
	public static boolean fnChkDiffObjAObjB(String objA, String objB) {
		if (!objA.equals(objB)) {
			throw new BizException("11005");
		}
		return true;
	}

	/**
	 * 
	 * @title 循环按位检查标志是否为0或1
	 * @description 检查状态(0,1)
	 * 
	 * @param cycleFlag
	 * @return
	 */
	public static boolean fnChkCycleZeroOrOne(String cycleFlag, String des) {
		for (int i = 1; i <= cycleFlag.length(); i++) {
			if (!"0".equals(cycleFlag.substring(i - 1, i))
					&& !"1".equals(cycleFlag.substring(i - 1, i))) {
				throw new BizException("10010", des);
			}
		}
		return true;
	}

	/**
	 * 
	 * @title 检查宽限期天数
	 * @description 检查宽限期天数
	 * 
	 * @param gracePrdTyp
	 * @param payDate
	 * @param gracePrnDays
	 * @param gracePrnDaysLimit
	 * @return
	 */
	public static boolean fnChkGraceDays(String gracePrdTyp, String payDate,
			int gracePrnDays, int gracePrnDaysLimit) {
		int days = 0;
		// 如果宽限期方式为按固定天数
		if ("1".equals(gracePrdTyp)) {
			if (gracePrnDays > gracePrnDaysLimit) {
				throw new BizException("10012");
			}
			// 宽限至月底
		} else if ("2".equals(gracePrdTyp)) {
			// 计算月底与指定还款日之间的天数
			days = 28 - Integer.parseInt(payDate);// 指定还款日转整数型
			if (days > gracePrnDaysLimit) {
				throw new BizException("10012");
			}
		}
		return true;
	}

	/**
	 * 
	 * @title 检查宽限期天数是否达到上限
	 * @description 检查宽限期天数是否达到上限
	 * 
	 * @param gracePrdTyp
	 * @param graceDays
	 * @param limitDays
	 * @return
	 */
	public static boolean fnChkGraceHldDays(String gracePrdTyp, Long graceDays,
			Long limitDays) {
		if ("1".equals(gracePrdTyp)) {
			if (graceDays > limitDays) {
				throw new BizException("10012");
			}
		}
		return true;
	}

	/**
	 * 
	 * @title内部加法
	 * @description
	 * 
	 * @param bs
	 * @return
	 */
	public static BigDecimal addAll(BigDecimal... bs) {
		BigDecimal bg = new BigDecimal("0");
		for (BigDecimal bigDecimal : bs) {
			if (null != bigDecimal) {
				bg = bg.add(bigDecimal);
			}
		}
		return bg;
	}

	/**
	 * 
	 * @title 比较两个金额的大小
	 * @description 比较两个金额的大小
	 * 
	 * @param num1
	 * @param num2
	 * @param compFlag
	 *            传入符号
	 * @return
	 */
	public static Boolean fnCompareTwoDec(BigDecimal num1, BigDecimal num2,
			String compFlag) {
		if ("=".equals(compFlag)) {
			if (num1.subtract(num2).abs().compareTo(AtomicConstants.BIGDEC_FIVE) < 0) {
				return true;
			} else {
				throw new BizException("10018");
			}
		} else if (">".equals(compFlag)) {
			if (num1.subtract(num2).compareTo(AtomicConstants.BIGDEC_FIVE) > 0) {
				return true;
			} else {
				throw new BizException("10018");
			}
		} else if ("<".equals(compFlag)) {
			if (num1.subtract(num2).compareTo(AtomicConstants.BIGDEC_FIVE) < 0) {
				return true;
			} else {
				throw new BizException("10018");
			}
		} else if (">=".equals(compFlag)) {
			if (num1.subtract(num2).compareTo(AtomicConstants.BIGDEC_MINUS_FIVE) > 0) {
				return true;
			} else {
				throw new BizException("10018");
			}
		} else if ("<=".equals(compFlag)) {
			if (num1.subtract(num2).compareTo(AtomicConstants.BIGDEC_FIVE) < 0 || num1.subtract(num2).abs().compareTo(AtomicConstants.BIGDEC_FIVE) < 0) {
				return true;
			} else {
				throw new BizException("10018");
			}
		} else {
			throw new BizException("10010");
		}
	}

	/**
	 * 
	 * @title 根据传入标识符比较两个日期大小
	 * @description 比较两个日期大小
	 * 
	 * @param date1
	 * @param date2
	 * @param compFlag
	 *            传入符号
	 * @return
	 */
	public static Boolean fnCompareTwoDay(String date1, String date2,
			String compFlag) {
		if ("=".equals(compFlag)) {
			if (date1.equals(date2)) {
				return true;
			} else {
				throw new BizException("10008");
			}
		} else if (">".equals(compFlag)) {
			if (date1.compareTo(date2) > 0) {
				return true;
			} else {
				throw new BizException("10008");
			}
		} else if ("<".equals(compFlag)) {
			if (date1.compareTo(date2) < 0) {
				return true;
			} else {
				throw new BizException("10008");
			}
		} else if (">=".equals(compFlag)) {
			if (date1.compareTo(date2) >= 0) {
				return true;
			} else {
				throw new BizException("10008");
			}
		} else if ("<=".equals(compFlag)) {
			if (date1.compareTo(date2) <= 0) {
				return true;
			} else {
				throw new BizException("10008");
			}
		} else {
			throw new BizException("10010");
		}
	}

	/**
	 * 
	 * @title 检查阶段性首次还本期次正确性
	 * @description
	 * 
	 * @param stgFirstMon
	 * @param numBasePeri
	 */
	public static void fnCheckStgFirstMon(Long stgFirstMon, Long numBasePeri) {
		// 阶段性首次还本期数不能为空
		if (null == stgFirstMon) {
			throw new BizException("10009", "阶段性首次还本期数不能为空");
		}
		// 总期次不能为空
		if (null == numBasePeri) {
			throw new BizException("10009", "总期次不能为空");
		}

		if (stgFirstMon < 1) {
			throw new BizException("10089");
		}
		if (stgFirstMon > numBasePeri - 2) {
			throw new BizException("10089");
		}
	}

	/**
	 * 
	 * @title 根据传入标识符比较两个数大小
	 * @description 比较两个数大小
	 * 
	 * @param num1
	 * @param num2
	 * @param compFlag
	 *            传入符号
	 * @return
	 */
	public static Boolean fnCompareTwoNum(Long num1, Long num2,
			String compFlag) {
		if ("=".equals(compFlag)) {
			if (num1.longValue() == num2.longValue()) {
				return true;
			} else {
				throw new BizException("10102");
			}
		} else if (">".equals(compFlag)) {
			if (num1.longValue() > num2.longValue()) {
				return true;
			} else {
				throw new BizException("10102");
			}
		} else if ("<".equals(compFlag)) {
			if (num1.longValue() < num2.longValue()) {
				return true;
			} else {
				throw new BizException("10102");
			}
		} else if (">=".equals(compFlag)) {
			if (num1.longValue() >= num2.longValue()) {
				return true;
			} else {
				throw new BizException("10102");
			}
		} else if ("<=".equals(compFlag)) {
			if (num1.longValue() <= num2.longValue()) {
				return true;
			} else {
				throw new BizException("10102");
			}
		} else {
			throw new BizException("10010");
		}
	}

	/**
	 * 
	 * @title 判断字符是否为空
	 * @description
	 * 
	 * @param obj
	 */
	public static void fnDifFiledEmpty(String obj) {
		if (StringUtils.isBlank(obj)) {
			throw new BizException("10009");
		}
	}
	
	public static double getDistanceOfTwoDate(String startDate,String endDate)
	{
	    double iDays = 0;
	    // 调用原子交易计算两日期间的间隔天数 (按一年365天数)
	    try {
	        iDays = DateUtils.getDistanceOfTwoDate(DateUtils.parseDate(startDate, "yyyyMMdd"),
	                DateUtils.parseDate(endDate, "yyyyMMdd"));
	    } catch (ParseException e) {
	        throw new BizException("11023");
	    }
	    
	    return iDays;
	}
	
	/**
     * 
     * @title 验证收取整期利息与是否归还未结计利息标志互斥
     * @description 验证收取整期利息与是否归还未结计利息标志互斥
     * @param entireFlg//是否收取整期利息标志
     * @param payOutItrFlg//是否收取未结计利息标志
     * @return 
     */    	
    public static void fnChkPeriodItrPayOutItrFlg(String entireFlg,String payOutItrFlg){
    	
    	//收取整期利息,一定要收取未结计利息
    	if("1".equals(entireFlg) && "0".equals(payOutItrFlg)){
    		// 输出错误日志，异常退出
    		throw new BizException("10009");
    	}
    	
    }

}
