package com.godliness.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.godliness.basemvp.R;
import com.godliness.mvp.sample.LoginPresenterActivity;
import com.godliness.mvp.sample.LoginPresenterExActivity;
import com.godliness.mvp.sample.LoginThemeActivity;
import com.longrise.android.mvp.BaseActivity;

/**
 * Created by godliness on 2019-09-11.
 *
 * @author godliness
 */
public final class SampleActivity extends BaseActivity implements View.OnClickListener {

    private View mPresenterView;
    private View mPresenterExView;
    private View mThemeView;

    /**
     * Returns the current page title
     *
     * @return String resource
     */
    @Override
    protected int hasTitle() {
        return R.string.string_sample_title;
    }

    /**
     * Returns the current layout resource id
     *
     * @param savedInstanceState Bundle state
     * @return Returns the layout resource id
     */
    @Override
    protected int getLayoutResourceId(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_sample;
    }

    /**
     * Initialize the View
     */
    @Override
    protected void initView() {
        mPresenterView = findViewById(R.id.btn_login_presenter_activity);
        mPresenterExView = findViewById(R.id.btn_login_presenter_ex_activity);
        mThemeView = findViewById(R.id.btn_login_theme_activity);
    }

    /**
     * Register event
     *
     * @param regEvent boolean
     */
    @Override
    protected void regEvent(boolean regEvent) {
        if (mPresenterView != null) {
            mPresenterView.setOnClickListener(regEvent ? this : null);
        }
        if (mPresenterExView != null) {
            mPresenterExView.setOnClickListener(regEvent ? this : null);
        }
        if (mThemeView != null) {
            mThemeView.setOnClickListener(regEvent ? this : null);
        }

    }

    /**
     * Retry after loading error
     */
    @Override
    public void onReload() {

    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        Intent intent = null;
        switch (id) {
            case R.id.btn_login_presenter_activity:
                intent = new Intent(this, LoginPresenterActivity.class);
                break;

            case R.id.btn_login_presenter_ex_activity:
                intent = new Intent(this, LoginPresenterExActivity.class);
                break;

            case R.id.btn_login_theme_activity:
                intent = new Intent(this, LoginThemeActivity.class);
                break;

            default:
                break;
        }
        startActivity(intent);
    }
}
