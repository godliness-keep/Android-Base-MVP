package com.longrise.android.mvp.internal.mvp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;

/**
 * Created by godliness on 2019-06-29.
 *
 * @author godliness
 */
@SuppressWarnings("unused")
public abstract class BasePresenter<V extends BaseView> {

    private static final String TAG = "BasePresenter";

    @Nullable
    protected Context mContext;
    @Nullable
    protected V mView;

    private boolean mFinished;

    /**
     * Presenter initialization is complete
     * This method is called after BaseMvpActivity#initView()
     */
    public abstract void init();

    /**
     * {@link Activity#isFinishing()}
     */
    public final boolean isFinish() {
        return mFinished;
    }

    /**
     * Presenter has destroy
     */
    public abstract void destroy();

    public final void attachV(V v) {
        this.mView = v;
        if (v instanceof Context) {
            this.mContext = (Context) v;
        }
    }

    public final void detachTarget() {
        this.mContext = null;
        this.mView = null;
        destroy();
    }

    public final void notifyFinish() {
        this.mFinished = true;
    }

    /**
     * {@link com.longrise.android.mvp.BaseActivity#showLoadingStyle()}
     */
    public final void showLoadingStyle() {
        if (mView != null) {
            mView.showLoadingStyle();
        }
    }

    /**
     * {@link com.longrise.android.mvp.BaseActivity#showLoadingError()}
     */
    public final void showLoadingError() {
        if (mView != null) {
            mView.showLoadingError();
        }
    }

    /**
     * {@link com.longrise.android.mvp.BaseActivity#showLoadingEmpty()}
     */
    public final void showLoadingEmpty() {
        if (mView != null) {
            mView.showLoadingEmpty();
        }
    }

    /**
     * {@link com.longrise.android.mvp.BaseActivity#dismissLoadingStyle()}
     */
    public final void dimissLoadingStyle() {
        if (mView != null) {
            mView.dismissLoadingStyle();
        }
    }
}
