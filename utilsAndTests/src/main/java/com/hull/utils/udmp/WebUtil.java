package com.hull.utils.udmp;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.FilterConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Pattern;


/**
 * WEB 工具类
 * @author Spring Cao
 * @date 2016-1-8  
*/
public class WebUtil {
    
    /** utf-8 编码 */
    public static final String UTF_8 = "UTF-8";

    /**
     * @description 添加会话cookie
     * 
     * @param name cookie名称
     * @param value cookie值
     * @param request http请求
     * @param response http响应
     */
    public static void addSessionCookie(String name, String value, HttpServletRequest request,
            HttpServletResponse response) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(request.getContextPath()); // 设置客户端返回的cookie的路径
        cookie.setMaxAge(-1); // 永不过期，直到浏览器关闭
        cookie.setSecure(request.isSecure()); // 浏览器是否启用了https or ssl协议
        response.addCookie(cookie);
    }

    /**
     * @description  清除指定的cookie
     * 
     * @param request http请求
     * @param response http响应
     * @param name cookie名称
     */
    public static void clearCookie(HttpServletRequest request, HttpServletResponse response, String name) {

        Cookie cookie = getCookie(request, name);
        if (cookie != null) {
            cookie.setValue("");
            cookie.setMaxAge(0);
            cookie.setSecure(request.isSecure());
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }
    }

    /**
     * @description 清除所有cookies
     * 
     * @param request 请求
     * @param response 响应
     */
    public static void clearCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return;
        }
        for (Cookie c : cookies) {
            c.setValue("");
            c.setMaxAge(0);
            c.setSecure(request.isSecure());
            c.setPath(request.getContextPath());
            response.addCookie(c);
        }
    }

    /**
     * @description  从请求参数中获取指定的cookie
     * 
     * @param request 请求
     * @param name cookie名称
     * @return 返回指定名称的cookie
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {

        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }

        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equals(name)) {
                return cookie;
            }
        }

        return null;
    }

    /**
     * @description  从请求参数中获取指定的cookie的值
     * 
     * @param request 请求
     * @param name cookie名称
     * @return  指定的cookie的值 ，若没有返回null
     */
    public static String getCookieValue(HttpServletRequest request, String name) {

        Cookie cookie = getCookie(request, name);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    /**
     * @description 获得web xml里忽略的urls
     * 
     * @param filterConfig 过滤器的配置文件
     * @param paramKey 配置文件中的参数的key
     * @return 所有的url
     */
    public static Set<String> getIgnoreURIs4Filter(FilterConfig filterConfig, String paramKey) {
        Set<String> ignoreURIs = new HashSet<String>();
        String uris = filterConfig.getInitParameter(paramKey);
        StringTokenizer token = new StringTokenizer(uris, ",");
        while (token.hasMoreTokens()) {
            ignoreURIs.add(token.nextToken().trim());
        }

        return ignoreURIs;
    }

    /**
     * @description 从uris中获取忽略的uris
     * 
     * @param uris 检查的uris
     * @return 忽略的uris
     */
    public static Set<String> getIgnoreURIs4Filter(String uris) {
        Set<String> ignoreURLs = new HashSet<String>();
        StringTokenizer token = new StringTokenizer(uris, ",");
        while (token.hasMoreTokens()) {
            ignoreURLs.add(token.nextToken().trim());
        }
        return ignoreURLs;
    }

    /**
     * @description 重新得到值
     * 
     * @param key 请求参数
     * @param request http请求
     * @return 请求参数对应的value
     */
    public static String retrieveValue(String key, HttpServletRequest request) {
        String value = (String) request.getAttribute(key);
        if (value == null) {
            value = request.getParameter(key);
        }
        if (value == null) {
            value = (String) request.getSession().getAttribute(key);
        }
        return value;
    }

    /**
     * @description 忽略的uri里是否包含指定的url
     * 
     * @param ignoreURIs 忽略的uris
     * @param url 指定的url
     * @return 忽略的uri里是否包含指定的url，有的话返回true，没有的话返回false
     */
    public static boolean contains(Set<String> ignoreURIs, String url) {
        for (Iterator<String> iter = ignoreURIs.iterator(); iter.hasNext();) {
            String ignoreURL = (String) iter.next();
            if (Pattern.matches(ignoreURL, url)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @description 获取请求中的令牌
     * 
     * @param request http请求
     * @return
     */
//    public static String getRequestToken(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        String requestToken = (String) session.getAttribute(WebConstant.SYS_KEY_REQUEST_TOKEN);
//        if (StringUtils.isBlank(requestToken)) {
//            requestToken = OneWayHash.MD5(session.getId());
//        }
//        session.setAttribute(WebConstant.SYS_KEY_REQUEST_TOKEN, requestToken);
//        return requestToken;
//    }

    /**
     * @description 向请求中添加令牌
     * 
     * @param url
     * @param request
     * @return
     */
//    public static String addRequestToken(String url, HttpServletRequest request) {
//        return addURLParameter(url, WebConstant.SYS_KEY_REQUEST_TOKEN, WebUtil.getRequestToken(request));
//    }

    /**
     * @description 向url中添加参数值
     * 
     * @param url 指定的url
     * @param key 添加的key
     * @param value 添加的value
     * @return 添加参数后的url
     */
    public static String addURLParameter(String url, String key, String value) {
        if (StringUtils.isBlank(url)) {
            return url;
        }
        if (!url.startsWith("javascript:") && !"#".equals(url)) {
            if (url.indexOf('?') < 0) {
                url += "?";
            }
            if (!url.endsWith("?")) {
                url += "&";
            }
            url += key + "=" + value;
        }
        return url;
    }

    /**
     * @description 获取请求令牌中的参数
     * 
     * @param httpReq
     * @return
     */
//    public static String getRequestTokenParam(HttpServletRequest httpReq) {
//        if (httpReq == null) {
//            return null;
//        }
//        return WebConstant.SYS_KEY_REQUEST_TOKEN + "=" + WebUtil.getRequestToken(httpReq);
//    }

    /**
     * @description 获取请求令牌中的参数
     * 
     * @return
     */
//    public static String getRequestTokenParam() {
//        return getRequestTokenParam((HttpServletRequest) ThreadBindResourceManager
//           .getBindResource(WebConstant.REQUEST));
//    }

    /**
     * @description 删除url里的参数
     * 
     * @param url 指定的url
     * @param parameter 要删除的参数
     * @return 删除参数后的url
     */
    public static String removeURLParameter(String url, String parameter) {
        int index = url.indexOf("?");
        if (index < 0) {
            return url;
        }
        index = url.indexOf(parameter, index);
        if (index < 0) {
            return url;
        }
        int index2 = url.indexOf("&", index + 1);
        if (index2 < 0) {
            return url.substring(0, index - 1);
        }
        return url.substring(0, index) + url.substring(index2 + 1);
    }

    /**
     * @description 对url按指定的方式 进行编码
     * 
     * @param s 需要编码的url
     * @param charSet 指定的字符集
     * @return 编码后的url
     */
    public static String encodeURL(String s, String charSet) {
        if (StringUtils.isBlank(s) || s.startsWith("javascript:") || "#".equals(s)) {
            return s;
        }
        StringBuilder result = new StringBuilder(s.length() + 20);
        try {
            int bIdx = s.indexOf('?') + 1;
            int eIdx = 0;
            result.append(s.substring(0, bIdx));
            String ecPart = s.substring(bIdx);
            if (ecPart.charAt(ecPart.length() - 1) != '&') {
                ecPart = ecPart + "&";
            }
            String toEc = null;
            while ((bIdx = ecPart.indexOf('&', eIdx)) > -1) {
                toEc = ecPart.substring(eIdx, bIdx);
                eIdx = bIdx + 1;
                bIdx = toEc.indexOf('=');
                if (bIdx > -1) {
                    if (StringUtils.isBlank(charSet)) {
                        result.append(URLEncoder.encode(toEc.substring(0, bIdx), UTF_8));
                    } else {
                        result.append(URLEncoder.encode(toEc.substring(0, bIdx), charSet));
                    }
                    result.append('=');
                    if (StringUtils.isBlank(charSet)) {
                        result.append(URLEncoder.encode(toEc.substring(bIdx + 1), UTF_8));
                    } else {
                        result.append(URLEncoder.encode(toEc.substring(bIdx + 1), charSet));
                    }
                } else {
                    if (StringUtils.isBlank(charSet)) {
                        result.append(URLEncoder.encode(toEc, UTF_8));
                    } else {
                        result.append(URLEncoder.encode(toEc, charSet));
                    }
                }
                result.append('&');
            }
            result.delete(result.length() - 1, result.length());
            return result.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @description 对url按指定的方式进行编码
     * 
     * @param url 需要编码的url
     * @param charSet 指定的字符集
     * @return 编码后的url
     */
    public static String encodeURLComponent(String url, String charSet) {
        try {
            if (StringUtils.isBlank(charSet)) {
                return URLEncoder.encode(url, UTF_8);
            } else {
                return URLEncoder.encode(url, charSet);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @description 将字符串按指定的分隔符转换成set集合
     * 
     * @param src 指定的字符串
     * @param splitchar 分隔符
     * @return 转换后生成set集合
     */
    public static Set<String> convertToSet(String src, String splitchar) {
        if (src == null || splitchar == null) {
            return new HashSet<String>();
        } else {
            String[] result = src.split(splitchar);
            Set<String> set = new HashSet<String>();
            for (String url : result) {
                set.add(url.trim());
            }
            return set;
        }
    }
}
