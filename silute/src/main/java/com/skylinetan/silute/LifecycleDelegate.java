package com.skylinetan.silute;

import android.content.res.Configuration;
import android.os.Bundle;

/**
 * Created by skylineTan on 16/11/29.
 */
public interface LifecycleDelegate {

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestory();

    void onSaveInstanceState(Bundle outState);

    void onRestoreInstanceState(Bundle savedInstanceState);

    void onPostCreate(Bundle savedInstanceState);

    void onConfigurationChanged(Configuration newConfig);
}