package com.xj.architecomponentssample.base;

import android.arch.lifecycle.LifecycleOwner;
import android.util.Log;

/**
 * Created by jun xu on 19-5-5.
 */
public class BasePresenter implements FullLifecycleObserver {

    private static final String TAG = "BasePresenter";

    private final LifecycleOwner mLifecycleOwner;

    public BasePresenter(LifecycleOwner lifecycleOwner) {
        mLifecycleOwner = lifecycleOwner;
        mLifecycleOwner.getLifecycle().addObserver(new FullLifecycleObserverAdapter(lifecycleOwner, this));
    }

    @Override
    public void onCreate(LifecycleOwner owner) {
        Log.i(TAG, "onCreate: owner = " + owner);
    }

    @Override
    public void onStart(LifecycleOwner owner) {
        Log.i(TAG, "onStart: owner = " + owner);
    }

    @Override
    public void onResume(LifecycleOwner owner) {
        Log.i(TAG, "onResume: owner = " + owner);
    }

    @Override
    public void onPause(LifecycleOwner owner) {
        Log.i(TAG, "onPause: owner = " + owner);
    }

    @Override
    public void onStop(LifecycleOwner owner) {
        Log.i(TAG, "onStop: owner = " + owner);
    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        Log.i(TAG, "onDestroy: owner = " + owner);
    }
}
