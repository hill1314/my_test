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

import java.math.BigDecimal;

/** 
 * @description 常量类
 * @author git_man git_man@git.com.cn 
 * @date 2016年8月11日 下午3:59:21  
*/
public class AtomicConstants {
    
    /**
     * 世界货币代码对应表
     */
    public final static String RIYUAN_CUR = "27";//日元
    
    /**
     * 返回成功码  
     */
    public final static String SECCESS_CODE = "00000";
    
    /**
     * 日终渠道
     */
    public final static String ORIG_FROM_EOD = "99999";
    
    //日终批处理 TC_SUP_PRM_CFG_INFO_EOD
    public final static String TC_SUP_PRM_CFG_INFO_EOD = "TC_SUP_PRM_CFG_INFO_EOD";
    
    //日终批处理 TC_SUP_PRM_CFG_INFO_EOD
    public final static String TC_SUP_PRM_CFG_INFO_TRAN = "TC_SUP_PRM_CFG_INFO_TRAN";
    
    /**
     * 常量数0.005
     */
    public final static BigDecimal BIGDEC_FIVE = new BigDecimal("0.005");
    
    /**
     * 常量数-0.005
     */
    public final static BigDecimal BIGDEC_MINUS_FIVE = new BigDecimal("-0.005");
    
    /**
     * 常量数0.00
     */
    public final static BigDecimal BIGDEC_ZERO = new BigDecimal("0.00");
    
    

}
