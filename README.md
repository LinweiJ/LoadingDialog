[![](https://jitpack.io/v/LinweiJ/LoadingDialog.svg)](https://jitpack.io/#LinweiJ/LoadingDialog)

#如何使用它？

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
	        compile 'com.github.LinweiJ:LoadingDialog:V0.0.1'
	}
```


## 效果图

![loadingDialog.gif](https://github.com/LinweiJ/LoadingDialog/blob/master/loadingDialog.gif)

##简单使用

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

## 自定义文字

### 方法一:
```java
LoadingDialog loadingDialog = new LoadingDialog(context);
//显示加载框
loadingDialog.loading("处理中...");
//显示处理成功后取消加载框
loadingDialog.loadSuccess("处理成功");
//显示处理失败后取消加载框
loadingDialog.loadFail("处理失败");
//显示处理完成后取消加载框
//loadSuccess("处理成功") loadFail("处理失败") 都是调用该方法
loadingDialog.loadComplete("处理完成");
```

###方法二:

```java
LoadingDialog.Builder builder = new LoadingDialog.Builder(context);
builder.setLoading_text("处理中...")
        .setFail_text("处理失败")
        .setSuccess_text("处理成功");
LoadingDialog customloadingDialog = builder.create();
```

#License

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
