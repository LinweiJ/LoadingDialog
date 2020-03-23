[![](https://jitpack.io/v/LinweiJ/LoadingDialog.svg)](https://jitpack.io/#LinweiJ/LoadingDialog)

## 1、为何使用它?

开发过程中一个简单的提交表单场景:

1、提交信息，显示"信息提交中，请稍后..."；

2、信息提交成功，显示"信息提交成功"；

3、等待 2 s，返回上一页面。

这时，就需要使用加载框了。

## 2、如何引用它？

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
	        implementation 'com.github.LinweiJ.LoadingDialog:loadingDialog:0.2.0'
	        //使用LottieLoadingDialog,需另外添加
            implementation 'com.github.LinweiJ.LoadingDialog:lottieLoadingDialog:0.2.0'
            implementation 'com.airbnb.android:lottie:2.7.0'	        
}
```



## 3、全新使用

重新设计后 使用更简洁了

### 3.1、Simple

#### 3.1.1 方法及参数

| 方法                                       | 描述                    | 参数           |
| ---------------------------------------- | --------------------- | ------------ |
| SimpleLoadingDialog(@NonNull Context context) | 创建SimpleLoadingDialog | Context      |
| showFirst(String message)                | 第一次显示                 | 显示文字内容       |
| showResult(String message)               | 显示结果（切换文字而已）          | 显示文字内容       |
| dismissDelay(long delayMillis, DismissDelayEndCallback callback) | 延时消失                  | 延时消失时间，消失时回调 |

#### 3.1.2 使用（try it）

```java
//SimpleLoadingDialog
SimpleLoadingDialog  mSimpleLoadingDialog = new SimpleLoadingDialog(this);
//显示加载框
mSimpleLoadingDialog.showFirst("加载中.....");
//模拟延时操作
mHandler.postDelayed(new Runnable() {
    @Override
    public void run() {
       //显示结果
       mSimpleLoadingDialog.showResult("加载6秒后加载成功");
	   //延时消失
       mSimpleLoadingDialog.dismissDelay(5000, new LoadingDialog.DismissDelayEndCallback() {
       	@Override
       	public void onEnd(LoadingDialog dialog) {
         	Toast.makeText(mActivity, "加载成功显示5秒消失了", Toast.LENGTH_SHORT).show();
        	}
        });
    }
}, 6000);
```

#### 3.1.3 效果图

![loadingDialog.gif](https://github.com/LinweiJ/LoadingDialog/blob/master/screen_shot/ld_3_1.gif)

### 3.2、Lottie

#### 3.2.1 方法及参数

| 方法                                       | 描述                    | 参数                                 |
| ---------------------------------------- | --------------------- | ---------------------------------- |
| LottieLoadingDialog(@NonNull Context context) | 创建LottieLoadingDialog | Context context                    |
| showFirst(String message, int typeLottie, @Nullable String jsonFileName, int lottieRepeatCount) | 第一次显示                 | 显示文字内容，Lottie动画Type，Lottie动画文件（可空）, 重复次数（INFINITE（-1）无限次）  |
| showResult(String message, int typeLottie, @Nullable String jsonFileName, int lottieRepeatCount) | 显示结果（切换文字及Lottie动画而已） | 显示文字内容，Lottie动画Type，Lottie动画文件（可空）, 重复次数（INFINITE（-1）无限次） |
| dismissDelay(long delayMillis, DismissDelayEndCallback callback) | 延时消失                  | 延时消失时间，消失时回调                       |



#### 3.2.2 使用（try it）

```java
//LottieLoadingDialog
LottieLoadingDialog  mLottieLoadingDialog = new LottieLoadingDialog(this);
//显示加载框
mLottieLoadingDialog.showFirst("加载中...", LLD_TYPE_LOADING_1, null, INFINITE);
new Handler().postDelayed(new Runnable() {
  @Override
  public void run() {
  	//显示结果
    mLottieLoadingDialog.showResult("加载4秒后加载成功...", LLD_TYPE_SUCCESS_1, null, 0);
    //延时消失
    mLottieLoadingDialog.dismissDelay(2000, new LoadingDialog.DismissDelayEndCallback() {
        @Override
        public void onEnd(LoadingDialog dialog) {
          Toast.makeText(mActivity, "加载成功显示2秒消失了", Toast.LENGTH_LONG).show();
        }
    });
  }
 }, 4000);

```

#### 3.2.3 效果图

![loadingDialog.gif](https://github.com/LinweiJ/LoadingDialog/blob/master/screen_shot/ld_3_2.gif)

### 3.3、其他

#### 3.3.1 、不可取消

```java
//跟Dialog一样样的
mLottieLoadingDialog.setCancelable(false);
mLottieLoadingDialog.setCanceledOnTouchOutside(false);
```

#### 3.3.2 、效果图

![loadingDialog.gif](https://github.com/LinweiJ/LoadingDialog/blob/master/screen_shot/ld_3_3.gif)


#### 3.3.3 、立即消失

```java
//跟Dialog一样样的
mLottieLoadingDialog.cancel();
or
mLottieLoadingDialog.dismiss();
```


## 4. 自定义

#### 4.1 构造函数
- 继承LoadingDialog
- 构造函数调用super方法
```java
public class SimpleLoadingDialog extends LoadingDialog {

    public SimpleLoadingDialog(@NonNull Context context) {
        super(context);
    }
    ...
```
#### 4.2 initView
- 在initView中初始化UI布局
```java
    /**
     * 初始化view
     */
    @Override
    protected void initView() {
        this.setContentView(R.layout.layout_loading_dialog_simple);
        mTvLoadingDialogText = (TextView) this.findViewById(R.id.tv_loading_dialog_text);
    }
```
#### 4.3 showFirst
- 传递并设置显示内容参数
- 调用Dialog的show()方法
```java
     /**
     * 第一次显示
     */
    public void showFirst(String message) {
        mTvLoadingDialogText.setText(message);
        this.show();
    }
```
#### 4.4 showResult
- 传递并设置显示内容参数
```java
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
```
#### 4.5 dismissDelay
- 延时关闭加载框
- 关闭时调用DismissDelayEndCallback的onEnd方法
```java
    @Override
    public void dismissDelay(long delayMillis, DismissDelayEndCallback callback) {
        super.dismissDelay(delayMillis, callback);
    }
```
#### 4.6 DismissDelayEndCallback
- 延时关闭加载框的回调
```java
    /**
     * 延时消失回调
     */
    public interface DismissDelayEndCallback {

        void onEnd(LoadingDialog dialog);

    }
```

## 5、更多

更多细节可以参考 demo/ 示例

## 6、使用到的第三方库

-  [lottie](https://github.com/airbnb/lottie-android)

## 7、版本0.2.0的改进思路

1. 对0.1.0进行重构；
2. 针对LoadingDialog使用场景，增加三个接入接口（开始显示处理，结果显示处理，消失处理）；
3. 对于三个接入接口，我们可以更加自由地定义效果；
4. 针对简单使用原则（懒），提供Simple、Lottie两种快速构建的Builder（也提供一些定制化方法）。
5. 主要还是自己在0.1.0使用过程的一些体验，进行的改进，希望也对你们有所帮助。



# License

```
Copyright 2017~2018 LinWeiJia

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
