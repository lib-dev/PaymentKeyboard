# PaymentKeyboard

封装一个简单的支付键盘

## 使用方式

### 1. 在工程中添加依赖

- 在工程的build.gradle中添加repositories:

```java
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

- 在app的build.gradle中添加以下依赖：

  最新[Release](https://github.com/lib-dev/PaymentKeyboard/releases)

```java
dependencies {
	   implementation 'com.github.lib-dev:PaymentKeyboard:Tag'
	}
```

### 2. 使用方法

   在布局文件中添加view，初始化视图，调用IResultCallback回调点击结果，在result中得到输入金额，在clickOK中处理后续逻辑。

   该接口提供两个方法，如下：

   ```java
   public interface IResultCallback {
       void result(String res);
   
       void clickOk();
   }
   ```

   

### 3. 未完待续...
