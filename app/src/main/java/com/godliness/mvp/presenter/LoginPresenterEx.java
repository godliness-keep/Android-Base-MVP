package com.godliness.mvp.presenter;

import com.godliness.mvp.model.LoginExModel;
import com.longrise.android.mvp.internal.mvp.BasePresenterEx;
import com.longrise.android.mvp.internal.mvp.BaseView;

/**
 * Created by godliness on 2019-09-11.
 *
 * @author godliness
 */
public final class LoginPresenterEx extends BasePresenterEx<LoginPresenterEx.LoginExView, LoginExModel> {

    /**
     * The BasePresenterEx is initialized
     */
    @Override
    protected void exInit() {

    }

    /**
     * Presenter has destroy
     */
    @Override
    public void destroy() {

    }

    public interface LoginExView extends BaseView{

        void bindData(String data);
    }
}
