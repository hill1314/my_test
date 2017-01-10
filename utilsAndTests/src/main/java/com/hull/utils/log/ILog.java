package com.hull.utils.log;

import org.slf4j.Logger;

/**
 * Created by hull on 2017/1/9.
 */
public interface ILog {
    /**
     * @Fields logger : 日志logger对象
     */
    public Logger logger = LoggerFactory.getLogger();
}
