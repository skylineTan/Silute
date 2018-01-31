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

## 依赖

```
compile 'com.skylinetan.silute:silute:1.0.0'
```

## 使用方法

推荐的使用方法

1.`View`层接口继承自`IView`，`Presenter`层接口继承自`IPresenter`

2.`Activity`继承自抽象类`SilBaseActivity`，`Fragment`继承自`SilBaseFragment`，其中选择性重写的方法有：

* createPresenterFactory：`Presenter`的工厂类，返回一个`BasePresenterFactory`实例
* getLoaderId：返回`Loader`的`id`，一个`id`对应一个`Loader`

3.`Presenter`继承`BasePresenter`实现`Presenter`层接口，`Activity`实现`View`层接口，`BasePresenter`和`factory`里面保存的是`Activity`的弱引用

License
=======

    MIT License

    Copyright (c) 2016-2018 skylineTan

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
