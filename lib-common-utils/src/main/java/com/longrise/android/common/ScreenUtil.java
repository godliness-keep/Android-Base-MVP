package com.longrise.android.common;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by godliness on 2019-07-16.
 *
 * @author godliness
 */
@SuppressWarnings("unused")
public final class ScreenUtil {

    public static final class DensityConsts {
        public static final String DENSITY = "density";
        public static final String DENSITY_DPI = "density_dpi";
        public static final String HEIGHT_PIXELS = "height_pixels";
        public static final String WIDTH_PIXELS = "width_pixels";
    }

    public static int dp2px(float dpValue) {
        final float scale = AppContext.get().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(float pxValue) {
        final float scale = AppContext.get().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2sp(float pxValue) {
        final float fontScale = AppContext.get().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(float spValue) {
        float fontScale = AppContext.get().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int getWidth() {
        final WindowManager wm = (WindowManager) AppContext.get().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        final DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public static int getHeight() {
        final WindowManager wm = (WindowManager) AppContext.get().getSystemService(Context.WINDOW_SERVICE);
        final DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public static ArrayMap<String, Integer> getDensity() {
        final DisplayMetrics displayMetrics = AppContext.get().getApplicationContext().getResources().getDisplayMetrics();
        final ArrayMap<String, Integer> map = new ArrayMap<>(4);
        map.put(DensityConsts.DENSITY, (int) displayMetrics.density);
        map.put(DensityConsts.DENSITY_DPI, displayMetrics.densityDpi);
        map.put(DensityConsts.HEIGHT_PIXELS, displayMetrics.heightPixels);
        map.put(DensityConsts.WIDTH_PIXELS, displayMetrics.widthPixels);
        return map;
    }

    public static int getStatusBarHeight() {
        int statusBarHeight = -1;
        final int resourceId = AppContext.get().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = AppContext.get().getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    private ScreenUtil() {
        throw new InstantiationError();
    }
}
