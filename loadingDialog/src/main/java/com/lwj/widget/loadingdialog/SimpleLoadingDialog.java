package com.lwj.widget.loadingdialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;


/**
 * Created by wega on 2018/9/27.
 */

public class SimpleLoadingDialog extends LoadingDialog {

    private TextView mTvLoadingDialogText;

    public SimpleLoadingDialog(@NonNull Context context) {
        super(context);
    }

    /**
     * 初始化view
     */
    @Override
    protected void initView() {
        this.setContentView(R.layout.layout_loading_dialog_simple);
        mTvLoadingDialogText = (TextView) this.findViewById(R.id.tv_loading_dialog_text);
    }

    /**
     * 第一次显示
     */
    public void showFirst(String message) {
        mTvLoadingDialogText.setText(message);
        this.show();
    }

    /**
     * 结果
     *
     * @param message
     */
    public void showResult(String message) {
        if (isShowing()) {
            mTvLoadingDialogText.setText(message);
        }
    }


}
