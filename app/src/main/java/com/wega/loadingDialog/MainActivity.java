package com.wega.loadingDialog;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lwj.widget.loadingdialog.LoadingDialog;
import com.lwj.widget.loadingdialog.LottieLoadingDialog;
import com.lwj.widget.loadingdialog.SimpleLoadingDialog;


import static com.lwj.widget.loadingdialog.LottieLoadingDialog.TYPE_LOADING_1;
import static com.lwj.widget.loadingdialog.LottieLoadingDialog.TYPE_SUCCESS_1;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private LoadingDialog mDefaultLoadingDialog;
//    private LoadingDialog mCustomloadingDialog;
    private Handler mHandler;
    private MainActivity mActivity;
    private SimpleLoadingDialog mSimpleLoadingDialog;
    private LottieLoadingDialog mLottieLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();
        mActivity = this;
        findViewById(R.id.button_cancel).setOnClickListener(this);
        findViewById(R.id.button_success).setOnClickListener(this);
        findViewById(R.id.button_fail).setOnClickListener(this);
        findViewById(R.id.button_custom_1).setOnClickListener(this);
        findViewById(R.id.button_custom_2).setOnClickListener(this);


        //默认文字 延时消失时间(1000ms)  没有延时消失监听事件
//        mDefaultLoadingDialog = new LoadingDialog(this);
//        mDefaultLoadingDialog = new LoadingDialog.Builder(this).create();这样也可以

        mSimpleLoadingDialog = new SimpleLoadingDialog(this);
        mLottieLoadingDialog = new LottieLoadingDialog(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_cancel:
                //显示加载框
                mSimpleLoadingDialog.showFirst("加载中.....");
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSimpleLoadingDialog.showResult("加载5秒后加载成功加载成功");
                        mSimpleLoadingDialog.dismissDelay(5000, new LoadingDialog.DismissDelayEndCallback() {
                            @Override
                            public void onEnd(LoadingDialog dialog) {
                                 Toast.makeText(mActivity,"加载成功显示5秒消失了",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }, 6000);
                break;
            case R.id.button_success:


                mLottieLoadingDialog.setCanceledOnTouchOutside(true);
                //展示
                mLottieLoadingDialog.showFirst("加载中...",TYPE_LOADING_1,null);
                new Handler().postDelayed(new  Runnable(){

                    @Override
                    public void run() {
                        //结果
                        mLottieLoadingDialog.showResult("加载成功...",TYPE_SUCCESS_1,null);
                        //延时显示
                        mLottieLoadingDialog.dismissDelay(2000, new LoadingDialog.DismissDelayEndCallback() {
                            @Override
                            public void onEnd(LoadingDialog dialog) {

                                Toast.makeText(mActivity,"结束",Toast.LENGTH_LONG).show();

                            }
                        });
                    }
                },4000);


//                mDefaultLoadingDialog.loading();//显示加载框
//                //模拟延时操作
//                mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mDefaultLoadingDialog.loadSuccess();//显示加载成功后1000ms取消加载框
//                    }
//                }, 2000);
                break;
            case R.id.button_fail:
//                mDefaultLoadingDialog.loading();//显示加载框
//                //模拟延时操作
//                mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mDefaultLoadingDialog.loadFail();//显示加载失败后1000ms取消加载框
//                    }
//                }, 2000);
                break;

            //方法一
            case R.id.button_custom_1:
               startActivity(new Intent(mActivity,Custom1Activity.class));
                break;
            //方法二
            case R.id.button_custom_2:
                startActivity(new Intent(mActivity,Custom2Activity.class));
                break;

        }
    }


}
