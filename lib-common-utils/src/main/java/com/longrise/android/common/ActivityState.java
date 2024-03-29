package com.longrise.android.common;

import android.app.Activity;
import android.os.Build;

/**
 * Created by godliness on 2019-07-23.
 *
 * @author godliness
 */
@SuppressWarnings("unused")
public final class ActivityState {

    public static boolean isAlive(Activity target) {
        if (target == null) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return target.isFinishing() || target.isDestroyed();
        }
        return target.isFinishing();
    }

    private ActivityState() {
        throw new InstantiationError();
    }
}
