package com.wega.loadingDialog;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wega.library.loadingDialog.LoadingDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LoadingDialog mDefaultLoadingDialog;
    private LoadingDialog mCustomloadingDialog;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();

        findViewById(R.id.button_cancel).setOnClickListener(this);
        findViewById(R.id.button_success).setOnClickListener(this);
        findViewById(R.id.button_fail).setOnClickListener(this);
        findViewById(R.id.button_success_custom).setOnClickListener(this);
        findViewById(R.id.button_fail_custom).setOnClickListener(this);
        findViewById(R.id.button_fail_custom_2).setOnClickListener(this);
        findViewById(R.id.button_success_custom_2).setOnClickListener(this);
        findViewById(R.id.button_cancel_custom_2).setOnClickListener(this);
        findViewById(R.id.button_complete_custom_2).setOnClickListener(this);
        //默认文字
        mDefaultLoadingDialog = new LoadingDialog(this);
//        mDefaultLoadingDialog = new LoadingDialog.Builder(this).create();这样也可以
        //定制文字方法二:通过LoadingDialog.Builder获得LoadingDialog时,定义文字
        LoadingDialog.Builder builder = new LoadingDialog.Builder(this);
        builder.setLoading_text("处理中...")
                .setFail_text("处理失败")
                .setSuccess_text("处理成功");
        mCustomloadingDialog = builder.create();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_cancel:
                mDefaultLoadingDialog.loading();//显示加载框
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDefaultLoadingDialog.cancel();//直接取消加载框
                    }
                }, 2000);
                break;
            case R.id.button_success:
                mDefaultLoadingDialog.loading();//显示加载框
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDefaultLoadingDialog.loadSuccess();//显示加载成功后500ms取消加载框
                    }
                }, 2000);
                break;
            case R.id.button_fail:
                mDefaultLoadingDialog.loading();//显示加载框
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDefaultLoadingDialog.loadFail();//显示加载失败后500ms取消加载框
                    }
                }, 2000);
                break;

            //方法一
            case R.id.button_cancel_custom_2:
                mDefaultLoadingDialog.loading("审核中>>>");//显示加载框
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDefaultLoadingDialog.cancel();//显示处理失败后500ms取消加载框
                    }
                }, 2000);
                break;
            //方法一
            case R.id.button_success_custom_2:
                mDefaultLoadingDialog.loading("审核中>>>");//显示加载框
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDefaultLoadingDialog.loadSuccess("审核成功");//显示处理成功后500ms取消加载框
                    }
                }, 2000);
                break;
            //方法一
            case R.id.button_fail_custom_2:
                mDefaultLoadingDialog.loading("审核中>>>");//显示加载框
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDefaultLoadingDialog.loadFail("审核失败");//显示处理成功后500ms取消加载框
                    }
                }, 2000);
                break;
            //方法一 以上两种情况 成功loadSuccess、失败loadFail时其实都在调用这个方法
            case R.id.button_complete_custom_2:
                mDefaultLoadingDialog.loading("审核中>>>");//显示加载框
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDefaultLoadingDialog.loadComplete("审核完成");//显示处理成功后500ms取消加载框
                    }
                }, 2000);
                break;
            //方法二
            case R.id.button_success_custom:
                mCustomloadingDialog.loading();//显示加载框
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mCustomloadingDialog.loadSuccess();//显示处理成功后500ms取消加载框
                    }
                }, 2000);
                break;
            //方法二
            case R.id.button_fail_custom:
                mCustomloadingDialog.loading();//显示加载框
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mCustomloadingDialog.loadFail();//显示处理失败后500ms取消加载框
                    }
                }, 2000);
                break;
        }
    }


}
