[![](https://jitpack.io/v/LinweiJ/LoadingDialog.svg)](https://jitpack.io/#LinweiJ/LoadingDialog)

# 如何使用它？

先在 project的build.gradle  添加:
```
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```
然后在module的build.gradle 添加:
```
dependencies {
	        compile 'com.github.LinweiJ:LoadingDialog:0.1.0'
	}
```




## 简单使用

```java
LoadingDialog loadingDialog = new LoadingDialog(context);
//或 LoadingDialog loadingDialog = new LoadingDialog.Builder(context).create();
//显示加载框
loadingDialog.loading();
//直接取消加载框
loadingDialog.cancel();
//显示加载成功后取消加载框
loadingDialog.loadSuccess();
//显示加载失败后取消加载框
loadingDialog.loadFail();
```

![loadingDialog.gif](https://github.com/LinweiJ/LoadingDialog/blob/master/loadingDialog_0.gif)


## 自定义参数(文字 延时时间 延时消失事件)

### 方法一:

```java
//自定义参数方法一:通过LoadingDialog.Builder获得LoadingDialog时,定义参数
        LoadingDialog.Builder builder = new LoadingDialog.Builder(this);
        builder.setLoadingText("处理中...")
                .setFailText("处理失败")
                .setSuccessText("处理成功")
          		//设置延时5000ms才消失,可以不设置默认1000ms
                .setDefaultDelayMillis(5000)
          		//设置默认延时消失事件, 可以不设置默认不调用延时消失事件
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
LoadingDialog loadingDialog = builder.create();

loadingDialog.loadSuccess();  调用  CancelDelayListener.success()
loadingDialog.loadFail();  调用  CancelDelayListener.fail()
loadingDialog.cancelDelay();  调用  CancelDelayListener.complete()  

  
//注意 : 传入延时消失事件时,需要添加 dialog.cancel() 才能使加载框消失
       
```
![loadingDialog.gif](https://github.com/LinweiJ/LoadingDialog/blob/master/loadingDialog_1.gif)


### 方法二:
```java
LoadingDialog loadingDialog = new LoadingDialog(context);
//显示加载框
loadingDialog.loading("处理中...");
//显示处理成功后取消加载框
loadingDialog.loadSuccess("处理成功");
//显示处理失败后取消加载框
loadingDialog.loadFail("处理失败");
//显示处理完成后取消加载框
loadingDialog.loadComplete("处理完成");

//显示审核成功后延时3s消失 调用SuccessCancelDelayListener.success()
 loadingDialog.loadSuccess("审核成功", 3000, new SuccessCancelDelayListener() {
                            @Override
                            public void success(LoadingDialog dialog) {
                                dialog.cancel();//加载框消失
                            }
                        });
//显示审核失败后延时3s消失 调用SuccessCancelDelayListener.fail()
loadingDialog.loadFail("审核失败", 3000, new FailCancelDelayListener() {
                            @Override
                            public void fail(LoadingDialog dialog) {
                                dialog.cancel();//加载框消失
                                Toast.makeText(mActivity, "审核失败==延时3s消失", Toast.LENGTH_SHORT).show();
                            }
                        });
//显示审核完成后延时3s消失 调用CompleteCancelDelayListener.complete()
loadingDialog.loadComplete("审核完成", 3000, new CompleteCancelDelayListener() {
                            @Override
                            public void complete(LoadingDialog dialog) {
                                dialog.cancel();//加载框消失
                                Toast.makeText(mActivity, "审核完成==延时3s消失", Toast.LENGTH_SHORT).show();
                            }
                        });//显示处理成功后延时3s消失并弹出toast
//延时3s消失 调用CompleteCancelDelayListener.complete()
loadingDialog.cancelDelay(3000, new CompleteCancelDelayListener() {
                            @Override
                            public void complete(LoadingDialog dialog) {
                                dialog.cancel();//加载框消失
                            }
                        });



```
![loadingDialog.gif](https://github.com/LinweiJ/LoadingDialog/blob/master/loadingDialog_2.gif)

当然, 方法一 可以和方法二混合使用

# License

```
Copyright 2017 LinWeiJia

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
