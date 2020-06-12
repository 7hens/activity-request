# Activity Request

[![jitpack](https://jitpack.io/v/7hens/activity-request.svg)](https://jitpack.io/#7hens/activity-request)
![travis](https://img.shields.io/travis/7hens/activity-request)
[![license](https://img.shields.io/github/license/7hens/activity-request.svg)](https://github.com/7hens/activity-request/blob/master/LICENSE)

- - -

让`Activity`的启动变得如此简单。

## 启动 Activity

```kotlin
suspend fun startActivitySample(context: Context) {
    // 这里的 context 参数甚至可以传 applicationContext
    val (code, data) = MyActivity.startForResult(context)

    when (code) {
        Activity.RESULT_OK -> {}
        Activity.RESULT_CANCELED -> {}
    }
}
```

## 请求动态权限

```kotlin
private suspend fun requestPermissionsSample(context: Context) {
    val storagePermission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE

    // 这里的 context 参数甚至可以传 applicationContext
    val isGranted = AndroidPermissions.request(context, storagePermission)
}
```

## 使用带参数的 Intent

```kotlin
MyActivity.start(context) {
    it.actionName = "foobar"
    it.hello = false
}
```

```kotlin
class MyActivity: Activity() {
    // 这是固定写法，必须实现 ActivityIntentOptions<Companion>
    companion object: ActivityIntentOptions<Companion> {
        var Intent.actionName by string()
        var Intent.hello by boolean()
        var Intent.activityInfo by parcelable<ActivityInfo>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 获取 intent 的参数
        // intent.actionName
        // intent.hello
        // intent.activityInfo
    }
}
```

## 添加依赖

```groovy
implementation 'com.github.7hens:activity-request:0.1'
```
