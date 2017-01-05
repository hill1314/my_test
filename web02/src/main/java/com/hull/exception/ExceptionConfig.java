package com.hull.exception;


import com.hull.constant.UtilConstant;
import com.hull.constant.UtilErrorMessage;
import com.hull.utils.PropertiesUtil;

import java.text.MessageFormat;

public class ExceptionConfig {

    private static String ERROR_MESSAGE = "message";

    /**
     * 默认模块
     */
    private static int DEFAULT_MODEL = 0;

    /**
     * 系统模块列表
     */
    private static String SYS_MODELlIST = "sys.modellist";

    /**
     * 错误信息未找到
     */
    public static String NotFoundErrorMessage = "NotFoundErrorMessage";

    public static String[] modelList = null;

    static {
        String modelListStr = PropertiesUtil.readString(UtilConstant.SYS_CONFIG_FILE, SYS_MODELlIST);
        modelList = modelListStr.split(",");
    }

    /**
     * 根据模块编码获取模块名称
     *
     * @param modelCode
     * @return
     */
    public static String getModelName(int modelCode) {
        return modelList[modelCode];
    }

    /**
     * 使用默认消息配置文件格式化错误信息
     *
     * @param code 错误代码
     * @param args 错误信息参数
     * @return 格式化替换后的错误信息描述
     */
    public static String mm(String code, String... args) {
        return mm(DEFAULT_MODEL, code, args);
    }

    /**
     * 格式化错误信息
     *
     * @param modelCode 错误信息模块
     * @param code      错误代码
     * @param args      错误信息参数
     * @return 格式化替换后的错误信息描述
     */
    public static String mm(int modelCode, String code, String... args) {
        String message = null;
        String modelName = modelList[modelCode];
        if (DEFAULT_MODEL == modelCode) {
            message = PropertiesUtil.readString(ERROR_MESSAGE, code, true);
        } else {
            message = PropertiesUtil.readString(modelName + "-" + ERROR_MESSAGE, code, true);
        }
        if (message == null) {
            return MessageFormat.format(UtilErrorMessage.NOT_FOUND_MSG, modelCode);
        }
        if (args != null) {
            message = MessageFormat.format(message, args);
        }
        // 返回异常处理结果
        return message;
    }

    /**
     *  根据消息模板格式化输出日志
     * @param message 错误信息,{d}进行变量占位
     * @param args 变量参数
     * @return  输出替换后的结果
     */
    public static String formartMessage(String message, Object... args) {
        return MessageFormat.format(message, args);
    }
}
