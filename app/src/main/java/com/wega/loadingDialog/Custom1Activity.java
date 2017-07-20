package com.wega.loadingDialog;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wega.library.loadingDialog.LoadingDialog;

/**
 * 自定义参数方法一
 * 通过LoadingDialog.Builder获得LoadingDialog时,定义参数(修改默认参数)
 */
public class Custom1Activity extends AppCompatActivity implements View.OnClickListener {

    private Handler mHandler;
    private Custom1Activity mActivity;
    private LoadingDialog mCustomloadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom1);
        findViewById(R.id.button_success_custom).setOnClickListener(this);
        findViewById(R.id.button_fail_custom).setOnClickListener(this);
        findViewById(R.id.button_cancel_delay_custom).setOnClickListener(this);
        findViewById(R.id.button_complete_custom).setOnClickListener(this);


        mHandler = new Handler();
        mActivity = this;
        //自定义参数方法一:通过LoadingDialog.Builder获得LoadingDialog时,定义参数
        LoadingDialog.Builder builder = new LoadingDialog.Builder(this);
        builder.setLoadingText("处理中...")
                .setFailText("处理失败")
                .setSuccessText("处理成功")
                .setDefaultDelayMillis(5000)//延时5000ms才消失
                .setCancelDelayListener(new LoadingDialog.CancelDelayListener() {
                    @Override
                    public void success(LoadingDialog dialog) {
                        dialog.cancel();//加载框消失
                        Toast.makeText(mActivity, "处理成功==延时消失", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void fail(LoadingDialog dialog) {
                        dialog.cancel();//加载框消失
                        Toast.makeText(mActivity, "处理失败==延时消失", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void complete(LoadingDialog dialog) {
                        dialog.cancel();//加载框消失
                        Toast.makeText(mActivity, "处理完成或直接延时消失==延时消失", Toast.LENGTH_SHORT).show();
                    }
                });


        mCustomloadingDialog = builder.create();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //显示处理成功后5000ms取消加载框 弹出toast
            case R.id.button_success_custom:
                mCustomloadingDialog.loading();//显示加载框
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mCustomloadingDialog.loadSuccess();
                    }
                }, 2000);
                break;
            //显示处理失败后5000ms取消加载框 弹出toast
            case R.id.button_fail_custom:
                mCustomloadingDialog.loading();//显示加载框
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mCustomloadingDialog.loadFail();
                    }
                }, 2000);
                break;
            //延时取消加载框 弹出toast
            case R.id.button_cancel_delay_custom:
                mCustomloadingDialog.loading();//显示加载框
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mCustomloadingDialog.cancelDelay();
                    }
                }, 2000);
                break;
            //处理完成5000ms取消加载框 弹出toast
            case R.id.button_complete_custom:
                mCustomloadingDialog.loading();//显示加载框
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mCustomloadingDialog.cancelDelay();
                    }
                }, 2000);
                break;
        }

    }
}
