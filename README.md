# Silute
基于Loader的MVP开发库

由于在`Configuration Changes`的时候，`Activity`会遭遇重启，这时候`Presenter`不应该被销毁，如果
`Presenter`受到`Activity`的生命周期控制，由于之前的`Activity`已经不存在，`Presenter`也就相应的不存在了。
保存`Presenter`实例，使用`Loader`是一种思路，`Silute`的特点如下
* `Loader`有自己的生命周期，Activity/Fragment都能持有`Loader`的引用，并且`Loader`在`Configuration Changes`的时候不会销毁，
在`Activity`重启的时候，能重新连接。
```
这点和调用了setRetainInstance(true)的Fragment很像，官方API Guides也提到
如果需要恢复大量的数据，Configuration Changes的时候是有必要使Activity重启的，这时候可以使用setRetainInstance(true)的Fragment
来保存大量的不会造成内存泄漏的数据。其中调用了setRetainInstance(true)的Fragment在Configuration Changes的时候不会销毁
```
* `Presenter`、`Model`和`View`都通过接口进行交互，`Silute`封装了大部分Model-View-Presenter绑定的代码，使用起来非常简单
* `Activity`的生命周期内回调`LifecycleDelegate`对应的方法，可以自己完成具体的实现类
* `Presenter`拥有`Activity`的生命周期的方法，可以在`Activity`对应的生命周期内做相应的操作
* `BaseActivity`里面的`Presenter`有关的方法是选择性的，一些简单的页面不需要使用`MVP`的时候，不用重写

##使用方法

推荐的使用方法
1.View层接口实现自IView，Presenter层接口实现自IPresenter

2.Activity继承自抽象类SilBaseActivity，Fragment继承自SilBaseFragment，其中选择性重写的方法有：

* createPresenterFactory：Presenter的工厂类，返回一个BasePresenterFactory实例
* getLoaderId：返回Loader的id，一个id对应一个Loader

3.Presenter继承BasePresenter实现Presenter层接口，Activity实现View层接口，BasePresenter和factory里面保存的是Activity的弱引用
