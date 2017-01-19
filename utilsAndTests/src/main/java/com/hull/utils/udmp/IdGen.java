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

import java.security.SecureRandom;
import java.util.UUID;

/**
 * Id生成工具
 * @description 封装各种生成唯一性ID算法的工具类.
 * @author Spring Cao
 * @date 2016年8月26日 上午11:31:30
 */
public class IdGen{

    /** 安全随机数生成器 */
	private static SecureRandom random = new SecureRandom();
	
	/**
	 * 
	 * @title UUID生成
	 * @description 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 * 
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 
	 * @title 生成Long型ID
	 * @description 使用SecureRandom随机生成Long. 
	 * 
	 * @return
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}
	
	/**
	 * 
	 * @title 基于Base62编码生成随机String
	 * @description 基于Base62编码的SecureRandom随机生成bytes.
	 * 
	 * @param length
	 * @return
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return Encodes.encodeBase62(randomBytes);
	}
	
	public static void main(String[] args) {
		System.out.println(IdGen.uuid());
		System.out.println(IdGen.uuid().length());
		for (int i=0; i<1000; i++){
			System.out.println(IdGen.randomLong() + "  " + IdGen.randomBase62(5));
		}
	}

}
