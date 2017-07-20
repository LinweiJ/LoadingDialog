package com.wega.library.loadingDialog;

/**
 * Created by lwj on 2017/7/20 21:10.
 * 加载完成 延时消失完成监听事件
 */

public  abstract class CompleteCancelDelayListener implements LoadingDialog.CancelDelayListener {


    @Override
    public void success(LoadingDialog dialog) {

    }

    @Override
    public void fail(LoadingDialog dialog) {

    }

    @Override
    public abstract void complete(LoadingDialog dialog) ;

}
