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

/**
 * 
 * @description 得到当前应用的系统路径
 * @author Spring Cao
 * @date 2016年8月30日 下午3:23:51
 */
public class SystemPath {

    /**
     * user.dir字符串
     */
    public static final String USER_DIR = "user.dir";
    /**
     * 系统文件路径分隔符
     */
    public static final String FILE_SEPARATOR = "file.separator";
    /**
     * 当前系统的临时目录
     */
    public static final String JAVA_IO_TMPDIR = "java.io.tmpdir";
    /**
     * 当前UDMP主要资源配置文件路径（Spring-*.xml、application.properties、errors_*.properties、mybatis-*.xml、web-fragment.xml）
     */
    public static final String UDMP_SYS_CFG_DIR = "/src/main/resources/META-INF";

    public static String getSysPath() {
        String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
        String temp = path.replaceFirst("file:/", "").replaceFirst("WEB-INF/classes/", "");
        String separator = getSeparator();
        String resultPath = temp.replaceAll("/", separator + separator);
        return resultPath;
    }

    public static String getClassPath() {
        String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
        String temp = path.replaceFirst("file:/", "");
        String separator = getSeparator();
        String resultPath = temp.replaceAll("/", separator + separator);
        return resultPath;
    }

    public static String getSystempPath() {
        return System.getProperty(JAVA_IO_TMPDIR);
    }

    public static String getSeparator() {
        return System.getProperty(FILE_SEPARATOR);
    }

    /**
     * 获取用户工作空间
     * 
     * @return 用户的工作空间
     */
    public static String getUserDir() {
        StringBuffer path = new StringBuffer();
        String workSpace = System.getProperty(USER_DIR);
        if (null != workSpace && workSpace.length() > 0 && workSpace.trim().length() > 0) {
            workSpace = workSpace.trim();
            if (workSpace.contains("\\")) {
                workSpace = workSpace.replace("\\", "/");
                path.append(workSpace);
            }
        }
        return path.toString();
    }
    
    /**
     * 获取UDMP主要资源配置文件路径
     * @return
     */
    public static String getUDMPSysConfigDir(){
        return getUserDir() + UDMP_SYS_CFG_DIR;
    }

    public static void main(String[] args) {
        System.out.println(getSysPath());
        System.out.println(getSystempPath());
        System.out.println(getSeparator());
        System.out.println(getClassPath());
        System.out.println(getUDMPSysConfigDir());
    }
}
