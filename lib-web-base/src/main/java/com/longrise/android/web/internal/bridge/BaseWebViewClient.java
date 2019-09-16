package com.longrise.android.web.internal.bridge;

import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.longrise.android.mvp.utils.MvpLog;
import com.longrise.android.web.BaseWebActivity;
import com.longrise.android.web.common.SchemeConsts;
import com.longrise.android.web.internal.webcallback.WebCallback;
import com.longrise.android.web.utils.ActivityState;

import java.lang.ref.WeakReference;

/**
 * Created by godliness on 2019-07-09.
 *
 * @author godliness
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class BaseWebViewClient<T extends BaseWebActivity> extends WebViewClient {

    private static final String TAG = "BaseWebViewClient";

    private Handler mHandler;
    private WeakReference<T> mTarget;
    private WeakReference<WebCallback.WebViewClientListener> mClientCallback;

    private boolean mBlockImageLoad;
    private boolean mLoadingError;

    public final void attachTarget(T target) {
        this.mHandler = target.getHandler();
        this.mTarget = new WeakReference<>(target);
        addWebViewClientListener(target);
    }

    protected final boolean isFinishing() {
        return ActivityState.isAlive(mTarget.get());
    }

    protected final Handler getHandler() {
        return mHandler;
    }

    protected final WebCallback.WebViewClientListener getCallback() {
        return mClientCallback != null ? mClientCallback.get() : null;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        MvpLog.e(TAG, "url: " + url);
        final boolean intercept = redirectOverrideUrlLoading(view, url);
        MvpLog.e(TAG, "intercept: " + intercept);
        return intercept;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        final String url = request.getUrl().toString();
        MvpLog.e(TAG, "url: " + url);
        final boolean intercept = redirectOverrideUrlLoading(view, url);
        MvpLog.e(TAG, "intercept: " + intercept);
        return intercept;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (isFinishing()) {
            return;
        }
        this.mLoadingError = false;
//        blockImageLoad(view);
    }

    private boolean mFirstFinished = true;

    @Override
    public void onPageFinished(WebView view, String url) {
        if (isFinishing()) {
            return;
        }
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                final WebCallback.WebViewClientListener callback = getCallback();
                if (callback != null) {
                    if (mLoadingError) {
                        callback.loadingError();
                    } else {
                        callback.loadingPage();
                    }
                }
            }
        });

        if (mFirstFinished) {
            clearForwardHistory(view);
            mFirstFinished = false;
        }
//        toImageLoad(view);
    }

    /**
     * File not found, no network connection, server not found for the main resource
     */
    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        if (interceptErrorState(view, failingUrl, errorCode)) {
            return;
        }
        this.mLoadingError = true;
        MvpLog.e(TAG, "onReceivedError deprecated : " + errorCode);
    }

    /**
     * File not found, no network connection, server not found for the main resource
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        final String description = (String) error.getDescription();
        final int errorCode = error.getErrorCode();
        MvpLog.e(TAG, "onReceivedError: " + " errorCode: " + errorCode + " description: " + description);
        final String failingUrl = request.getUrl().toString();
        if (interceptErrorState(view, failingUrl, errorCode)) {
            return;
        }
        this.mLoadingError = true;
    }

    /**
     * Api Level 23 Capture http error
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        //任何HTTP请求产生的错误都会回调这个方法，包括主页面的html文档请求，iframe、图片等资源请求。
        //在这个回调中，由于混杂了很多请求，不适合用来展示加载错误的页面，而适合做监控报警。
        //当某个URL，或者某个资源收到大量报警时，说明页面或资源可能存在问题，这时候可以让相关运营及时响应修改。
        MvpLog.e(TAG, "onReceivedHttpError");
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        if (handler != null) {
            handler.proceed();
        }

//        if (UrlUtils.isHost(getUrl())) {
//            handler.proceed();
//        } else {
//            super.onReceivedSslError(view, handler, error);
//        }
    }

    private boolean beforeUrlLoading(String url) {
        if (url.startsWith(SchemeConsts.HTTP)
                || url.startsWith(SchemeConsts.HTTPS)
                || url.startsWith(SchemeConsts.FILE)) {
            return false;
        }
        final WebCallback.WebViewClientListener callback = getCallback();
        if (callback != null) {
            return callback.onLoadUri(Uri.parse(url));
        }
        return false;
    }

    /**
     * 阻塞图片加载
     */
    private void blockImageLoad(WebView view) {
        if (!mBlockImageLoad) {
            final WebSettings settings = view.getSettings();
            if (settings != null) {
                settings.setBlockNetworkImage(true);
            }
            mBlockImageLoad = true;
        }
    }

    /**
     * 开始图片加载
     */
    private void toImageLoad(WebView view) {
        if (mBlockImageLoad) {
            final WebSettings settings = view.getSettings();
            if (settings != null) {
                settings.setBlockNetworkImage(false);
            }
            mBlockImageLoad = false;
        }
    }

    private void addWebViewClientListener(WebCallback.WebViewClientListener clientListener) {
        this.mClientCallback = new WeakReference<>(clientListener);
    }

    private boolean interceptErrorState(WebView view, String failingUrl, int errorCode) {
        if (errorCode != -8 && errorCode != -2) {
            boolean isErrorUrl = !TextUtils.equals(failingUrl, view.getUrl()) && !TextUtils.equals(failingUrl, view.getOriginalUrl());
            if (isErrorUrl
                    || (failingUrl == null && errorCode != -12)
                    || errorCode == -1) {
                MvpLog.e(TAG, "intercept onReceivedError");
                return true;
            }
        }
        return false;
    }

    private boolean redirectOverrideUrlLoading(WebView view, String url) {
        if (TextUtils.isEmpty(url) || beforeUrlLoading(url)) {
            return true;
        }
        final WebView.HitTestResult hitTestResult = view.getHitTestResult();
        if (hitTestResult == null) {
            view.loadUrl(url);
            return true;
        }
        return false;
    }

    private boolean interceptAddressType(String url) {
        boolean intercept =
                url.endsWith(".apk")
                        || url.endsWith(".css")
                        || url.endsWith(".png")
                        || url.endsWith(".jpg")
                        || url.endsWith(".gif")
                        || url.endsWith(".js");
        return intercept;
    }

    private void clearForwardHistory(WebView view) {
        final WebBackForwardList forwardList = view.copyBackForwardList();
        if (forwardList != null && forwardList.getSize() > 1) {
            final WebHistoryItem item = forwardList.getItemAtIndex(0);
            if (item != null && TextUtils.equals(item.getUrl(), SchemeConsts.BLANK)) {
                view.clearHistory();
                MvpLog.e(TAG, "clearForwardHistory");
            }
        }
    }
}
