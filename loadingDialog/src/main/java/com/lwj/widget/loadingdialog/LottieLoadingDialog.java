package com.lwj.widget.loadingdialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieListener;
import com.airbnb.lottie.LottieTask;
import com.airbnb.lottie.OnCompositionLoadedListener;

/**
 * Created by wega on 2018/9/27.
 */

public class LottieLoadingDialog extends LoadingDialog {

    public static final int TYPE_LOADING_1 = 201;//加载 soda 苏打水
    public static final int TYPE_LOADING_2 = 202;//加载 三球
    public static final int TYPE_SUCCESS_1 = 301;//成功 白圈 白钩
    public static final int TYPE_SUCCESS_2 = 302;//成功 白圈 绿花 白钩
    public static final int TYPE_SUCCESS_3 = 303;//成功 绿圆 白钩
    public static final int TYPE_FAIL_1 = 401;//失败 红叉 red cross
    public static final int TYPE_CUSTOM = 500;//自定义 需要JsonFileName
    private LottieAnimationView mLavLoading;
    private TextView mTvLoadingDialogText;

    public LottieLoadingDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void initView() {
        this.setContentView(R.layout.layout_loading_dialog_lottie);
        mLavLoading = (LottieAnimationView) this.findViewById(R.id.lav_loading);
        mTvLoadingDialogText = (TextView)this.findViewById(R.id.tv_loading_dialog_text);
    }

    /**
     * 展示lottie动画
     * @param typeLottie
     * @param jsonFileName  like "loading_soda.json" ,  json suffix save in assets
     */
    private void showLottie(int typeLottie,@Nullable String jsonFileName){
        LottieTask<LottieComposition> lottieTask =null;
        switch(typeLottie) {
            case TYPE_LOADING_1:
                lottieTask=LottieCompositionFactory.fromAsset(getContext(), "loading_soda.json");
                break;
            case TYPE_LOADING_2:
                lottieTask=LottieCompositionFactory.fromAsset(getContext(), "loading_three_ball.json");
                break;
            case TYPE_SUCCESS_1:
                lottieTask=LottieCompositionFactory.fromAsset(getContext(), "loading_success_white_ring_white_check.json");
                break;
            case TYPE_SUCCESS_2:
                lottieTask=LottieCompositionFactory.fromAsset(getContext(), "loading_success_wr_green_flower_white_check.json");
                break;
            case TYPE_SUCCESS_3:
                lottieTask=LottieCompositionFactory.fromAsset(getContext(), "loading_success_green_circle_white_check.json");
                break;
            case TYPE_FAIL_1:
                lottieTask=LottieCompositionFactory.fromAsset(getContext(), "loading_fail_1.json");
                break;
            case TYPE_CUSTOM:
                lottieTask=LottieCompositionFactory.fromAsset(getContext(), jsonFileName);
                break;
        }
        if(lottieTask!=null){
            lottieTask.addListener(new LottieListener<LottieComposition>() {
                @Override
                public void onResult(LottieComposition result) {
                    mLavLoading.setComposition(result);
                    mLavLoading.setRepeatCount(0);
                    mLavLoading.playAnimation();
                }
            });
        }
    }

    /**
     * 第一次显示
     */
    public void showFirst(String message, int typeLottie, @Nullable String jsonFileName) {
        mTvLoadingDialogText.setText(message);
        showLottie(typeLottie,jsonFileName);
        this.show();
    }

    /**
     * 结果
     * @param message
     */
    public void showResult(String message, int typeLottie, @Nullable String jsonFileName) {
        if(isShowing()){
            mTvLoadingDialogText.setText(message);
            showLottie(typeLottie,jsonFileName);
        }
    }

    @Override
    public void dismissDelay(long delayMillis, DismissDelayEndCallback callback) {
        super.dismissDelay(delayMillis, callback);
    }
}
