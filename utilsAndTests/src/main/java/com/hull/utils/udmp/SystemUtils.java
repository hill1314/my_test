package com.hull.utils.udmp;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统工具——继承Apache Commons
 * 
 * @author Spring.Cao
 * @version v1.0 2014-5-15
 * 
 */
public class SystemUtils extends org.apache.commons.lang3.SystemUtils {

    protected static final Logger logger = LoggerFactory.getLogger(SystemUtils.class);

    @SuppressWarnings("rawtypes")
    private static Map environment;

    private static String STR_BLANK = "";

    private static final String[] UNIX_ENV_PREFIXES = new String[] {"declare -", "typeset -" };

    /**
     * @description 获取系统环境
     * @version
     * @title
     * @author tanzhiliang
     * @return 系统环境信息
    */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static synchronized Map getenv() {
        if (environment == null) {
            try {
                if (SystemUtils.IS_JAVA_1_4 || SystemUtils.IS_JAVA_1_5) {
                    environment = Collections.unmodifiableMap(getenvJDK14());
                } else {
                    Class target = System.class;
                    Method envMethod = target.getMethod("getenv", ArrayUtils.EMPTY_CLASS_ARRAY);
                    environment = Collections.unmodifiableMap((Map) envMethod.invoke(target, (Object[]) null));
                }
            } catch (Exception ex) {
                logger.error("Could not access OS environment: ", ex);
                environment = Collections.EMPTY_MAP;
            }
        }

        return environment;
    }

    /**
     * @description 获取系统环境
     * @version
     * @title
     * @author tanzhiliang
     * @param name 变量名
     * @return 系统环境
    */
    public static String getenv(String name) {
    	Object value = SystemUtils.getenv().get(name);
    	if(value != null) return (String)value;
    	else return "";
    }

    public static boolean isSunJDK() {
        return SystemUtils.JAVA_VM_VENDOR.toUpperCase().indexOf("SUN") != -1;
    }

    public static boolean isAppleJDK() {
        return SystemUtils.JAVA_VM_VENDOR.toUpperCase().indexOf("APPLE") != -1;
    }

    public static boolean isIbmJDK() {
        return SystemUtils.JAVA_VM_VENDOR.toUpperCase().indexOf("IBM") != -1;
    }

    /**
     * @description 获取环境变量
     * @version
     * @title
     * @author tanzhiliang
     * @return 环境变量
     * @throws Exception 获取环境变量异常
    */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Map getenvJDK14() throws Exception {
        Map env = new HashMap();
        Process process = null;

        try {
            boolean isUnix = true;
            String command;

            if (SystemUtils.IS_OS_WINDOWS) {
                command = "cmd /c set";
                isUnix = false;
            } else {
                command = "env";
            }

            process = Runtime.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = br.readLine()) != null) {
                for (int prefix = 0; prefix < UNIX_ENV_PREFIXES.length; prefix++) {
                    if (line.startsWith(UNIX_ENV_PREFIXES[prefix])) {
                        line = line.substring(UNIX_ENV_PREFIXES[prefix].length());
                    }
                }

                int index = -1;
                if ((index = line.indexOf('=')) > -1) {
                    String key = line.substring(0, index).trim();
                    String value = line.substring(index + 1).trim();
                    // remove quotes, if any
                    if (isUnix && value.length() > 1 && (value.startsWith("\"") || value.startsWith("'"))) {
                        value = value.substring(1, value.length() - 1);
                    }
                    env.put(key, value);
                } else {
                    env.put(line, STR_BLANK);
                }
            }
        } catch (Exception e) {
        	logger.error(e.getMessage());
        	e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }

        return env;
    }
}
