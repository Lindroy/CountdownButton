# CountdownButton
使用Kotlin编写的倒计时按钮。

[![](https://jitpack.io/v/Lindroy/CountdownButton.svg)](https://jitpack.io/#Lindroy/CountdownButton)

## 配置
### 在工程gradle中添加：

```
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
```
### 添加以下依赖：

```
    dependencies {
            implementation 'com.github.Lindroy:CountdownButton:vlatest-version'
    }
```
`latest-version`参见图标`jitpack`后面的版本号，注意不要漏掉前面的v。

## 简单使用

在布局中直接添加`CountdownButton`：
```xml
    <com.lindroid.countdownbutton.CountdownButton
        android:id="@+id/btnSubmit"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="发送验证码" />
```
然后在代码中启动倒计时：

```kotlin
        btnSubmit.setOnClickListener { btnSubmit.start() }
        btnSubmit.setOnFinishedListener {
            //倒计时结束监听
        }
```
更多的方法和写法请参考后面的**属性和方法**和**监听事件的写法**两节。

## 属性和方法

### 布局属性：

| 名称 | 作用 | 类型 | 默认值  |
| ------------ | ------------ | :------------: | :------------: |
| millisInFuture | 倒计时总时间 | integer | 60000（即60s） |
| countDownInterval | 倒计时的间隔 | integer | 1000 |
| tickText | 倒计时进行中的按钮文字 | stringformat格式 | “%s” |
| finishedText | 倒计时完成后的按钮文字  | string  | “重新发送” |

### 属性和方法：

| 名称 | 作用 | 备注 |
| ------------ | ------------ | :------------: |
| millisInFuture | 获取或设置倒计时的总时间 | Java有相应的Getter或Setter方法 |
| countDownInterval | 获取或设置倒计时的时间间隔 | 同上 |
| tickText | 获取或设置倒计时进行中的按钮文字 | 同上 |
| finishText | 获取或设置倒计时结束时的按钮文字 | 同上 |
| start() | 启动倒计时 | / |
| cancel() | 取消倒计时 | / |
| setOnStartListener（） | 开始倒计时的监听 | Lambda形式  |
| setonCancelListener() | 取消倒计时的监听 | Lambda形式 |
| setOnTickListener() | 倒计时进行中的监听 | Lambda形式 |
| setOnFinishedListener() | 倒计时完成的监听 | Lambda形式 |
| setOnCountdownListener() | 设置倒计时监听，包含从开始到完成  | 匿名内部类形式 |

## 监听事件的写法
### Kotlin
在Kotlin中调用监听事件十分简单：
```kotlin
        btnSubmit.setOnFinishedListener {

        }
```
如果是设置多个监听事件，可以使用`apply()`函数：

```kotlin
         btnSubmit.apply {
             setOnClickListener {
                 btnSubmit.start()
             }
             setOnStartListener {
                 Log.d(TAG, "倒计时开始")
             }
             setonCancelListener {
                 Log.d(TAG, "倒计时取消")
             }
             setOnTickListener {
                 Log.d(TAG, "倒计时：$it")
             }
             setOnFinishedListener {
                 Log.d(TAG, "倒计时结束")
                 Toast.makeText(this@MainActivity, "倒计时结束", Toast.LENGTH_SHORT).show()
             }
         }
```

或者使用`setOnCountdownListener()`，传入匿名内部类：

```kotlin
        btnSubmit.setOnCountdownListener(object : CountdownButton.SimpleOnCountdownListener() {
            override fun onStart() {
                super.onStart()
                Log.d(TAG, "倒计时开始")
            }

            override fun onCancel() {
                super.onCancel()
                Log.d(TAG, "倒计时取消")
            }

            override fun onTick(interval: Int) {
                super.onTick(interval)
                Log.d(TAG, "倒计时：$interval")
            }

            override fun onFinished() {
                super.onFinished()
                Log.d(TAG, "倒计时结束")
            }
        })
```

如果传入的是`OnCountdownListener`接口对象，则需要实现所有的方法，而它的`SimpleOnCountdownListener`实现类的对象则不必。

### Java

在Kotlin中以Lambda形式实现的监听在Java中必须使用默认的接口：

```java
        btnSubmit.setOnFinishedListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                //倒计时结束监听
                return null;
            }
        });
```
如果觉得这种方式不美观，也可以选择匿名内部类的形式：
```java
        btnSubmit.setOnCountdownListener(new CountdownButton.OnCountdownListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onTick(int interval) {

            }

            @Override
            public void onFinished() {

            }
        });
```

如果不想一次实现多个方法，也可以像前面的Kotlin一节中的一样传入`SimpleOnCountdownListener`的对象：

```java
        btnSubmit.setOnCountdownListener(new CountdownButton.SimpleOnCountdownListener() {
            @Override
            public void onFinished() {
                super.onFinished();
            }
        });
```
