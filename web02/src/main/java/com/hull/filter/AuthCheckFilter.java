package com.hull.filter;

import com.hull.utils.DateUtil;
import com.hull.utils.PropertiesUtil;
import com.hull.utils.SessionUtil;
import com.hull.utils.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Administrator on 2016/11/20.
 */
public class AuthCheckFilter implements Filter{

    /**
     * 日志记录工具
     */
    private static final Log log = LogFactory.getLog(AuthCheckFilter.class);

    /**
     * filter过滤器配置信息
     */
    protected FilterConfig filterConfig = null;

    /**
     * 跳过验证的URL
     */
    private List<String> noCheckUrlList = new ArrayList<String>();

    private List<String> notCheckSessionTimeOutList = new ArrayList<String>();

    private boolean ENABLE_SESSION_TIME_OUT;

    private long time_out_ms;


    /**
     * 初始化过滤器
     *
     * @param filterconfig 过滤器配置
     * @throws ServletException 配置异常
     */
    public void init(FilterConfig filterconfig) throws ServletException {
        this.filterConfig = filterconfig;
        String notCheckURLListStr = filterConfig.getInitParameter("notCheckURL");
        if (notCheckURLListStr != null) {
            StringTokenizer st = new StringTokenizer(notCheckURLListStr, ";");
            noCheckUrlList.clear();
            while (st.hasMoreTokens()) {
                noCheckUrlList.add(st.nextToken());
            }
        }

        String notCheckSessionTimeOutStr = filterConfig.getInitParameter("notCheckSessionTimeOut");
        if (notCheckSessionTimeOutStr != null) {
            String[] notCheckSessionTimeOutArray = notCheckSessionTimeOutStr.split(";");
            for (String servletPathStr : notCheckSessionTimeOutArray) {
                notCheckSessionTimeOutList.add(servletPathStr);
            }
        }

        // 读取 uif.properties 中的 time_out
        this.ENABLE_SESSION_TIME_OUT = PropertiesUtil.readString("uif", "SESSION_TIME_OUT") == null ? false : true ;
        if (ENABLE_SESSION_TIME_OUT) {
            String time_out_min_str = PropertiesUtil.readString("uif", "SESSION_TIME_OUT");
            int time_out_min = Integer.parseInt(time_out_min_str);
            this.time_out_ms =  time_out_min*60*1000 ; // 分钟转为 毫秒
        }
    }

    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain) throws IOException, ServletException {
        long startTime = 0L;
        if(log.isDebugEnabled()) {
            startTime = (new Date()).getTime();
            log.debug("调用【doFilter】方法开始");
        }

        HttpServletRequest request = (HttpServletRequest)srequest;
        HttpServletResponse response = (HttpServletResponse)sresponse;
        if(log.isDebugEnabled()) {
            log.debug("验证请求URL=" + request.getRequestURI());
        }

        SessionUtil sessionUtil = new SessionUtil(request);
        if(this.checkRequestURIIntNotFilterList(request)) {
            chain.doFilter(request, response);
        } else {
            if(sessionUtil.getObject("LoginInfo") == null) {
                ServletException useredTime2 = new ServletException("121004");
                log.info("验证不通过进入系统[srequest]=" + srequest, useredTime2);
                throw useredTime2;
            }

            if(this.ENABLE_SESSION_TIME_OUT) {
                boolean useredTime = this.checkSessionTimeOutFilter(request);
                if(sessionUtil.getObject("OperTime") != null && useredTime) {
                    Date nowDate = DateUtil.getSysDate();
                    long diff = nowDate.getTime() - ((Long)sessionUtil.getObject("OperTime")).longValue();
                    if(diff > this.time_out_ms) {
                        ServletException exception = new ServletException("121006");
                        log.info("session 超时 ! 请重新登陆! [srequest]=" + srequest, exception);
                        throw exception;
                    }

                    sessionUtil.setAttr("OperTime", Long.valueOf(nowDate.getTime()));
                }
            }

            if(log.isDebugEnabled()) {
                log.debug("权限过滤器[AuthCheckFilter]验证完成，执行下一个过滤器");
            }

            chain.doFilter(request, response);
        }

        if(log.isDebugEnabled()) {
            long useredTime1 = (new Date()).getTime() - startTime;
            log.debug("调用【doFilter】方法结束, 耗时【" + useredTime1 + "】ms");
        }

    }

    private boolean checkRequestURIIntNotFilterList(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        uri = uri.replaceFirst(contextPath, "");
        return this.noCheckUrlList.contains(uri);
    }

    private boolean checkSessionTimeOutFilter(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        return !this.notCheckSessionTimeOutList.contains(servletPath);
    }

    private boolean checkBrowserInput(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        return StringUtil.isEmptyOrNull(referer)?request.getRequestURI().indexOf("download.do") == -1:false;
    }

    public void destroy() {
        this.filterConfig = null;
    }

}
