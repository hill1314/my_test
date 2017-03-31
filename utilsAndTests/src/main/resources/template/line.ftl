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

package cn.com.git.ncl.aplus.camel.routes;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

<#list importList as ClassInfo>
import cn.com.git.ncl.aplus.camel.process.${ClassInfo.packageName}.${ClassInfo.ClassName};
</#list>

/** 
 * @description 
 * @author zoulq
 * @date ${Time}
*/
@Component
public class ${lineName} extends SpringRouteBuilder{

	<#list beanList as bean>
	@Autowired
    ${bean.ClassN} ${bean.classN};
    
    </#list>
    /**
     * @title
     * @description
     *
     * @see org.apache.camel.builder.RouteBuilder#configure()
     * @throws Exception 
    */
    @Override
    public void configure() throws Exception {
        
        from("direct:${lineName}")
        <#list methodList as method>
		.bean(${method.methodName},"doProcess")//${method.methodRmk}
		</#list>
        .end();
        
    }
}
