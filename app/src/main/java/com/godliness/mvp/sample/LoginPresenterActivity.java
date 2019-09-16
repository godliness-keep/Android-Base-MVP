package com.godliness.mvp.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import com.godliness.mvp.presenter.LoginPresenter;
import com.longrise.android.mvp.internal.BaseMvpActivity;

/**
 * Created by godliness on 2019-09-11.
 *
 * @author godliness
 */
public final class LoginPresenterActivity extends BaseMvpActivity<LoginPresenter> implements LoginPresenter.LoginView {

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
        mPresenter.getLoginData();
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
    public void bindData(ArrayMap<String, String> data) {

    }
}
