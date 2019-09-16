package com.godliness.mvp.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.godliness.mvp.presenter.LoginPresenterEx;
import com.longrise.android.mvp.internal.BaseMvpActivity;

/**
 * Created by godliness on 2019-09-11.
 *
 * @author godliness
 */
public final class LoginPresenterExActivity extends BaseMvpActivity<LoginPresenterEx> implements LoginPresenterEx.LoginExView {
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

    @Override
    public void bindData(String data) {

    }
}
