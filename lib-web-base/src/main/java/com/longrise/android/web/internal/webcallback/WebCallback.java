package com.longrise.android.web.internal.webcallback;

import android.net.Uri;

/**
 * Created by godliness on 2019-07-09.
 *
 * @author godliness
 */
public interface WebCallback {

    interface WebChromeListener {

        /**
         * Current web page title
         *
         * @param title String
         */
        void onReceivedTitle(String title);

        /**
         * Current page loading progress
         *
         * @param newProgress progress
         */
        void onProgressChanged(int newProgress);

    }

    interface WebViewClientListener {

        /**
         * Loading the page
         */
        void loadingPage();

        /**
         * Page loading error
         */
        void loadingError();

        /**
         * Unknown uri type
         *
         * @param uri Uri
         * @return Boolean Whether or not to consume
         */
        boolean onLoadUri(Uri uri);
    }
}
