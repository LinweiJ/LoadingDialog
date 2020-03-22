package com.wega.loadingDialog;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lwj.widget.loadingdialog.LoadingDialog;
import com.lwj.widget.loadingdialog.SimpleLoadingDialog;
import com.lwj.widget.lottieloadingdialog.LottieLoadingDialog;

import static com.airbnb.lottie.LottieDrawable.INFINITE;
import static com.lwj.widget.lottieloadingdialog.LottieLoadingDialog.LLD_TYPE_CUSTOM;
import static com.lwj.widget.lottieloadingdialog.LottieLoadingDialog.LLD_TYPE_FAIL_1;
import static com.lwj.widget.lottieloadingdialog.LottieLoadingDialog.LLD_TYPE_LOADING_1;
import static com.lwj.widget.lottieloadingdialog.LottieLoadingDialog.LLD_TYPE_LOADING_2;
import static com.lwj.widget.lottieloadingdialog.LottieLoadingDialog.LLD_TYPE_SUCCESS_1;
import static com.lwj.widget.lottieloadingdialog.LottieLoadingDialog.LLD_TYPE_SUCCESS_2;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Handler mHandler;
    private MainActivity mActivity;
    private SimpleLoadingDialog mSimpleLoadingDialog;
    private LottieLoadingDialog mLottieLoadingDialog;
    private LottieLoadingDialog mLottieLoadingDialog2;
    private LottieLoadingDialog mLottieLoadingDialog3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();
        mActivity = this;
        findViewById(R.id.button_simple).setOnClickListener(this);
        findViewById(R.id.button_Lottie).setOnClickListener(this);
        findViewById(R.id.button_Lottie_can_not_be_canceled_outside).setOnClickListener(this);
        findViewById(R.id.button_Lottie_can_not_be_canceled).setOnClickListener(this);
        //SimpleLoadingDialog
        mSimpleLoadingDialog = new SimpleLoadingDialog(this);
        //LottieLoadingDialog
        mLottieLoadingDialog = new LottieLoadingDialog(this);
        //LottieLoadingDialog
        mLottieLoadingDialog2 = new LottieLoadingDialog(this);
        //LottieLoadingDialog
        mLottieLoadingDialog3 = new LottieLoadingDialog(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_simple:
                //显示加载框
                mSimpleLoadingDialog.showFirst("加载中.....");
                //模拟延时操作
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //显示结果
                        mSimpleLoadingDialog.showResult("加载6秒后加载成功");
//                      //延时消失
                        mSimpleLoadingDialog.dismissDelay(5000, new LoadingDialog.DismissDelayEndCallback() {
                            @Override
                            public void onEnd(LoadingDialog dialog) {
                                Toast.makeText(mActivity, "加载成功显示5秒消失了", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }, 6000);
                break;
            case R.id.button_Lottie:
                mLottieLoadingDialog.setCancelable(true);
                //显示加载框
                mLottieLoadingDialog.showFirst("加载中...", LLD_TYPE_LOADING_1, null,INFINITE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //显示结果
                        mLottieLoadingDialog.showResult("加载4秒后加载成功...", LLD_TYPE_SUCCESS_1, null,0);
                        //延时消失
                        mLottieLoadingDialog.dismissDelay(2000, new LoadingDialog.DismissDelayEndCallback() {
                            @Override
                            public void onEnd(LoadingDialog dialog) {
                                Toast.makeText(mActivity, "加载成功显示2秒消失了", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }, 4000);
                break;
            case R.id.button_Lottie_can_not_be_canceled_outside:
                mLottieLoadingDialog2.setCanceledOnTouchOutside(false);
                //显示加载框
                mLottieLoadingDialog2.showFirst("加载中...", LLD_TYPE_LOADING_2, null,INFINITE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //显示结果
                        mLottieLoadingDialog2.showResult("加载8秒后加载成功...", LLD_TYPE_SUCCESS_2, null,1);
                        //延时消失
                        mLottieLoadingDialog2.dismissDelay(5000, new LoadingDialog.DismissDelayEndCallback() {
                            @Override
                            public void onEnd(LoadingDialog dialog) {
                                Toast.makeText(mActivity, "加载成功显示5秒消失了", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }, 8000);
                break;
            case R.id.button_Lottie_can_not_be_canceled:
                mLottieLoadingDialog3.setCancelable(false);
                //显示加载框
                mLottieLoadingDialog3.showFirst("加载中...", LLD_TYPE_CUSTOM, "loading_plane.json",INFINITE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //显示结果
                        mLottieLoadingDialog3.showResult("加载10秒后加载失败...", LLD_TYPE_FAIL_1, null,0);
                        //延时消失
                        mLottieLoadingDialog3.dismissDelay(3000, new LoadingDialog.DismissDelayEndCallback() {
                            @Override
                            public void onEnd(LoadingDialog dialog) {
                                Toast.makeText(mActivity, "加载失败显示3秒消失了", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }, 10000);
                break;

        }
    }


}
