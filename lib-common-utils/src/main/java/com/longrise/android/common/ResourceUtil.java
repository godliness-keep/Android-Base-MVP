package com.longrise.android.common;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * Created by godliness on 2019-07-16.
 *
 * @author godliness
 * Android resource management
 */
public final class ResourceUtil {

    /**
     * @see Activity#getResources()
     */
    public static Resources getResources() {
        return AppContext.get().getResources();
    }

    /**
     * @see Activity#getDrawable(int)
     */
    public static Drawable getDrawable(@DrawableRes int id) {
        return getResources().getDrawable(id);
    }

    /**
     * @see Activity#getString(int)
     */
    public static String getString(@StringRes int resId) {
        return getResources().getString(resId);
    }

    /**
     * @see Activity#getResources()#getStringArray(int)
     */
    public static String[] getStringArray(@ArrayRes int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * @see Activity#getColor(int)
     */
    public static int getColor(@ColorRes int resId) {
        return getResources().getColor(resId);
    }

    /**
     * @see Activity#getResources() {@link #getColorArray(int)}
     */
    public static int[] getColorArray(@ArrayRes int resId) {
        return getResources().getIntArray(resId);
    }

    private ResourceUtil() {
        throw new InstantiationError();
    }
}
