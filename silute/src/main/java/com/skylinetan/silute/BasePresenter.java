package com.skylinetan.silute;

import android.os.Bundle;

import java.lang.ref.WeakReference;

/**
 * Created by skylineTan on 16/11/29.
 */
public class BasePresenter<V extends IView> implements IPresenter<V> {

    private WeakReference<V> viewRef;

    public void attachView(V view) {
        viewRef = new WeakReference<V>(view);
        onAttachView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onDestory() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    public V getView(){
        return viewRef == null ? null : viewRef.get();
    }

    //选择性重写的方法
    protected void onAttachView(){

    }

}
