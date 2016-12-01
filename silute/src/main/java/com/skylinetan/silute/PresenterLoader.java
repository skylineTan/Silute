package com.skylinetan.silute;

import android.content.Context;
import android.support.v4.content.Loader;

/**
 * Created by skylineTan on 16/12/1.
 */
public class PresenterLoader<P extends IPresenter> extends Loader<P> {

    private PresenterFactory mPresenterFactory;
    private IPresenter mIPresenter;

    public PresenterLoader(Context context, PresenterFactory factory) {
        super(context);
        this.mPresenterFactory = factory;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onStartLoading() {
        if(mIPresenter != null){
            deliverResult((P)mIPresenter);
            return;
        }
        forceLoad();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onForceLoad() {
        mIPresenter = mPresenterFactory.create();
        deliverResult((P)mIPresenter);
    }

    @Override
    protected void onReset() {
        mIPresenter.onDestory();
        mIPresenter = null;
    }
}
