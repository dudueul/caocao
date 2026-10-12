package com.nexon.core.android;

import android.app.Application;
import android.content.Context;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPToyApplication extends Application {
    @Override // android.content.ContextWrapper
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
