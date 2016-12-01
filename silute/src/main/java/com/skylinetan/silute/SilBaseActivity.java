package com.skylinetan.silute;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by skylineTan on 16/11/29.
 */
public abstract class SilBaseActivity<P extends IPresenter> extends AppCompatActivity implements IView, LoaderManager.LoaderCallbacks<P>{

    protected P mPresenter;

    //返回具体的生命周期委托类
    protected LifecycleDelegate getLifecycleDelegate(){
        return null;
    }

    //选择性实现的方法，如果使用mvp，则要创建Loader
    protected PresenterFactory createPresenterFactory(){
        return null;
    }

    @Override
    public IPresenter getPresenter() {
        return mPresenter;
    }

    //如果使用mvp，则要重写此方法
    protected int getLoaderId(){
        return 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportLoaderManager().initLoader(getLoaderId(), null, this);
        if(getLifecycleDelegate() != null)
            getLifecycleDelegate().onCreate(savedInstanceState);
        if(getPresenter() != null) {
            //presenter的生命周期
            getPresenter().onCreate(savedInstanceState);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(getLifecycleDelegate() != null)
            getLifecycleDelegate().onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(getLifecycleDelegate() != null)
            getLifecycleDelegate().onResume();
        if(getPresenter() != null)
            getPresenter().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(getLifecycleDelegate() != null)
            getLifecycleDelegate().onPause();
        if(getPresenter() != null)
            getPresenter().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(getLifecycleDelegate() != null)
            getLifecycleDelegate().onStop();
        if(getPresenter() != null)
            getPresenter().onPause();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(getLifecycleDelegate() != null)
            getLifecycleDelegate().onDestory();
        if(getPresenter() != null) {
            getPresenter().onDestory();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(getLifecycleDelegate() != null)
            getLifecycleDelegate().onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(getLifecycleDelegate() != null)
            getLifecycleDelegate().onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if(getLifecycleDelegate() != null)
            getLifecycleDelegate().onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(getLifecycleDelegate() != null)
            getLifecycleDelegate().onConfigurationChanged(newConfig);
    }

    @Override
    public Loader<P> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(this, createPresenterFactory());
    }

    @Override
    public void onLoadFinished(Loader<P> loader, P data) {
        this.mPresenter = data;
    }

    @Override
    public void onLoaderReset(Loader<P> loader) {
        this.mPresenter = null;
    }
}
