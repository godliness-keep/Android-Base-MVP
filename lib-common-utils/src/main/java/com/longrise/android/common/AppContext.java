package com.longrise.android.common;

import android.content.Context;

/**
 * Created by godliness on 2019-07-16.
 *
 * @author godliness
 */
public final class AppContext {

    private static Context sContext;

    public static void register(Context context) {
        sContext = context.getApplicationContext();
    }

    public static Context get() {
        return sContext;
    }

    private AppContext(){
        throw new InstantiationError();
    }
}
