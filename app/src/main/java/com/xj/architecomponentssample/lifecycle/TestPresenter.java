package com.xj.architecomponentssample.lifecycle;

import android.arch.lifecycle.LifecycleOwner;

import com.xj.architecomponentssample.base.BasePresenter;

public class TestPresenter extends BasePresenter {

    public TestPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }
}
