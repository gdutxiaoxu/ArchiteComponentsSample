package com.xj.architecomponentssample.lifecycle;

import android.app.Activity;
import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.xj.architecomponentssample.R;

public class SimpleLifecycleActivity extends Activity implements LifecycleOwner {

    private static final String TAG = "SimpleLifecycleActivity";
    private LifecycleRegistry mLifecycleRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_lifecycle);
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);
        getLifecycle().addObserver(new GenericLifecycleObserver() {

            @Override
            public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
                Log.d(TAG, "onStateChanged: event =" + event);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

}
