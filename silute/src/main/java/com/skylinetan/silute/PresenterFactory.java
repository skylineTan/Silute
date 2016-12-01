package com.skylinetan.silute;

/**
 * Created by skylineTan on 16/12/1.
 */
public interface PresenterFactory<P extends IPresenter> {

    P create();

}
