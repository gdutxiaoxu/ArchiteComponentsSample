package com.xj.architecomponentssample.livedata;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModelProviders;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xj.architecomponentssample.beans.People;
import com.xj.architecomponentssample.R;

public class LiveDataSampleActivity extends AppCompatActivity implements TextFragment.OnFragmentInteractionListener {

    private static final String TAG = "LiveDataSampleActivity";

    private FrameLayout mFlContainer1;
    private FrameLayout mFlContainer2;

    private String mkey = "LiveData";
    public TestViewModel mTestViewModel;
    private TextView mTvName;
    private Button mBtnChangeName;
    private String[] mNames = new String[]{"lufei", "sulong", "shanzhi", "NaMei"};
    private int i = 0;
    private TextFragment mTextFragment1;
    private TextFragment mTextFragment2;
    private MutableLiveData<String> mNameEvent;
    private Observer<String> mForeverObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data_sample);
        initView();

        mTestViewModel = ViewModelProviders.of(this, new TestViewModel.Factory(mkey)).get(TestViewModel.class);
        mNameEvent = mTestViewModel.getNameEvent();
        mNameEvent.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.i(TAG, "onChanged: s = " + s);
                mTvName.setText(s);
            }
        });

        mForeverObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.d(TAG, "onChanged: forver s =" + s);
            }
        };
        mNameEvent.observeForever(mForeverObserver);

        final LiveData<People> peopleLiveData = Transformations.map(mNameEvent, new Function<String, People>() {
            @Override
            public People apply(String input) {
                return new People(input, i);
            }
        });

        LiveData<People> peopleLive = Transformations.switchMap(mNameEvent, new Function<String, LiveData<People>>() {
            @Override
            public LiveData<People> apply(String input) {
                MutableLiveData<People> peopleMutableLiveData = new MutableLiveData<>();
                peopleMutableLiveData.setValue(new People(input, i));
                return peopleLiveData;
            }
        });

        NetworkLiveData.getInstance(this).observe(this, new Observer<NetworkInfo>() {
            @Override
            public void onChanged(@Nullable NetworkInfo networkInfo) {
                Log.d(TAG, "onChanged: networkInfo=" + networkInfo);
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        mNameEvent.setValue("456");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNameEvent.removeObserver(mForeverObserver);
    }

    private void initView() {
        mTvName = findViewById(R.id.tv_name);
        mBtnChangeName = findViewById(R.id.btn_change_name);
        mBtnChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                String name = mNames[i % mNames.length];
                mTestViewModel.getNameEvent().setValue(name);
            }
        });
        mFlContainer1 = findViewById(R.id.fl_container1);
        mFlContainer2 = findViewById(R.id.fl_container2);

        mTextFragment1 = TextFragment.newInstance(mkey);
        mTextFragment2 = TextFragment.newInstance(mkey);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_container1, mTextFragment1);
        fragmentTransaction.replace(R.id.fl_container2, mTextFragment2);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
