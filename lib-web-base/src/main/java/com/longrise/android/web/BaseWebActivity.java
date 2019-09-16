package com.longrise.android.web;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;

import com.longrise.android.mvp.internal.BaseMvpActivity;
import com.longrise.android.mvp.internal.mvp.BasePresenter;
import com.longrise.android.mvp.utils.MvpLog;
import com.longrise.android.web.internal.BaseWebView;
import com.longrise.android.web.internal.SettingInit;
import com.longrise.android.web.internal.bridge.BaseFileChooser;
import com.longrise.android.web.internal.bridge.BaseWebChromeClient;
import com.longrise.android.web.internal.bridge.BaseWebViewClient;
import com.longrise.android.web.internal.webcallback.WebCallback;

/**
 * Created by godliness on 2019-07-09.
 *
 * @author godliness
 */
public abstract class BaseWebActivity<P extends BasePresenter> extends BaseMvpActivity<P> implements WebCallback.WebChromeListener, WebCallback.WebViewClientListener {

    private static final String TAG = "BaseWebActivity";

    private BaseWebView mWebView;
    private BaseFileChooser mFileChooser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
    }

    /**
     * Returns the current WebView instance
     *
     * @return {@link BaseWebView}
     */
    protected abstract BaseWebView getWebView();

    /**
     * Web page loading is performed here {@link #loadUrl(String)}
     *
     * @param savedInstanceState {@link android.app.Activity#onCreate(Bundle)}
     */
    protected abstract void onCreatePerform(@Nullable Bundle savedInstanceState);

    /**
     * Returns the current layout resource id
     *
     * @return Layout resource id
     */
    @LayoutRes
    protected abstract int getLayoutResourceId();

    /**
     * Perform load Web address in {@link #onCreatePerform(Bundle)}
     */
    protected final void loadUrl(String path) {
        if (mWebView != null) {
            mWebView.loadUrl(path);
        }
    }

    /**
     * Notify the WebView to reload
     */
    protected final void notifyWebViewReload() {
        if (mWebView != null) {
            mWebView.reload();
        }
    }

    /**
     * {@link BaseWebChromeClient#openFileChooser}
     */
    @SuppressWarnings("unchecked")
    public final BaseFileChooser createOrGetFileChooser() {
        if (mFileChooser == null) {
            mFileChooser = createFileChooser();
            mFileChooser.attachTarget(this);
        }
        return mFileChooser;
    }

    @Override
    protected final int getLayoutResourceId(@Nullable Bundle savedInstanceState) {
        return getLayoutResourceId();
    }

    @Override
    public final boolean onKeyDown(int keyCode, KeyEvent event) {
        if (webViewGoBack(false)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected BaseWebViewClient getWebViewClient() {
        return null;
    }

    protected BaseWebChromeClient getWebChromeClient() {
        return null;
    }

    protected BaseFileChooser getFileChooser() {
        return null;
    }

    /**
     * {@link BaseWebViewClient#shouldOverrideUrlLoading(WebView, WebResourceRequest)}
     */
    @Override
    public boolean onLoadUri(Uri uri) {
        MvpLog.e(TAG, "uri: " + uri);
        return true;
    }

    protected boolean webViewGoBack(boolean finish) {
        if (mWebView != null) {
            if (mWebView.webViewGoBack()) {
                return true;
            }
        }
        if (finish) {
            finish();
        }
        return false;
    }

    @Override
    protected void onResume() {
        if (mWebView != null) {
            mWebView.onResume();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (mWebView != null) {
            mWebView.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.recycle();
        }
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mFileChooser != null) {
            mFileChooser.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void init(Bundle state) {
        final BaseWebView webView = getWebView();
        if (webView == null) {
            MvpLog.e(TAG, "!!! getWebView() == null !!!");
            return;
        }
        createBridgeAndInit(webView);
        onCreatePerform(state);
    }

    private void createBridgeAndInit(BaseWebView webView) {
        //Init WebView Setting
        SettingInit.initSetting(webView);
        //WebViewClient
        webView.setWebViewClient(createWebViewClient());
        //WebChromeClient
        webView.setWebChromeClient(createWebChromeClient());
        this.mWebView = webView;
    }

    private BaseFileChooser createFileChooser() {
        this.mFileChooser = getFileChooser();
        if (mFileChooser == null) {
            this.mFileChooser = new BaseFileChooser();
        }
        return mFileChooser;
    }

    @SuppressWarnings("unchecked")
    private BaseWebViewClient createWebViewClient() {
        BaseWebViewClient webViewClient = getWebViewClient();
        if (webViewClient == null) {
            webViewClient = new BaseWebViewClient();
        }
        webViewClient.attachTarget(this);
        return webViewClient;
    }

    @SuppressWarnings("unchecked")
    private BaseWebChromeClient createWebChromeClient() {
        BaseWebChromeClient chromeClient = getWebChromeClient();
        if (chromeClient == null) {
            chromeClient = new BaseWebChromeClient();
        }
        chromeClient.attachTarget(this);
        return chromeClient;
    }
}
