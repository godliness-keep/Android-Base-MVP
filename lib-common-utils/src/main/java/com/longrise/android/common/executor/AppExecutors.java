package com.longrise.android.common.executor;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * Created by godliness on 2019-09-11.
 *
 * @author godliness
 */
public final class AppExecutors {

    private static final int THREAD_COUNT = 3;

    public void test() {
    }

    public static Executor mainThread() {
        return ExecutorHolder.mainThread();
    }

    private static final class ExecutorHolder {

        private static DiskIOThreadExecutor sDiskIOExecutor;

        static Executor diskIO() {
            if (sDiskIOExecutor == null) {
                sDiskIOExecutor = new DiskIOThreadExecutor();
            }
            return sDiskIOExecutor;
        }

        static Executor mainThread() {
            return null;
        }

        static Executor serialThread() {
return null;
        }

    }

}
