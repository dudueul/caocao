package com.nexon.core.android;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.nexon.core.log.ToyLog;
import com.nexon.core.preference.NXToyCommonPreferenceController;
import com.nexon.core.requestpostman.NXToyRequestPostman;
import com.nexon.core.session.NXToySessionManager;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPApplicationManager {
    private WeakReference<Application> weakApplication;

    private static class Singleton {
        private static final NXPApplicationManager instance = new NXPApplicationManager();

        private Singleton() {
        }
    }

    private NXPApplicationManager() {
    }

    public static NXPApplicationManager getInstance() {
        return Singleton.instance;
    }

    private void initialize(Application application) {
        Context applicationContext = application.getApplicationContext();
        NXPApplicationConfigManager.getInstance().initialize(applicationContext);
        NXToyCommonPreferenceController.getInstance().initialize(applicationContext);
        NXToySessionManager.getInstance().initialize(applicationContext);
        NXToyRequestPostman.getInstance().initialize(applicationContext);
    }

    private void loadClasses() {
        Iterator it = Arrays.asList("com.nexon.platform.test_support.NXPTestHelper", "kr.co.nexon.npaccount.stats.NXLog", "com.nexon.platform.auth.NXPVKontakte", NXPActivityLifecycleCallbackManager.class.getName()).iterator();
        while (it.hasNext()) {
            try {
                Class.forName((String) it.next());
            } catch (Exception e) {
            }
        }
    }

    @Nullable
    public Application getApplication() {
        ToyLog.dd("NXPApplicationManager.getApplication() " + this.weakApplication);
        if (this.weakApplication == null) {
            return null;
        }
        return this.weakApplication.get();
    }

    @Nullable
    public Context getApplicationContext() {
        Application application = getApplication();
        if (application == null) {
            ToyLog.dd("application is null in getApplicationContext()");
            return null;
        }
        Context applicationContext = application.getApplicationContext();
        if (applicationContext != null) {
            return applicationContext;
        }
        ToyLog.dd("applicationContext is null in getApplicationContext()");
        return applicationContext;
    }

    public void onAttachBaseContext(@NonNull Application application) {
        ToyLog.setupLogcatLogger(application.getPackageManager());
        loadClasses();
    }

    public void onCreate(@NonNull Application application) {
        if (this.weakApplication == null || this.weakApplication.get() != application) {
            if (this.weakApplication != null) {
                this.weakApplication.clear();
            }
            this.weakApplication = new WeakReference<>(application);
        }
        initialize(application);
        NXPApplicationLifeCycleManager.getInstance().onCreateApplication(application);
    }
}
