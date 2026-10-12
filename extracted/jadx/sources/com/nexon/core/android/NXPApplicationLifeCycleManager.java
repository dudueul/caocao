package com.nexon.core.android;

import android.app.Application;
import android.support.annotation.NonNull;
import com.nexon.core.log.ToyLog;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPApplicationLifeCycleManager {
    private List<ApplicationLifecycleCallbacks> callbacks;

    public interface ApplicationLifecycleCallbacks {
        void onCreate(Application application);
    }

    private static class Singleton {
        private static final NXPApplicationLifeCycleManager instance = new NXPApplicationLifeCycleManager();

        private Singleton() {
        }
    }

    private NXPApplicationLifeCycleManager() {
        this.callbacks = new ArrayList();
    }

    public static NXPApplicationLifeCycleManager getInstance() {
        return Singleton.instance;
    }

    public void addApplicationLifecycleCallbacks(@NonNull ApplicationLifecycleCallbacks applicationLifecycleCallbacks) {
        if (this.callbacks.contains(applicationLifecycleCallbacks)) {
            return;
        }
        this.callbacks.add(applicationLifecycleCallbacks);
    }

    void onCreateApplication(Application application) {
        ArrayList<ApplicationLifecycleCallbacks> arrayList = new ArrayList(this.callbacks);
        for (ApplicationLifecycleCallbacks applicationLifecycleCallbacks : arrayList) {
            ToyLog.dd("callback class name : " + applicationLifecycleCallbacks.getClass().getName());
            applicationLifecycleCallbacks.onCreate(application);
        }
        arrayList.clear();
    }

    public void removeApplicationLifeCycleCallbacks(@NonNull ApplicationLifecycleCallbacks applicationLifecycleCallbacks) {
        this.callbacks.remove(applicationLifecycleCallbacks);
    }
}
