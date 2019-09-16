package com.godliness.mvp.presenter;

import android.support.v4.util.ArrayMap;

import com.longrise.android.mvp.internal.mvp.BasePresenter;
import com.longrise.android.mvp.internal.mvp.BaseView;

/**
 * Created by godliness on 2019-09-11.
 *
 * @author godliness
 */
public final class LoginPresenter extends BasePresenter<LoginPresenter.LoginView> {

    /**
     * Presenter initialization is complete
     * This method is called after BaseMvpActivity#initView()
     */
    @Override
    public void init() {

    }

    public void getLoginData() {
        final ArrayMap<String, String> data = new ArrayMap<>();

        if (mView != null) {
            mView.bindData(data);
        }
    }

    /**
     * Presenter has destroy
     */
    @Override
    public void destroy() {

    }

    public interface LoginView extends BaseView {

        void bindData(ArrayMap<String, String> data);
    }

}
