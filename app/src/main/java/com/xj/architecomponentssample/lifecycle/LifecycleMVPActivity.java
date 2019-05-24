package com.xj.architecomponentssample.lifecycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xj.architecomponentssample.R;

public class LifecycleMVPActivity extends AppCompatActivity {

    private TestPresenter mTestPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_mvp);
        mTestPresenter = new TestPresenter(this);
    }
}
