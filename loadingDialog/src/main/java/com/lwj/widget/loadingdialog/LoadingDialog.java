package com.lwj.widget.loadingdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.Log;

/**
 * Created by wega on 2018/9/27.
 */

public abstract class LoadingDialog extends Dialog {

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.style_loading_dialog);
        initView();
    }

    public LoadingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }


    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /**
     * 初始化view
     */
    protected abstract void initView();


    /**
     * 延时消失
     */
    protected void dismissDelay(long delayMillis, final DismissDelayEndCallback callback) {

        if(!isShowing()){
            return;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(LoadingDialog.this.isShowing()){
                    LoadingDialog.this.dismiss();
                    if(callback!=null){
                        callback.onEnd(LoadingDialog.this);
                    }
                }
            }
        },delayMillis);

    }

    /**
     * 延时消失回调
     */
    public interface DismissDelayEndCallback {

        void onEnd(LoadingDialog dialog);

    }

}
