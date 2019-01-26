package com.xj.architecomponentssample.livedata;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

/**
 * Created by jun xu on 19-1-22.
 */
public class TestViewModel extends ViewModel {

    private final String mKey;
    private MutableLiveData<String> mNameEvent = new MutableLiveData<>();

    public MutableLiveData<String> getNameEvent() {
        return mNameEvent;
    }

    public TestViewModel(String key) {
        mKey = key;
    }

    public static class Factory implements ViewModelProvider.Factory {
        private String mKey;

        public Factory(String key) {
            mKey = key;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new TestViewModel(mKey);
        }
    }

    public String getKey() {
        return mKey;
    }
}
