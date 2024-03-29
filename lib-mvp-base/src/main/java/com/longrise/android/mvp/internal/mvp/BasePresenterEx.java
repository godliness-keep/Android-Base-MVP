package com.longrise.android.mvp.internal.mvp;

import com.longrise.android.mvp.utils.GenericUtil;

/**
 * Created by godliness on 2019-06-29.
 *
 * @author godliness
 */
public abstract class BasePresenterEx<V extends BaseView, M extends BaseModel> extends BasePresenter<V> {

    protected final M mModal;

    public BasePresenterEx() {
        this.mModal = GenericUtil.getT(this, 1);
    }

    @Override
    public final void init() {
        extendInit();

        if (mModal != null) {
            mModal.init();
        }
    }

    /**
     * The BasePresenterEx is initialized
     */
    protected abstract void extendInit();
}
