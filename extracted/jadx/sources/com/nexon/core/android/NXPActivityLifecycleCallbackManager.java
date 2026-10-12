package com.nexon.core.android;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.nexon.core.log.ToyLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXPActivityLifecycleCallbackManager {
    private static NXPApplicationLifeCycleManager.ApplicationLifecycleCallbacks applicationLifecycleCallback = new NXPApplicationLifeCycleManager.ApplicationLifecycleCallbacks() { // from class: com.nexon.core.android.NXPActivityLifecycleCallbackManager.1
        @Override // com.nexon.core.android.NXPApplicationLifeCycleManager.ApplicationLifecycleCallbacks
        public void onCreate(Application application) {
            ToyLog.dd("onCreate(Application) in NXPActivityLifecycleCallbackManager. application:" + application);
            NXPActivityLifecycleCallbackManager.getInstance().registerToApplication(application);
        }
    };
    private static NXPActivityLifecycleCallbackManager instance;
    private List<Application.ActivityLifecycleCallbacks> activityLifecycleObserverList = new ArrayList();
    private Application.ActivityLifecycleCallbacks lifeCycleCallbacks;

    static {
        NXPApplicationLifeCycleManager.getInstance().addApplicationLifecycleCallbacks(applicationLifecycleCallback);
    }

    private NXPActivityLifecycleCallbackManager() {
    }

    public static NXPActivityLifecycleCallbackManager getInstance() {
        if (instance == null) {
            synchronized (NXPActivityLifecycleCallbackManager.class) {
                if (instance == null) {
                    instance = new NXPActivityLifecycleCallbackManager();
                }
            }
        }
        return instance;
    }

    public void addActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        if (this.activityLifecycleObserverList.contains(activityLifecycleCallbacks)) {
            ToyLog.d("Already exist. callback:" + activityLifecycleCallbacks);
        } else {
            this.activityLifecycleObserverList.add(activityLifecycleCallbacks);
        }
    }

    public void registerToApplication(@NonNull Application application) {
        if (application == null) {
            ToyLog.d("In registerToApplication, application param is null.");
        } else {
            if (this.lifeCycleCallbacks != null) {
                ToyLog.d("lifecycleCallback is already added");
                return;
            }
            this.lifeCycleCallbacks = new Application.ActivityLifecycleCallbacks() { // from class: com.nexon.core.android.NXPActivityLifecycleCallbackManager.2
                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    ToyLog.d("onActivityCreated. activity:" + activity);
                    Iterator it = NXPActivityLifecycleCallbackManager.this.activityLifecycleObserverList.iterator();
                    while (it.hasNext()) {
                        ((Application.ActivityLifecycleCallbacks) it.next()).onActivityCreated(activity, bundle);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityDestroyed(Activity activity) {
                    ToyLog.d("onActivityDestroyed activity:" + activity);
                    Iterator it = NXPActivityLifecycleCallbackManager.this.activityLifecycleObserverList.iterator();
                    while (it.hasNext()) {
                        ((Application.ActivityLifecycleCallbacks) it.next()).onActivityDestroyed(activity);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(Activity activity) {
                    ToyLog.d("onActivityPaused activity:" + activity);
                    Iterator it = NXPActivityLifecycleCallbackManager.this.activityLifecycleObserverList.iterator();
                    while (it.hasNext()) {
                        ((Application.ActivityLifecycleCallbacks) it.next()).onActivityPaused(activity);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(Activity activity) {
                    ToyLog.d("onActivityResumed activity:" + activity);
                    Iterator it = NXPActivityLifecycleCallbackManager.this.activityLifecycleObserverList.iterator();
                    while (it.hasNext()) {
                        ((Application.ActivityLifecycleCallbacks) it.next()).onActivityResumed(activity);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                    ToyLog.d("onActivitySaveInstanceState activity:" + activity);
                    Iterator it = NXPActivityLifecycleCallbackManager.this.activityLifecycleObserverList.iterator();
                    while (it.hasNext()) {
                        ((Application.ActivityLifecycleCallbacks) it.next()).onActivitySaveInstanceState(activity, bundle);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStarted(Activity activity) {
                    ToyLog.d("onActivityStarted activity:" + activity);
                    Iterator it = NXPActivityLifecycleCallbackManager.this.activityLifecycleObserverList.iterator();
                    while (it.hasNext()) {
                        ((Application.ActivityLifecycleCallbacks) it.next()).onActivityStarted(activity);
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStopped(Activity activity) {
                    ToyLog.d("onActivityStopped activity:" + activity);
                    Iterator it = NXPActivityLifecycleCallbackManager.this.activityLifecycleObserverList.iterator();
                    while (it.hasNext()) {
                        ((Application.ActivityLifecycleCallbacks) it.next()).onActivityStopped(activity);
                    }
                }
            };
            ToyLog.d("register ActivityLifeCycle to application");
            application.registerActivityLifecycleCallbacks(this.lifeCycleCallbacks);
        }
    }

    public void removeActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.activityLifecycleObserverList.remove(activityLifecycleCallbacks);
    }
}
