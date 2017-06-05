package com.wega.library.loadingDialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wega.library.R;


/**
 * Created by wega on 2017/6/5.
 */

public class LoadingDialog extends Dialog {


    private ProgressBar mPbLoadingProgress;
    private TextView mTvLoadingDialogText;
    private CharSequence mLoading_text;
    private CharSequence mSuccess_text;
    private CharSequence mFail_text;
    private long defaultDelayMillis = 1000;
    private Context mContext;


    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.style_loading_dialog);
        mContext = context;
        setContentView(R.layout.layout_loading_dialog);

        initView();

        setup();
    }

    private void initView() {

        mTvLoadingDialogText = (TextView) findViewById(R.id.tv_loading_dialog_text);
        mPbLoadingProgress = (ProgressBar) findViewById(R.id.pb_loading_progress);

    }

    private void setup() {

        setCancelable(false);
        mLoading_text = mContext.getString(R.string.loading);
        mSuccess_text = mContext.getString(R.string.load_success);
        mFail_text = mContext.getString(R.string.load_fail);
    }

    private void setLoading_text(CharSequence loading_text) {
        mLoading_text = loading_text;
    }

    private void setSuccess_text(CharSequence success_text) {
        mSuccess_text = success_text;
    }

    private void setFail_text(CharSequence fail_text) {
        mFail_text = fail_text;
    }

    /**
     * 加载中
     */
    public void loading() {
        loading(mLoading_text);
    }

    public void loading(CharSequence loading_text) {

        mTvLoadingDialogText.setText(loading_text);
        if (!this.isShowing()) {
            this.show();
        }

    }

    /**
     * 加载成功
     */
    public void loadSuccess() {
        loadComplete(mSuccess_text);
    }

    public void loadSuccess(CharSequence success_text) {
        loadComplete(success_text);
    }

    /**
     * 加载失败
     */
    public void loadFail() {
        loadComplete(mFail_text);
    }

    public void loadFail(CharSequence fail_text) {
        loadComplete(fail_text);
    }

    /**
     * 加载完成
     */
    public void loadComplete(CharSequence completeText) {
        mTvLoadingDialogText.setText(completeText);
        cancelDelay(defaultDelayMillis);
    }


    public void cancelDelay(long delayMillis) {
        mTvLoadingDialogText.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadingDialog.this.cancel();
            }
        }, delayMillis);
    }


    public static class Builder {

        private Context mBuilder_Context;


        private CharSequence mBuilder_Loading_text;
        private CharSequence mBuilder_Success_text;
        private CharSequence mBuilder_Fail_text;

        private LoadingDialog mLoadingDialog;


        public Builder(Context context) {
            mBuilder_Context = context;
        }


        public LoadingDialog create() {

            if (mLoadingDialog == null) {
                mLoadingDialog = new LoadingDialog(mBuilder_Context);


                if (!TextUtils.isEmpty(mBuilder_Loading_text)) {
                    mLoadingDialog.setLoading_text(mBuilder_Loading_text);
                }


                if (!TextUtils.isEmpty(mBuilder_Success_text)) {
                    mLoadingDialog.setSuccess_text(mBuilder_Success_text);
                }

                if (!TextUtils.isEmpty(mBuilder_Fail_text)) {
                    mLoadingDialog.setFail_text(mBuilder_Fail_text);
                }

            }
            return mLoadingDialog;
        }

        public Builder setLoading_text(CharSequence loading_text) {
            mBuilder_Loading_text = loading_text;
            return this;
        }

        public Builder setSuccess_text(CharSequence success_text) {
            mBuilder_Success_text = success_text;
            return this;

        }

        public Builder setFail_text(CharSequence fail_text) {
            mBuilder_Fail_text = fail_text;
            return this;

        }


    }


}
