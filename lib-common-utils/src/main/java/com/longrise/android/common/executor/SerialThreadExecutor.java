package com.longrise.android.common.executor;

import android.os.Handler;
import android.os.HandlerThread;

import java.util.concurrent.Executor;

/**
 * Created by godliness on 2019-09-11.
 *
 * @author godliness
 */
public final class SerialThreadExecutor implements Executor {

    private final HandlerThread mThread;
    private final Handler mThreadHandler;

    protected SerialThreadExecutor() {
        this.mThread = new HandlerThread("AppExecutors-SerialThreadExecutor");
        this.mThread.start();
        this.mThreadHandler = new Handler(mThread.getLooper());
    }

    @Override
    public void execute(Runnable command) {
        mThreadHandler.post(command);
    }
}
