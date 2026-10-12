package com.nexon.caocao;

import android.content.Context;
import android.support.multidex.MultiDex;
import com.nexon.core.android.NXPToyMultidexApplication;
import io.fabric.unity.android.FabricInitializer;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class CaoCaoUnityApplication extends NXPToyMultidexApplication {
    @Override // com.nexon.core.android.NXPToyMultidexApplication, android.support.multidex.MultiDexApplication, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
        FabricInitializer.initializeFabric(this, FabricInitializer.Caller.Android);
    }

    @Override // com.nexon.core.android.NXPToyMultidexApplication, android.app.Application
    public void onCreate() {
        super.onCreate();
    }
}
