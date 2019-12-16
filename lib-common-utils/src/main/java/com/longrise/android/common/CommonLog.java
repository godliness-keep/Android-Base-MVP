package com.longrise.android.common;

import android.util.Log;

/**
 * Created by godliness on 2019-07-23.
 *
 * @author godliness
 */
@SuppressWarnings("unused")
public final class CommonLog {

    private static Logger sLogger;

    static {
        if (BuildConfig.DEBUG) {
            sLogger = new DefaultLogger();
        }
    }

    public interface Logger {

        void v(String tag, String msg);

        void d(String tag, String msg);

        void i(String tag, String msg);

        void w(String tag, String msg);

        void e(String tag, String msg);

        void print(Throwable throwable);
    }

    public static void setLogger(Logger logger) {
        if (BuildConfig.DEBUG) {
            sLogger = logger;
        }
    }

    public static void v(String tag, String msg) {
        final Logger logger = sLogger;
        if (logger != null) {
            logger.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        final Logger logger = sLogger;
        if (logger != null) {
            logger.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        final Logger logger = sLogger;
        if (logger != null) {
            logger.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        final Logger logger = sLogger;
        if (logger != null) {
            logger.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        final Logger logger = sLogger;
        if (logger != null) {
            logger.e(tag, msg);
        }
    }

    public static void print(Throwable throwable) {
        final Logger logger = sLogger;
        if (logger != null) {
            logger.print(throwable);
        }
    }

    private static final class DefaultLogger implements Logger {

        @Override
        public void v(String tag, String msg) {
            Log.v(tag, msg);
        }

        @Override
        public void d(String tag, String msg) {
            Log.d(tag, msg);
        }

        @Override
        public void i(String tag, String msg) {
            Log.i(tag, msg);
        }

        @Override
        public void w(String tag, String msg) {
            Log.w(tag, msg);
        }

        @Override
        public void e(String tag, String msg) {
            Log.e(tag, msg);
        }

        @Override
        public void print(Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private CommonLog() {
        throw new InstantiationError();
    }
}
