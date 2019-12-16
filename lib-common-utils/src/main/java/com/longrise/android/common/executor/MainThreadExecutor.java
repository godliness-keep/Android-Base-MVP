package com.longrise.android.common.executor;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * Created by godliness on 2019-09-11.
 *
 * @author godliness
 */
public final class MainThreadExecutor implements Executor {

    private final Handler mManThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(Runnable command) {
        mManThreadHandler.post(command);
    }
}
