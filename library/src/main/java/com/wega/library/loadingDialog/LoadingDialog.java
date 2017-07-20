package com.wega.library.loadingDialog;

import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wega.library.R;


/**
 * Created by wega on 2017/6/5.
 * 加载 dialog
 */

public class LoadingDialog extends Dialog {


    private final int TAG_SUCCESS = 100;
    private final int TAG_FAIL = 101;
    private final int TAG_COMPLETE = 102;

    //加载进度条
    private ProgressBar mPbLoadingProgress;

    private TextView mTvLoadingDialogText;
    //加载文字
    private CharSequence mLoadingText;
    //成功文字
    private CharSequence mSuccess_text;
    //错误文字
    private CharSequence mFailText;
    //延时消失时间
    private long mDefaultDelayMillis;
    private Context mContext;

    //延时消失完成监听事件
    private CancelDelayListener mCancelDelayListener;

    /**
     * 延时消失完成监听事件
     */
    public interface CancelDelayListener {

        void success(LoadingDialog dialog);

        void fail(LoadingDialog dialog);

        void complete(LoadingDialog dialog);

    }

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.style_loading_dialog);
        mContext = context;
        setContentView(R.layout.layout_loading_dialog);
        //加载UI
        initView();
        //设置默认值
        setup();
    }

    private void initView() {

        mTvLoadingDialogText = (TextView) findViewById(R.id.tv_loading_dialog_text);
        mPbLoadingProgress = (ProgressBar) findViewById(R.id.pb_loading_progress);

    }

    private void setup() {

        setCancelable(false);
        mLoadingText = mContext.getString(R.string.loading);
        mSuccess_text = mContext.getString(R.string.load_success);
        mFailText = mContext.getString(R.string.load_fail);
        mDefaultDelayMillis = 1000;
    }

    private void setLoading_text(CharSequence loading_text) {
        mLoadingText = loading_text;
    }

    private void setSuccess_text(CharSequence success_text) {
        mSuccess_text = success_text;
    }

    private void setFail_text(CharSequence fail_text) {
        mFailText = fail_text;
    }

    private void setDefaultDelayMillis(long defaultDelayMillis) {
        mDefaultDelayMillis = defaultDelayMillis;
    }

    private void setCancelDelayListener(CancelDelayListener listener) {
        mCancelDelayListener = listener;
    }


    /**
     * 加载中
     */
    public void loading() {
        loading(mLoadingText);
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
        loadComplete(mSuccess_text, mDefaultDelayMillis, TAG_SUCCESS, mCancelDelayListener);
    }

    public void loadSuccess(CharSequence SuccessText) {
        loadComplete(SuccessText, mDefaultDelayMillis, TAG_SUCCESS, mCancelDelayListener);
    }


    public void loadSuccess(long delayMillis) {
        loadComplete(mSuccess_text, delayMillis, TAG_SUCCESS, mCancelDelayListener);
    }

    public void loadSuccess(SuccessCancelDelayListener listener) {
        loadComplete(mSuccess_text, mDefaultDelayMillis, TAG_SUCCESS, listener);
    }

    public void loadSuccess(CharSequence SuccessText, long delayMillis) {

        loadComplete(SuccessText, delayMillis, TAG_SUCCESS, mCancelDelayListener);
    }

    public void loadSuccess(CharSequence SuccessText, SuccessCancelDelayListener listener) {

        loadComplete(SuccessText, mDefaultDelayMillis, TAG_SUCCESS, listener);
    }

    public void loadSuccess(long delayMillis, SuccessCancelDelayListener listener) {

        loadComplete(mSuccess_text, delayMillis, TAG_SUCCESS, listener);
    }

    public void loadSuccess(CharSequence successText, long delayMillis, SuccessCancelDelayListener listener) {

        loadComplete(successText, delayMillis, TAG_SUCCESS, listener);
    }


    /**
     * 加载失败
     */
    public void loadFail() {
        loadComplete(mFailText, mDefaultDelayMillis, TAG_FAIL, mCancelDelayListener);
    }

    public void loadFail(CharSequence failText) {
        loadComplete(failText, mDefaultDelayMillis, TAG_FAIL, mCancelDelayListener);
    }

    public void loadFail(long delayMillis) {
        loadComplete(mFailText, delayMillis, TAG_FAIL, mCancelDelayListener);
    }

    public void loadFail(FailCancelDelayListener listener) {
        loadComplete(mFailText, mDefaultDelayMillis, TAG_FAIL, listener);
    }


    public void loadFail(CharSequence failText, long delayMillis) {

        loadComplete(failText, delayMillis, TAG_FAIL, mCancelDelayListener);
    }

    public void loadFail(CharSequence failText, FailCancelDelayListener listener) {

        loadComplete(failText, mDefaultDelayMillis, TAG_FAIL, listener);
    }

    public void loadFail(long delayMillis, FailCancelDelayListener listener) {

        loadComplete(mFailText, delayMillis, TAG_FAIL, listener);
    }

    public void loadFail(CharSequence failText, long delayMillis, FailCancelDelayListener listener) {

        loadComplete(failText, delayMillis, TAG_FAIL, listener);
    }

    /**
     * 加载完成
     */
    public void loadComplete(CharSequence completeText) {

        loadComplete(completeText, mDefaultDelayMillis, TAG_COMPLETE, mCancelDelayListener);
    }

    public void loadComplete(CharSequence completeText, long delayMillis, CompleteCancelDelayListener listener) {

        loadComplete(completeText, delayMillis, TAG_COMPLETE, listener);
    }

    private void loadComplete(CharSequence completeText, long delayMillis, int tag, CancelDelayListener listener) {
        mTvLoadingDialogText.setText(completeText);
        cancelDelay(delayMillis, tag, listener);
    }


    /**
     * 延时消失  处理默认完成事件
     */
    public void cancelDelay() {

        cancelDelay(mDefaultDelayMillis, TAG_COMPLETE, mCancelDelayListener);
    }


    public void cancelDelay(long delayMillis, CompleteCancelDelayListener listener) {

        cancelDelay(delayMillis, TAG_COMPLETE, listener);
    }

    /**
     * 延时消失 处理延时消失事件
     */
    private void cancelDelay(long delayMillis, final int tag, final CancelDelayListener cancelDelayListener) {
        mTvLoadingDialogText.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (cancelDelayListener != null) {

                    switch (tag) {
                        case TAG_SUCCESS:
                            cancelDelayListener.success(LoadingDialog.this);
                            break;
                        case TAG_FAIL:
                            cancelDelayListener.fail(LoadingDialog.this);
                            break;
                        case TAG_COMPLETE:
                            cancelDelayListener.complete(LoadingDialog.this);
                            break;
                    }

                } else {
                    LoadingDialog.this.cancel();
                }
            }
        }, delayMillis);
    }

    /**
     * loadingDialog builder
     * 用于设置文字 延时时间 加载完成事件
     */
    public static class Builder {

        private Context mBuilder_Context;


        private CharSequence mBuilder_Loading_text;
        private CharSequence mBuilder_Success_text;
        private CharSequence mBuilder_Fail_text;

        private LoadingDialog mLoadingDialog;
        private long mDefaultDelayMillis;
        private CancelDelayListener mCancelDelayListener;


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

                if (mDefaultDelayMillis > 0) {
                    mLoadingDialog.setDefaultDelayMillis(mDefaultDelayMillis);
                } else {
                    mLoadingDialog.setDefaultDelayMillis(0L);
                }

                mLoadingDialog.setCancelDelayListener(mCancelDelayListener);

            }


            return mLoadingDialog;
        }


        /**
         * 改名 弃用
         *
         * @param loading_text
         * @return
         */
        @Deprecated
        public Builder setLoading_text(CharSequence loading_text) {
            mBuilder_Loading_text = loading_text;
            return this;
        }

        public Builder setLoadingText(CharSequence loadingText) {
            mBuilder_Loading_text = loadingText;
            return this;
        }

        /**
         * 改名 弃用
         *
         * @param success_text
         * @return
         */
        @Deprecated
        public Builder setSuccess_text(CharSequence success_text) {
            mBuilder_Success_text = success_text;
            return this;

        }

        public Builder setSuccessText(CharSequence successText) {
            mBuilder_Success_text = successText;
            return this;

        }

        /**
         * 改名 弃用
         *
         * @param fail_text
         * @return
         */
        @Deprecated
        public Builder setFail_text(CharSequence fail_text) {
            mBuilder_Fail_text = fail_text;
            return this;
        }

        public Builder setFailText(CharSequence failText) {
            mBuilder_Fail_text = failText;
            return this;
        }

        public Builder setDefaultDelayMillis(long defaultDelayMillis) {
            mDefaultDelayMillis = defaultDelayMillis;
            return this;
        }

        public Builder setCancelDelayListener(CancelDelayListener listener) {
            mCancelDelayListener = listener;
            return this;

        }

    }


}
