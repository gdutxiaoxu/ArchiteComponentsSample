package com.xj.architecomponentssample.base;


import android.arch.lifecycle.LifecycleOwner;
import android.os.Handler;
import android.os.Looper;

/**
 * Created by jun xu on 19-5-5.
 */
public class LifecycleHandler extends Handler implements FullLifecycleObserver {


    private LifecycleOwner mLifecycleOwner;

    public LifecycleHandler(LifecycleOwner lifecycleOwner) {
        mLifecycleOwner = lifecycleOwner;
        addObserver();
    }

    public LifecycleHandler(LifecycleOwner lifecycleOwner, Callback callback) {
        super(callback);
        mLifecycleOwner = lifecycleOwner;
        addObserver();
    }

    public LifecycleHandler(LifecycleOwner lifecycleOwner, Looper looper) {
        super(looper);
        mLifecycleOwner = lifecycleOwner;
        addObserver();
    }

    public LifecycleHandler(LifecycleOwner lifecycleOwner, Looper looper, Callback callback) {
        super(looper, callback);
        mLifecycleOwner = lifecycleOwner;
        addObserver();
    }

    private void addObserver() {
        if (mLifecycleOwner != null) {
            mLifecycleOwner.getLifecycle().addObserver(new FullLifecycleObserverAdapter(mLifecycleOwner, this));
        }
    }


    @Override
    public void onCreate(LifecycleOwner owner) {

    }

    @Override
    public void onStart(LifecycleOwner owner) {

    }

    @Override
    public void onResume(LifecycleOwner owner) {

    }

    @Override
    public void onPause(LifecycleOwner owner) {

    }

    @Override
    public void onStop(LifecycleOwner owner) {

    }

    @Override
    public void onDestroy(LifecycleOwner owner) {
        removeCallbacksAndMessages(null);
    }
}
