package com.skylinetan.silute;

import android.os.Bundle;

/**
 * Created by skylineTan on 16/11/29.
 */
public interface IPresenter<V extends IView>{

    void attachView(V view);

    void onCreate(Bundle savedInstanceState);

    void onDestory();

    void onResume();

    void onPause();

    void onStart();

    void onStop();
}
