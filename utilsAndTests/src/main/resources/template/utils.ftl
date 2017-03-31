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

package cn.com.git.ncl.aplus.camel.process.${packageName};

import java.util.Map;

import org.springframework.stereotype.Component;
import cn.com.git.ncl.aplus.camel.process.baseprocess.AppAbsProcessor;
import cn.com.git.udmp.common.model.DataObject;

<#list importList as import>
import ${import};
</#list> 


/** 
 * @description 
 * @author admin
 * @date ${Time} 
*/
@Component
public class ${MethodName} extends AppAbsProcessor {

    /**
     * 
     * @title
     * @description
     *
     * @param result
     * @param paramMap
     */
    @Override
    public void appProcess(Map<String, Object> result,Map<String, Object> paramMap) {
        //取出最终参数，调用方法
        <#list paramNameList as param>
		${param.paramType} ${param.paramName} = checkAndGainParam(result, paramMap.get("${param_index+1}"));
		</#list> 
        
        <#if returnName??>${returnType} ${returnName} = </#if> ${ClassName}.${methodName}(<#list paramNameList as param>${param.paramName}<#if param_has_next>,</#if></#list>);
       <#if returnType??>
       
        //封装参数，供下一方法使用
        <#if returnType = "Map"> 
        result.putAll(${returnName});
        <#else> 
        result.put("${returnName}", ${returnName});
		</#if> 
        </#if>
    }

    /**
     * @title
     * @description
     *
     * @param sourceObj 
     */
    @Override
    public <T extends DataObject> void afterProcess(T sourceObj) {
        
    }

}
