package com.longrise.android.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.telephony.TelephonyManager;

/**
 * Created by godliness on 2019-07-16.
 *
 * @author godliness
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public final class NetUtil {

    public interface NetType{
        int TYPE_NON = -1;
        int TYPE_WIFI = 0;
        int TYPE_MOBILE = 1;
        int TYPE_GPRS = 2;
    }

    /**
     * 根据UID获取网络接收流量
     *
     * @param uid uid
     */
    public static long getUidRxBytes(int uid) {
        return TrafficStats.getUidRxBytes(uid);
    }

    /**
     * 根据UID获取网络传送流量
     *
     * @param uid uid
     */
    public static long getUidTxBytes(int uid) {
        return TrafficStats.getUidTxBytes(uid);
    }

    /**
     * 是否WIFI环境
     *
     * @return true表示WIFI环境
     */
    public static boolean isWIFI() {
        final ConnectivityManager manager = (ConnectivityManager) AppContext.get().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            final NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
        }
        return false;
    }

    /**
     * 是否有网络连接
     *
     * @return true表示有网络连接
     */
    public static boolean hasNetwork() {
        final ConnectivityManager manager = (ConnectivityManager) AppContext.get().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            final NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isAvailable();
        }
        return false;
    }

    /**
     * 获取网络连接类型
     *
     * @return -1表示没有网络
     */
    public static int getNetWorkType(Context context) {
        final ConnectivityManager conn = (ConnectivityManager) context.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conn == null) {
            return NetType.TYPE_NON;
        }
        final NetworkInfo info = conn.getActiveNetworkInfo();
        if (info == null || !info.isAvailable()) {
            return NetType.TYPE_NON;
        }
        int type = info.getType();
        if (type == ConnectivityManager.TYPE_WIFI) {
            return NetType.TYPE_WIFI;
        } else {
            final TelephonyManager tm = (TelephonyManager) context.getApplicationContext()
                    .getSystemService(Context.TELEPHONY_SERVICE);
            switch (tm.getNetworkType()) {
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    return NetType.TYPE_GPRS;
                case TelephonyManager.NETWORK_TYPE_EDGE:
                    return NetType.TYPE_GPRS;
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    return NetType.TYPE_GPRS;
                default:
                    return NetType.TYPE_MOBILE;
            }
        }
    }

    private NetUtil() {
        throw new InstantiationError();
    }
}
