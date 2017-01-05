package com.hull.utils;


import com.hull.constant.UtilConstant;
import com.hull.constant.UtilErrorMessage;
import com.hull.exception.SystemException;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期格式转换类
 *
 * @author linql 创建 2012-2-21
 * @author sunhao 添加dateCompare比较日期 & 获取数据库时间
 * @version 1.0 Copyright(c) 北京思特奇信息技术股份有限公司
 */
public final class DateUtil {

    /**
     * 日志输出
     */
    private static final Log log = LogFactory.getLog(DateUtil.class);

    /**
     * 主机时间与数据库时间的差值
     */
    private static Long hostDbTimeMinus = null;

    // 构造类时候直接初始化系统时间
    static {
        hostDbTimeMinus = new Long(0);
//        refreshSysDate();
    }


    /**
     * 年
     */
    public static int YEAR = 1;

    /**
     * 月
     */
    public static int MONTH = 2;

    /**
     * 周
     */
    public static int WEEK = 3;

    /**
     * 日
     */
    public static int DAY = 5;

    /**
     * 小时
     */
    public static int HOUR = 10;

    /**
     * 分钟
     */
    public final static int MINUTE = 12;

    /**
     * 秒
     */
    public final static int SECOND = 13;

    /**
     * 毫秒
     */
    public final static int MILLISECOND = 14;

    /**
     * 创建一个新的实例 DateUtil.
     */
    private DateUtil() {

    }

    /**
     * 将Date型转换为Timestamp类型
     *
     * @param date 日期
     * @return 时间撮
     */
    public static java.sql.Timestamp convertToTimestamp(Date date) {
        if (date == null) {
            return null;
        } else {
            return new java.sql.Timestamp(date.getTime());
        }
    }

    /**
     * 将日期对象转换为yyyy-MM-dd HH:mm:ss格式字符串
     *
     * @param date 时间对象
     * @return yyyy-MM-dd HH:mm:ss格式字符串
     */
    public static String toStringYmdHmsWthH(Date date) {
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
    }

    /**
     * 将日期对象转换为yyyy-MM-dd HH:mm:ss SSS格式字符串
     *
     * @param date 时间对象
     * @return yyyy-MM-dd HH:mm:ss SSS格式字符串
     */
    public static String toStringYmdHmsWthHS(Date date) {
        return (new SimpleDateFormat("yyyyMMddHHmmssSSS")).format(date);
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式字符串转换为日期对象
     *
     * @param dateStr yyyy-MM-dd HH:mm:ss格式字符串
     * @return 日期对象
     */
    public static Date toDateYmdHmsWthH(String dateStr) {
        try {
            return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(dateStr);
        } catch (ParseException e) {
            throw new SystemException(UtilConstant.MODEL_CODE_UTIL, e,
                    UtilErrorMessage.DATE_FORMAT_ERROR, dateStr, "yyyy-MM-dd HH:mm:ss");
        }
    }

    /**
     * 将日期对象转换为yyyy-MM-dd格式字符串
     *
     * @param date 时间对象
     * @return yyyy-MM-dd格式字符串
     */
    public static String toStringYmdWthH(Date date) {
        return (new SimpleDateFormat("yyyy-MM-dd")).format(date);
    }

    /**
     * 将日期对象转换为yyyy/MM/dd格式字符串
     *
     * @param date 时间对象
     * @return yyyy/MM/dd格式字符串
     */
    public static String toStringYmdWthB(Date date) {
        return (new SimpleDateFormat("yyyy/MM/dd")).format(date);
    }

    /**
     * 将yyyy-MM-dd格式字符串转换为日期对象
     *
     * @param dateStr yyyy-MM-dd格式字符串
     * @return 日期对象
     */
    public static Date toDateYmdWthH(String dateStr) {
        try {
            return (new SimpleDateFormat("yyyy-MM-dd")).parse(dateStr);
        } catch (ParseException e) {
            throw new SystemException(UtilConstant.MODEL_CODE_UTIL, e,
                    UtilErrorMessage.DATE_FORMAT_ERROR, dateStr, "yyyy-MM-dd");
        }
    }

    /**
     * 将日期对象转换为yyyyMMddHHmmss格式字符串
     *
     * @param date 时间对象
     * @return yyyyMMddHHmmss格式字符串
     */
    public static String toStringYmdHms(Date date) {
        return (new SimpleDateFormat("yyyyMMddHHmmss")).format(date);
    }

    /**
     * 将yyyyMMddHHmmss格式字符串转换为日期对象
     *
     * @param dateStr yyyyMMddHHmmss格式字符串
     * @return 日期对象
     */
    public static Date toDateYmdHms(String dateStr) {
        try {
            SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
            return dfm.parse(dateStr);
        } catch (ParseException e) {
            throw new SystemException(UtilConstant.MODEL_CODE_UTIL, e,
                    UtilErrorMessage.DATE_FORMAT_ERROR, dateStr, "yyyyMMddHHmmss");
        }
    }

    /**
     * 将日期对象转换为yyyyMMdd格式字符串
     *
     * @param date 时间对象
     * @return yyyyMMdd格式字符串
     */
    public static String toStringYmd(Date date) {
        return (new SimpleDateFormat("yyyyMMdd")).format(date);
    }

    /**
     * 将日期对象转换为yyMMdd格式字符串
     *
     * @param date 时间对象
     * @return yyMMdd格式字符串
     */
    public static String toStringYYmd(Date date) {
        return (new SimpleDateFormat("yyMMdd")).format(date);
    }

    /**
     * 将yyyyMMdd格式字符串转换为日期对象
     *
     * @param dateStr yyyyMMdd格式字符串
     * @return 日期对象
     */
    public static Date toDateYmd(String dateStr) {
        try {
            return (new SimpleDateFormat("yyyyMMdd")).parse(dateStr);
        } catch (ParseException e) {
            throw new SystemException(UtilConstant.MODEL_CODE_UTIL, e,
                    UtilErrorMessage.DATE_FORMAT_ERROR, dateStr, "yyyyMMdd");
        }
    }

    /**
     * 将日期对象转换为yyyyMM格式字符串
     *
     * @param date 时间对象
     * @return yyyyMM格式字符串
     */
    public static String toStringYm(Date date) {
        return (new SimpleDateFormat("yyyyMM")).format(date);
    }

    /**
     * 将日期对象转换为yyyy-MM格式字符串
     *
     * @param date 时间对象
     * @return yyyy-MM格式字符串
     */
    public static String toStringYcrm(Date date) {
        return (new SimpleDateFormat("yyyy-MM")).format(date);
    }

    /**
     * 将yyyyMM格式字符串转换为日期对象
     *
     * @param dateStr yyyyMM格式字符串
     * @return 日期对象
     */
    public static Date toDateYm(String dateStr) {
        try {
            return (new SimpleDateFormat("yyyyMM")).parse(dateStr);
        } catch (ParseException e) {
            throw new SystemException(UtilConstant.MODEL_CODE_UTIL, e,
                    UtilErrorMessage.DATE_FORMAT_ERROR, dateStr, "yyyyMM");
        }
    }
    /**
     * 将yyyyMM格式字符串转换为日期对象
     *
     * @param dateStr yyyy-MM格式字符串
     * @return 日期对象
     */
    public static Date toDateYcrm(String dateStr) {
        try {
            return (new SimpleDateFormat("yyyy-MM")).parse(dateStr);
        } catch (ParseException e) {
            throw new SystemException(UtilConstant.MODEL_CODE_UTIL, e,
                    UtilErrorMessage.DATE_FORMAT_ERROR, dateStr, "yyyy-MM");
        }
    }

    /**
     * 将日期对象转换为yyyy格式字符串
     *
     * @param date 时间对象
     * @return yyyy格式字符串
     */
    public static String toStringY(Date date) {
        return (new SimpleDateFormat("yyyy")).format(date);
    }

    /**
     * 将yyyy格式字符串转换为日期对象
     *
     * @param dateStr yyyy格式字符串
     * @return 日期对象
     */
    public static Date toDateY(String dateStr) {
        try {
            return (new SimpleDateFormat("yyyy")).parse(dateStr);
        } catch (ParseException e) {
            throw new SystemException(UtilConstant.MODEL_CODE_UTIL, e,
                    UtilErrorMessage.DATE_FORMAT_ERROR, dateStr, "yyyy");
        }
    }

    /**
     * 将日期对象转换为yyyy年MM月dd日格式字符串
     *
     * @param date 时间对象
     * @return yyyyMM格式字符串
     */
    public static String toStringYmdwithChinese(Date date) {
        return (new SimpleDateFormat("yyyy年MM月dd日")).format(date);
    }

    /**
     * 将yyyy-MM-dd HH24:mm:ss格式字符串转换为日期对象
     *
     * @param dateStr yyyy-MM-dd HH24:mm:ss格式字符串
     * @return 日期对象
     * @deprecated 请使用toDateYmdHmsWthH方法
     */
    @Deprecated
    public static Date toDateYmdH24msWthH(String dateStr) {
        return toDateYmdHmsWthH(dateStr);
    }

    /**
     * 将yyyy-MM-dd HH24:mi:ss格式字符串转换为日期对象
     *
     * @param dateStr yyyy-MM-dd HH24:mi:ss格式字符串
     * @return 日期对象
     * @deprecated 请使用toDateYmdHmsWthH方法
     */
    @Deprecated
    public static Date toDateYmdH24misWthH(String dateStr) {
        return toDateYmdH24msWthH(dateStr);
    }

    /**
     * 将日期对象转换为YYYYMMDDHH24MISS格式字符串
     *
     * @param date 时间对象
     * @return YYYYMMDDHH24MISS格式字符串
     * @deprecated 请使用toStringYmdHms方法
     */
    @Deprecated
    public static String toStringYmdH24ms(Date date) {
        return toStringYmdHms(date);
    }

    /**
     * 将YYYYMMDDHH24MISS格式字符串转换为日期对象
     *
     * @param dateStr YYYYMMDDHH24MISS格式字符串<br/>
     * @return 日期对象
     * @deprecated 请使用toDateYmdHms方法
     */
    @Deprecated
    public static Date toDateYmdH24ms(String dateStr) {
        return toDateYmdHms(dateStr);
    }

    /**
     * 将日期对象转换为yyyy年MM月dd日HH24时MI分SS秒格式字符串
     *
     * @param date 时间对象
     * @return yyyyMM格式字符串
     */
    public static String toStringYmdwithsfm(Date date) {
        return (new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒")).format(date);
    }

    /**
     * 比较当前日期(数据库日期)是否大于指定日期
     *
     * @param str 待比较日期参数
     * @return boolean
     * @throws ParseException
     */
    public static boolean dateCompare(Object str) {
        boolean bea = false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String isDate = simpleDateFormat.format(getSysDate());

        Date date1 = null;
        Date date0;
        try {
            if (str instanceof Date) {
                date1 = simpleDateFormat.parse(DateUtil.toStringYmdWthH((Date) str));
            }
            if (str instanceof String) {
                date1 = simpleDateFormat.parse((String) str);
            }
            date0 = simpleDateFormat.parse(isDate);
            if (date0.after(date1)) {
                bea = true;
            }
        } catch (ParseException e) {
            throw new SystemException(UtilConstant.MODEL_CODE_UTIL, e,
                    UtilErrorMessage.DATE_FORMAT_ERROR, str.toString());
        }
        return bea;
    }

    /**
     * 比较两个日期的大小
     * <p/>
     * <pre>
     * 1、日期参数为空表示无穷小
     * </pre>
     *
     * @param inDate1 第一个日期参数
     * @param inDate2 第二个日期参数
     * @return 处理结果 0:相等, -1:inDate1<inDate2, 1:inDate1>inDate2
     * @throws
     */
    public static int dateCompare(Date inDate1, Date inDate2) {
        return dateCompare(inDate1, inDate2, SECOND);
    }

    /**
     * 比较日期大小
     *
     * @param inDate1 日期1
     * @param inDate2 日期2
     * @param unit    比较精度 年：1 ;月：2; 周：3; 日：5; 时：10; 分：12;秒：13
     * @return 处理结果 0:相等, -1:inDate1<inDate2, 1:inDate1>inDate2
     */
    public static int dateCompare(Date inDate1, Date inDate2, int unit) {
        // 字符空验证
        if (inDate1 == null && inDate2 == null) {
            // 两个日期都为空返回相等
            return 0;
        } else if (inDate1 == null) {
            // 日期1为空，日期2不为空返回-1
            return -1;
        } else if (inDate2 == null) {
            // 日期1不为空，日期为空返回2
            return 1;
        }
        return DateUtil.truncate(inDate1, unit).compareTo(DateUtil.truncate(inDate2, unit));
    }

    /**
     * 调整时间, 按照需要调整的单位调整时间
     * <p/>
     * <pre>
     * 例如:保留到日期的年change("20120511154440", DateUtil.YEAE, 2);返回：20140511154440<br/>
     * </pre>
     *
     * @param inDate 传入日志
     * @param unit   调整单位 年：1 ;月：2; 周：3; 日：5; 时：10; 分：12;秒：13
     * @param amount 调整数量
     * @return 调整后的日期
     */
    public static Date change(Date inDate, int unit, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inDate);
        calendar.add(unit, amount);
        return calendar.getTime();
    }

    /**
     * 按照精度要求对日期进行舍取
     * <p/>
     * <pre>
     * 例如:保留到日期的年truncate("20120511154440", DateUtil.YEAE);返回：20120101000000<br/>
     * </pre>
     *
     * @param inDate 输入日期
     * @param unit   单位 年：1 ;月：2;日：5; 时：10; 分：12;秒：13
     * @return 处理后的日期
     */
    public static Date truncate(Date inDate, int unit) {
        return DateUtils.truncate(inDate, unit);
    }

    /**
     * 按照指定的党委获取指定日志的最后值
     * <p/>
     * <pre>
     * 例如:保留到日期的年last("20120511154440", DateUtil.YEAE);返回：20121231235959<br/>
     * </pre>
     *
     * @param inDate 输入日期
     * @param unit   单位 年：1 ;月：2;日：5; 时：10; 分：12;秒：13
     * @return 处理后的日期
     */
    public static Date last(Date inDate, int unit) {
        Date tempDate = truncate(inDate, unit);
        tempDate = change(tempDate, unit, 1);
        return change(tempDate, DateUtil.MILLISECOND, -1);

    }

    /**
     * 比较两个日期的大小
     * <p/>
     * <pre>
     * 1、日期参数为空表示无穷小
     * </pre>
     *
     * @param inDate1 第一个日期参数
     * @param inDate2 第二个日期参数
     * @param unit    单位 年：1 ;月：2;日：5; 时：10; 分：12;秒：13
     * @return 处理结果 0:相等, -1:inDate1<inDate2, 1:inDate1>inDate2
     * @throws
     */
    public static int truncateComare(Date inDate1, Date inDate2, int unit) {
        if (inDate1 == null && inDate2 == null) {
            // 两个日期都为空返回相等
            return 0;
        } else if (inDate1 == null) {
            // 日期1为空，日期2不为空返回-1
            return -1;
        } else if (inDate2 == null) {
            // 日期1不为空，日期为空返回2
            return 1;
        } else {
            Date tempDate1 = truncate(inDate1, unit);
            Date tempDate2 = truncate(inDate2, unit);
            return tempDate1.compareTo(tempDate2);
        }

    }

    /**
     * 将日期格式化为指定的格式
     *
     * @param date    日期
     * @param pattern 格式化模式
     * @return
     */
    public static String format(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 获取数据库当前时间
     * 根据主机时间与主机时间与系统时间的差值获取统一时间
     *
     * @return 数据库时间
     */
    public static Date getSysDate() {
        // 根据主机时间及及

        return new Date(new Date().getTime() + hostDbTimeMinus);
    }

    /**
     * 更新主机时间与数据库统一时间的差值
     *
     * @param minusTime
     */
    public static void updateHostDbTimeMinus(long minusTime) {
        synchronized (hostDbTimeMinus) {
            hostDbTimeMinus = minusTime;
        }
    }

//    /**
//     * 同步系统时间
//     */
//    public static void refreshSysDate() {
//        IBaseDao baseDao = null;
//        try {
//            baseDao = (IBaseDao) LocalContextFactory.getInstance().getBean("baseDao");
//        } catch (Exception e) {
//            log.info("获取baseDao数据源数据源失败", e);
//        }
//        long minusTime = 0l;
//        if (baseDao == null) {
//            log.info("未注册baseDao数据源，程序时间将直接使用主机时间");
//        } else {
//            try {
//                Date hostDate = new Date();
//                Date sysDate = (Date) baseDao.queryForObject("CommonDao.getSysTm");
//                minusTime = sysDate.getTime() - hostDate.getTime();
//            } catch (Exception e) {
//                log.info("获取数据库时间异常", e);
//            }
//        }
//        updateHostDbTimeMinus(minusTime);
//    }

    /**
     * 获取某个日期段内所有月份的集合
     * @param start 开始日期 格式如：20130904，不可小于6位
     * @param end 结束日期 格式如：20130906，不可小于6位
     * @return List 月份集合
     */
    public static List<String> intervalMonths (String start, String end) {
        List<String> list = intervalMonthsList(start, end);
        return list;
    }

    /**
     * 获取某个日期段内所有月份的集合
     * @param start 开始日期 date类型
     * @param end 结束日期 date类型
     * @return List 月份集合
     */
    public static List<String> intervalMonths (Date start, Date end) {
        List<String> list = intervalMonthsList(toStringYcrm(start), toStringYcrm(end));
        return list;
    }
    /**
     * 获取某个日期段内所有月份的集合
     * @param start 开始日期 格式如：20130904，不可小于6位
     * @param end 结束日期 格式如：20130906，不可小于6位
     * @return List 月份集合
     */
    private static List<String> intervalMonthsList (String start, String end) {
        // 判断入参字符串长度是否大于等于六位
        if (start.length() >= 6 && end.length() >= 6) {
            start = start.substring(0, 4) + "-" + start.substring(4, 6);
            end = end.substring(0, 4) + "-" + end.substring(4, 6);
        } else {
            log.error("日期入参有误，请修改入参日期字符串！");
        }
        String splitSign = "-";
        //判断YYYY-MM时间格式的正则表达式
        String regex = "\\d{4}" + splitSign + "(([0][1-9])|([1][012]))";
        if (!start.matches(regex) || !end.matches(regex)) ;
        List<String> list = new ArrayList<String>();
        //start大于end日期时，互换
        if (start.compareTo(end) > 0) {
            String temp = start;
            start = end;
            end = temp;
        }
        //从最小月份开始
        String temp = start;
        while (temp.compareTo(start) >= 0 && temp.compareTo(end) <= 0) {
            //首先加上最小月份,接着计算下一个月份
            list.add(temp.replace("-", ""));
            String[] arr = temp.split(splitSign);
            int year = Integer.valueOf(arr[0]);
            int month = Integer.valueOf(arr[1]) + 1;
            if (month > 12) {
                month = 1;
                year++;
            }
            //补0操作
            if (month < 10) {//补0操作
                temp=year+splitSign+"0"+month;
            } else {
                temp=year+splitSign+month;
            }
        }
        return list;
    }
    /**
     * 计算两个日期时间相差几天,几小时,几分钟
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param format 时间格式
     */
    public static String differTime (String startTime, String endTime, String format) throws ParseException {
        String differTime = differTimes(startTime, endTime, format);
        return differTime;
    }

    /**
     * 计算两个日期时间相差几天,几小时,几分钟
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param format 时间格式
     */
    public static String differTime (Date startTime, Date endTime, String format) throws ParseException {
        String differTime = differTimes(format(startTime, format), format(endTime, format), format);
        return differTime;
    }
    /**
     * 将yyyy/MM/dd HH:mm:ss格式字符串转换为日期对象
     * @param dateStr yyyy/MM/dd HH:mm:ss格式字符串
     * @return 日期对象
     */
    public static Date toDateYmdHmsWthS(String dateStr) {
        try {
            return (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).parse(dateStr);
        } catch (ParseException e) {
            throw new SystemException(UtilConstant.MODEL_CODE_UTIL, e,
                    UtilErrorMessage.DATE_FORMAT_ERROR, dateStr, "yyyy/MM/dd HH:mm:ss");
        }
    }
    /**
     * 计算两个日期时间相差几天,几小时,几分钟
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param format 时间格式
     */
    private static String differTimes (String startTime, String endTime, String format) throws ParseException {
        //按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 1000 * 60;//一分钟的毫秒数
        long ns = 1000;//一秒钟的毫秒数long diff;try {
        //获得两个时间的毫秒时间差异
        long diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
        long day = diff / nd;//计算差多少天
        long hour = diff % nd / nh;//计算差多少小时
        long min = diff % nd % nh / nm;//计算差多少分钟
        long sec = diff % nd % nh % nm / ns;//计算差多少秒//输出结果
        //System.out.println("时间相差："+day+"天"+hour+"小时"+min+"分钟"+sec+"秒。");
        String outDate = "";
        if (day != 0) {
            outDate = day + "天前";
        } else if (day == 0 && hour != 0) {
            outDate = hour + "小时前";
        } else if (day == 0 && hour == 0) {
            outDate = min + "分钟" + sec + "秒前";
        } else if (day == 0 && hour == 0 && min == 0) {
            outDate = sec + "秒前";
        }
        return outDate;
    }
}
