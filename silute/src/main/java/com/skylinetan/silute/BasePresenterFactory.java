package com.skylinetan.silute;

import java.lang.ref.WeakReference;

/**
 * Created by skylineTan on 16/12/1.
 */
public class BasePresenterFactory<P extends IPresenter> implements PresenterFactory<P>{

    private WeakReference<IView> mIViewRef;
    private P mIPresenter;

    public BasePresenterFactory(IView iView, P presenter){
        mIViewRef = new WeakReference<IView>(iView);
        mIPresenter = presenter;
    }

    @SuppressWarnings("unchecked")
    @Override
    public P create() {
        mIPresenter.attachView(mIViewRef.get());
        return mIPresenter;
    }
}
