package com.xj.architecomponentssample.lifecycle;

import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.util.Log;

/**
 * Created by jun xu on 19-1-21.
 */
public class MediaCompoment {
    private static final String TAG = "MediaCompoment";

    private final LifecycleOwner mLifecycleOwner;

    public MediaCompoment(LifecycleOwner lifecycleOwner) {
        mLifecycleOwner = lifecycleOwner;
        mLifecycleOwner.getLifecycle().addObserver(new GenericLifecycleObserver() {
            @Override
            public void onStateChanged(LifecycleOwner source, final Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_CREATE) {
                    onCreate();
                } else if (event == Lifecycle.Event.ON_START) {
                    onStart();
                } else if (event == Lifecycle.Event.ON_RESUME) {
                    onResume();
                } else if (event == Lifecycle.Event.ON_PAUSE) {
                    onPause();
                } else if (event == Lifecycle.Event.ON_STOP) {
                    onStop();
                } else if (event == Lifecycle.Event.ON_DESTROY) {
                    onDestroy();
                }
            }
        });
    }

    public void onCreate() {
        Log.d(TAG, "onCreate:");
    }

    public void onStart() {
        Log.d(TAG, "onStart:");
    }

    public void onResume() {
        Log.d(TAG, "onResume:");
    }

    public void onPause() {
        Log.d(TAG, "onPause:");
    }

    public void onStop() {
        Log.d(TAG, "onStop:");
    }

    public void onDestroy() {
        Log.d(TAG, "onDestroy:");
    }
}
