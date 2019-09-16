package com.godliness.mvp.sample.viewbind;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import com.godliness.mvp.presenter.LoginPresenter;
import com.longrise.android.mvp.BaseViewBindActivity;

/**
 * Created by godliness on 2019-09-11.
 *
 * @author godliness
 */
public final class LoginViewBindActivity extends BaseViewBindActivity<LoginPresenter, LoginViewBind> implements
        LoginPresenter.LoginView {

    /**
     * Initialize the
     */
    @Override
    protected void init() {
        mPresenter.getLoginData();
    }

    /**
     * Returns the current page title
     *
     * @return String resource
     */
    @Override
    protected int hasTitle() {
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

    @Override
    public void bindData(ArrayMap<String, String> data) {
        if (mViewBind != null) {
            mViewBind.bindData(data);
        }
    }
}