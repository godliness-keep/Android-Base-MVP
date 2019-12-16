package com.longrise.android.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by godliness on 2019-07-23.
 *
 * @author godliness
 * Broadcast register
 */
@SuppressWarnings("unused")
public final class ReceiverUtil {

    private static final Context CONTEXT = AppContext.get();

    /**
     * Register for cross-process broadcasting
     */
    public static Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        return registerReceiver(receiver, filter, null, null);
    }

    /**
     * Uninstall cross-process broadcasting
     */
    public static void unRegisterReceiver(BroadcastReceiver receiver) {
        CONTEXT.unregisterReceiver(receiver);
    }

    /**
     * Register for local process broadcasts
     */
    public static void registerLocalReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        final LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(CONTEXT);
        broadcastManager.registerReceiver(receiver, filter);
    }

    /**
     * Uninstall local process broadcasts
     */
    public static void unRegisterLocalReceiver(BroadcastReceiver receiver) {
        final LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(CONTEXT);
        broadcastManager.unregisterReceiver(receiver);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Intent registerReceiver(
            BroadcastReceiver receiver, IntentFilter filter, int flags) {
        return CONTEXT.registerReceiver(receiver, filter, flags);
    }

    public static Intent registerReceiver(
            BroadcastReceiver receiver, IntentFilter filter,
            String broadcastPermission, Handler scheduler) {
        return CONTEXT.registerReceiver(receiver, filter, broadcastPermission, scheduler);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Intent registerReceiver(
            BroadcastReceiver receiver, IntentFilter filter,
            String broadcastPermission, Handler scheduler, int flags) {
        return CONTEXT.registerReceiver(receiver, filter, broadcastPermission, scheduler, flags);
    }

    private ReceiverUtil() {
        throw new InstantiationError();
    }

}
