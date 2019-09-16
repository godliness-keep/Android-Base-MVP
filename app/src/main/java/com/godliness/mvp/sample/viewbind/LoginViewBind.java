package com.godliness.mvp.sample.viewbind;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import com.longrise.android.mvp.internal.viewbind.BaseViewBinding;

/**
 * Created by godliness on 2019-09-11.
 *
 * @author godliness
 */
public class LoginViewBind extends BaseViewBinding<LoginViewBindActivity> {
    /**
     * Binding the View
     *
     * @param target T
     */
    @Override
    protected void bindView(@NonNull LoginViewBindActivity target) {

    }

    public void bindData(ArrayMap<String, String> data) {

    }

    /**
     * Register event
     *
     * @param regEvent Boolean
     */
    @Override
    protected void regEvent(boolean regEvent) {

    }

    /**
     * The target has been destroy
     */
    @Override
    public void onDestroy() {

    }
}
