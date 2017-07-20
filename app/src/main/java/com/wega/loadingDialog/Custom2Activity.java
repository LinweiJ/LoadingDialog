package com.wega.loadingDialog;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wega.library.loadingDialog.CompleteCancelDelayListener;
import com.wega.library.loadingDialog.FailCancelDelayListener;
import com.wega.library.loadingDialog.LoadingDialog;
import com.wega.library.loadingDialog.SuccessCancelDelayListener;

/**
 * 自定义参数方法二
 * 调用LoadingDialog方法时,传入参数(不会修改默认参数)
 */
public class Custom2Activity extends AppCompatActivity implements View.OnClickListener {

    private LoadingDialog mDefaultLoadingDialog;
    private Handler mHandler;
    private Custom2Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom2);


        mHandler = new Handler();
        mActivity = this;

        findViewById(R.id.button_fail_custom_2).setOnClickListener(this);
        findViewById(R.id.button_success_custom_2).setOnClickListener(this);
        findViewById(R.id.button_cancel_custom_2).setOnClickListener(this);
        findViewById(R.id.button_complete_custom_2).setOnClickListener(this);

        findViewById(R.id.button_fail_custom_2_all).setOnClickListener(this);
        findViewById(R.id.button_success_custom_2_all).setOnClickListener(this);
        findViewById(R.id.button_cancel_custom_2_all).setOnClickListener(this);
        findViewById(R.id.button_complete_custom_2_all).setOnClickListener(this);

        //默认文字 延时消失时间(1000ms)  无延时消失监听事件
        mDefaultLoadingDialog = new LoadingDialog(this);
//        mDefaultLoadingDialog = new LoadingDialog.Builder(this).create();这样也可以

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            //显示处理失败后默认1000ms取消加载框
            case R.id.button_cancel_custom_2:
                mDefaultLoadingDialog.loading("审核中>>>");//显示加载框
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDefaultLoadingDialog.cancel();
                    }
                }, 2000);
                break;
            //显示处理成功后默认1000ms取消加载框
            case R.id.button_success_custom_2:
                mDefaultLoadingDialog.loading("审核中>>>");//显示加载框
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDefaultLoadingDialog.loadSuccess("审核成功");
                    }
                }, 2000);
                break;
            //显示处理成功后默认1000ms取消加载框
            case R.id.button_fail_custom_2:
                mDefaultLoadingDialog.loading("审核中>>>");//显示加载框
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDefaultLoadingDialog.loadFail("审核失败");
                    }
                }, 2000);
                break;
            //以上两种情况 成功loadSuccess、失败loadFail时其实都可以调用这个方法来实行
            case R.id.button_complete_custom_2:
                mDefaultLoadingDialog.loading("审核中>>>");//显示加载框
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDefaultLoadingDialog.loadComplete("审核完成");//显示处理成功后默认1000ms取消加载框
                    }
                }, 2000);
                break;

            //显示处理失败后延时3s消失并弹出toast
            case R.id.button_cancel_custom_2_all:
                mDefaultLoadingDialog.loading("审核中>>>");//显示加载框
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDefaultLoadingDialog.cancelDelay(3000, new CompleteCancelDelayListener() {
                            @Override
                            public void complete(LoadingDialog dialog) {
                                dialog.cancel();//加载框消失
                                Toast.makeText(mActivity, "延时消失==延时3s消失", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }, 2000);
                break;
            //显示处理成功后延时3s消失并弹出toast
            case R.id.button_success_custom_2_all:
                mDefaultLoadingDialog.loading("审核中>>>");//显示加载框
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDefaultLoadingDialog.loadSuccess("审核成功", 3000, new SuccessCancelDelayListener() {
                            @Override
                            public void success(LoadingDialog dialog) {
                                dialog.cancel();//加载框消失
                                Toast.makeText(mActivity, "审核成功==延时3s消失", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }, 2000);
                break;
            //显示处理成功后延时3s消失并弹出toast
            case R.id.button_fail_custom_2_all:
                mDefaultLoadingDialog.loading("审核中>>>");//显示加载框
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDefaultLoadingDialog.loadFail("审核失败", 3000, new FailCancelDelayListener() {
                            @Override
                            public void fail(LoadingDialog dialog) {
                                dialog.cancel();//加载框消失
                                Toast.makeText(mActivity, "审核失败==延时3s消失", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }, 2000);
                break;
            //以上两种情况 成功loadSuccess、失败loadFail时其实都可以调用这个方法来实行
            case R.id.button_complete_custom_2_all:
                mDefaultLoadingDialog.loading("审核中>>>");//显示加载框
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDefaultLoadingDialog.loadComplete("审核完成", 3000, new CompleteCancelDelayListener() {
                            @Override
                            public void complete(LoadingDialog dialog) {
                                dialog.cancel();//加载框消失
                                Toast.makeText(mActivity, "审核完成==延时3s消失", Toast.LENGTH_SHORT).show();
                            }
                        });//显示处理成功后延时3s消失并弹出toast
                    }
                }, 2000);
                break;

        }
    }
}
