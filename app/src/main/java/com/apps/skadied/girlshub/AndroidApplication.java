package com.apps.skadied.girlshub;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tumblr.remember.Remember;

/**
 * Created by Lauren Steven on 9/5/2018.
 */
public class AndroidApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Remember.init(getApplicationContext(), "com.apps.skadied.girlshub");
        Fresco.initialize(this);
    }
}
