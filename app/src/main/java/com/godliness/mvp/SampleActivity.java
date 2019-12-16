package com.godliness.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.longrise.android.mvp.BaseActivity;

/**
 * Created by godliness on 2019-12-16.
 *
 * @author godliness
 */
public class SampleActivity extends BaseActivity {

    /**
     * Returns the current page title
     *
     * @return String resource
     */
    @Override
    protected int returnTitle() {
        return 0;
    }

    /**
     * Returns the current layout resource id
     *
     * @param savedInstanceState Bundle state
     * @return Returns the layout resource id
     */
    @Override
    protected int getLayoutResourceId(@Nullable Bundle savedInstanceState) {
        return 0;
    }

    /**
     * Initialize the View
     */
    @Override
    protected void initView() {

    }

    /**
     * Register event
     *
     * @param regEvent boolean
     */
    @Override
    protected void regEvent(boolean regEvent) {

    }

    /**
     * Retry after loading error
     */
    @Override
    public void onReload() {

    }
}
