package com.wega.library.loadingDialog;

/**
 * Created by lwj on 2017/7/20 21:08.
 * 加载成功 延时消失完成监听事件
 */

public abstract class SuccessCancelDelayListener implements LoadingDialog.CancelDelayListener {


    @Override
    public abstract void success(LoadingDialog dialog) ;

    @Override
    public void fail(LoadingDialog dialog) {

    }

    @Override
    public void complete(LoadingDialog dialog) {

    }
}
