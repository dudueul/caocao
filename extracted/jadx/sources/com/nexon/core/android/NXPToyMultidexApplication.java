package com.nexon.core.android;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPToyMultidexApplication extends MultiDexApplication {
    @Override // android.support.multidex.MultiDexApplication, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        NXPApplicationManager.getInstance().onAttachBaseContext(this);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        NXPApplicationManager.getInstance().onCreate(this);
    }
}
