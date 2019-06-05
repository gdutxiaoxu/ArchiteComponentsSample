package com.xj.architecomponentssample.livedata;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xj.architecomponentssample.R;
import com.xj.architecomponentssample.beans.User;

import java.util.ArrayList;
import java.util.List;

public class LiveDataTransfromActivity extends AppCompatActivity {

    private static final String TAG = "LiveDataTransfromActivi";
    private Button mBtnChangeUser;
    private Button mBtnChangeUserName;
    public long mUid;
    private MutableLiveData<User> mUserLiveData;
    private LiveData<String> mUserNameLiveData;
    private TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data_transfrom);
        initView();
        mUserLiveData = new MutableLiveData<>();
        mUserLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                Log.i(TAG, "onChanged: user=" + user);
                appendText("user liveData change", user);
            }
        });

        mUserNameLiveData = Transformations.map(mUserLiveData, new Function<User, String>() {
            @Override
            public String apply(User input) {
                return input.userName;
            }
        });

        mUserNameLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.i(TAG, "onChanged: s = " + s);
                appendText("userNameLiveData change", s);
            }
        });

        LiveData<List<String>> listLiveData = Transformations.switchMap(mUserLiveData, new Function<User, LiveData<List<String>>>() {
            @Override
            public LiveData<List<String>> apply(User input) {
                MutableLiveData<List<String>> listMutableLiveData = new MutableLiveData<>();
                List<String> list = new ArrayList<>();
                list.add(input.userName);
                list.add(input.uid);
                listMutableLiveData.setValue(list);
                return listMutableLiveData;
            }
        });
        listLiveData.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                Log.i(TAG, "onChanged: strings=" + strings);
                appendText("listLiveData change", strings);
            }
        });


    }

    private void appendText(String preText, Object object) {
        CharSequence text = mTvContent.getText();
        StringBuilder builder = new StringBuilder();
        builder.append(text).append(preText).append(":").append(object).append("\n");
        String result = builder.toString();
        Log.i(TAG, "appendText: result=" + result);
        boolean isMain = Looper.getMainLooper() == Looper.myLooper();
        Log.i(TAG, "appendText: isMain=" + isMain);
        mTvContent.setText(result);
    }

    private void initView() {
        mBtnChangeUser = findViewById(R.id.btn_change_user);
        mBtnChangeUserName = findViewById(R.id.btn_change_user_name);

        mBtnChangeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvContent.setText("");
                mUid++;
                User user = new User(getUserName(), String.valueOf(mUid));
                mUserLiveData.setValue(user);
            }
        });

        mBtnChangeUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mTvContent = findViewById(R.id.tv_content);
    }

    @NonNull
    private String getUserName() {
        return "xujun_" + mUid;
    }
}
