package com.sind.projectx.repository.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dmytro Bekuzarov
 */
public class LoggingUtils {

    public static Logger getLogger() {
        final Throwable t = new Throwable();
        t.fillInStackTrace();
        final String clazz = t.getStackTrace()[1].getClassName();
        return LoggerFactory.getLogger(clazz);
    }

}
