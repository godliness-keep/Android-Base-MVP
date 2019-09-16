package com.longrise.android.web.internal;

import android.content.Context;
import android.support.annotation.NonNull;

import com.longrise.android.mvp.utils.MvpLog;

import java.util.LinkedList;

/**
 * Created by godliness on 2019-07-10.
 *
 * @author godliness
 */
final class WebViewFactory {

    private static final int MAX_CACHE_SIZE = 2;
    private static final LinkedList<BaseWebView> WEB_VIEWS = new LinkedList<>();

    static BaseWebView findWebView(@NonNull Context context) {
        synchronized (WEB_VIEWS) {
            if (WEB_VIEWS.size() > 0) {
                return WEB_VIEWS.removeFirst();
            }
        }
        try {
            return new BaseWebView(context.getApplicationContext());
        } catch (Exception e) {
            MvpLog.print(e);
        }
        return null;
    }

    static boolean recycle(BaseWebView webView) {
        synchronized (WEB_VIEWS) {
            if (WEB_VIEWS.size() < MAX_CACHE_SIZE) {
                WEB_VIEWS.add(webView);
                return true;
            }
        }
        return false;
    }

    private WebViewFactory() {
        throw new InstantiationError();
    }
}
