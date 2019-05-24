package com.xj.architecomponentssample.lifecycle;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.xj.architecomponentssample.R;
import com.xj.architecomponentssample.base.LifecycleHandler;

import java.text.SimpleDateFormat;

public class LifecycleHandlerActivity extends AppCompatActivity {

    private LifecycleHandler mLifecycleHandler;
    private TextView mTv;
    private Runnable mRunnable;
    private long mStartTime;
    private SimpleDateFormat mSimpleDateFormat;
    private static final String TAG = "LifecycleHandlerActivit";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_handler);
        mSimpleDateFormat = new SimpleDateFormat("mm:ss");
        mLifecycleHandler = new LifecycleHandler(this);
        mRunnable = new Runnable() {
            @Override
            public void run() {
                long time = SystemClock.elapsedRealtime() - mStartTime;
                String format = mSimpleDateFormat.format(time);
                Log.i(TAG, "run: time =" + time);
                Log.i(TAG, "run: format =" + format);
                mTv.setText(format);
                mLifecycleHandler.postDelayed(this, 1000);

            }
        };
        mStartTime = SystemClock.elapsedRealtime();
        mLifecycleHandler.post(mRunnable);
        initView();
    }

    private void initView() {
        mTv = findViewById(R.id.tv);
    }
}
