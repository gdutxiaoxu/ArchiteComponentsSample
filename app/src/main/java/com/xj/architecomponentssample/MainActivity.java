package com.xj.architecomponentssample;

import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xj.architecomponentssample.lifecycle.CustomLifecycleActivity;
import com.xj.architecomponentssample.lifecycle.MediaCompoment;
import com.xj.architecomponentssample.lifecycle.SimpleLifecycleActivity;
import com.xj.architecomponentssample.livedata.LiveDataSampleActivity;
import com.xj.architecomponentssample.livedata.NetworkLiveData;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button mBtnFragmentActivity;
    private Button mBtnLivedata;
    private Button mBtnSimpleLifecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        getLifecycle().addObserver(new GenericLifecycleObserver() {

            @Override
            public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
                Log.d(TAG, "onStateChanged: event =" + event);
            }
        });
        new MediaCompoment(this);

        NetworkLiveData.getInstance(this).observe(this, new Observer<NetworkInfo>() {
            @Override
            public void onChanged(@Nullable NetworkInfo networkInfo) {
                Log.d(TAG, "onChanged: networkInfo=" + networkInfo);
            }
        });
    }

    private void initView() {
        mBtnFragmentActivity = findViewById(R.id.btn_fragment_activity);
        mBtnFragmentActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomLifecycleActivity.class));
            }
        });
        mBtnLivedata = findViewById(R.id.btn_livedata);
        mBtnLivedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LiveDataSampleActivity.class));
            }
        });
        mBtnSimpleLifecycle = findViewById(R.id.btn_simple_lifecycle);
        mBtnSimpleLifecycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SimpleLifecycleActivity.class));
            }
        });
    }
}
