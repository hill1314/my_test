package com.hull.constant;

/**
 * <p/>
 * 错误代码
 * <p/>
 *
 * @author zhangsf 创建 2012-5-10
 * @version 1.0 Copyright(c) 北京神州数码思特奇信息技术股份有限公司
 */
public interface UtilErrorMessage {
	
	/**
	 * {0}对象调用错误
	 */
	public static final String SYS_CALL_ERROR ="110002";

    /**
     * 读取的{0}文件不存在
     */
    public static final String FILE_NOT_FOUND_ERROR = "120003";

    /**
     * {0}文件读取失败
     */
    public static final String FILE_READ_ERROR = "120004";

    /**
     * FTP配置信息{}初始化化失败
     */
    public static final String FTP_ERROR_120001 = "120001";


    /**
     * FTP主机{0}中读取{1}目录下文件列表失败
     */
    public static final String FTP_ERROR_120005 = "120005";

    /**
     * FTP主机{0}中密码解密失败
     */
    public static final String FTP_ERROR_120006 = "120006";

    /**
     * 120007=FTP主机{0}连接失败
     */
    public static final String FTP_ERROR_120007 = "120007";

    /**
     * 120008=FTP主机{0}切换目录{1}失败
     */
    public static final String FTP_ERROR_120008 = "120008";

    /**
     * 120009=FTP主机{0}上传文件失败
     */
    public static final String FTP_ERROR_120009 = "120009";

    /**
     * 120010=FTP主机{0}文件{1}删除失败
     */
    public static final String FTP_ERROR_120010 = "120010";

    /**
     * 120011=FTP主机{0}文件{1}重名名失败
     */
    public static final String FTP_ERROR_120011 = "120011";

    /**
     * Excel的Sheet页创建失败
     */
    public static final String EXCEL_SHEET_CREAT_ERROR = "121000";

    /**
     * Excel的Sheet编号超出边界
     */
    public static final String EXCEL_SHEET_BOUND_ERROR = "121001";

    /**
     * Excel行超出错误
     */
    public static final String EXCEL_ROW_EXCEEDED_ERROR = "121002";

    /**
     * Excel行超出错误
     */
    public static final String EXCEL_WRITE_ERROR = "121003";

    /**
     * {0}报文转换错误
     */
    public static final String ERR_CODE_110009 = "110009";

    /**
     * {0}不是有效的{1}编码
     */
    public static final String ERR_CODE_110010 = "110010";

    /**
     * 创建{0}文件错误
     */
    public static final String FILE_CREATE_ERROR = "120002";

    /**
     * 字符不是有效的【{0}】日期格式
     */
    public static final String DATE_FORMAT_ERROR = "200012";

    /**
     * 类型转换错误：值[{0}]无法从{1}类型转换为{0}类型！
     */
    public static final String DATA_FORMAT_200013 = "200013";

    /**
     * 替换{0}语句中{1}参数不能为空！
     */
    public static final String ERR_CODE_200033 = "200033";

    /**
     * {0}对应的{1}不存在！
     */
    public static final String ERR_CODE_200026 = "200026";

    /**
     * 123101=作业{0}启动失败,原因:{1}！
     */
    public static final String ERR_CODE_123101 = "123101";

    /**
     * 123102=作业{0}停止失败,原因:{1}！
     */
    public static final String ERR_CODE_123102 = "123102";

    /**
     * 123103=作业{0}不存在！
     */
    public static final String ERR_CODE_123103 = "123103";

    /**
     *  123104=作业{0}启动失败,原因:{1}！
     */
    public static final String ERR_CODE_123104 = "123104";

    /**
     *  123105=作业{0}停止失败,原因:{1}！
     */
    public static final String ERR_CODE_123105 = "123105";

    /**
     *  123106=不支撑的启动类型参数{0}!
     */
    public static final String ERR_CODE_123106 = "123106";

    /**
     *  123107=作业初始化化失败{0}!
     */
    public static final String ERR_CODE_123107 = "123107";


    /**
     * 暂时不支持{0}报表格式！
     */
    public static final String ERR_CODE_122001 = "122001";

    /**
     * 报表{0}生成失败
     */
    public static final String REPORT_GENERATE_ERROR = "121006";

    /**
     * 121007=报表模板{0}编译失败
     */
    public static final String REPORT_COMPILE_ERROR = "121007";

    /**
     * 缓存配置文件{0}加载失败！
     */
    public static final String CACHE_ERROR_123000 = "123000";

    /**
     * 缓存项目{0}未在[xfgcache.xml]配置！
     */
    public static final String CACHE_ERROR_123001 = "123001";

    /**
     * 缓存引擎{0}初始化失败，对应实现类{1}无法创建！
     */
    public static final String CACHE_ERROR_123003 = "123003";

    /**
     * 缓存引擎{0}初始化失败，对应实现类{1}不存在！
     */
    public static final String CACHE_ERROR_123004 = "123004";

    /**
     * 缓存项目{0}对应的缓存容器{1}未配置！
     */
    public static final String CACHE_ERROR_123005 = "123005";

    /**
     * 从{0}缓存引擎中读取缓存项目{1}失败！
     */
    public static final String CACHE_ERROR_123006 = "123006";

    /**
     * 200036=规则{0}没有在配置文件{1}中定义!
     */
    public static final String RULE_STATUS_200036 = "200036";

    /**
     * 200037=规则引擎出事化失败，{0}配置文件读取失败!
     */
    public static final String RULE_STATUS_200037 = "200037";

    /**
     * 200038=从{0}读取规则{0}失败!
     */
    public static final String RULE_STATUS_200038 = "200038";

    /**
     * 200039=规则{0}状态不可用!
     */
    public static final String RULE_STATUS_200039 = "200039";

    /**
     * 200040=规则{0}执行异常!
     */
    public static final String RULE_STATUS_200040 = "200040";

    /**
     * 200041=规则{0}创建实例对象失败!
     */
    public static final String RULE_STATUS_200041 = "200041";


    /**
     * 200042=从数据库中读取规则{0}-{1}失败，请检查规则数据库!
     */
    public static final String RULE_STATUS_200042 = "200042";

    /**
     *  300001=数据库连接获取失败
     */
    public static final String ERROR_MESSAGE_300001 = "300001";

    /**
     *  300002=执行数据库{0}语句{1}错误，执行参数{2}
     */
    public static final String ERROR_MESSAGE_300002 = "300002";
    
    /**
     * 130000=redis服务器地址为空!
     */
    public static final String REDIS_130000 = "130000";
    
    
    public static final String ObjTree_LIST_OUT_BOUNDS = "130001";
    
    public static final String ObjTree_INNER_ILLEGAL = "130002";
    
    
    /**
     * 未找到此错误代码编号与信息,请到【message.properties】中进行配置
     */
    public static final String NOT_FOUND_MSG = "未找到此错误代码编号与信息,请到【{0}-message.properties】中进行配置";


}
