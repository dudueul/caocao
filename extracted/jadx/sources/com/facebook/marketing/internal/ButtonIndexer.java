package com.facebook.marketing.internal;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.codeless.CodelessLoggingEventListener;
import com.facebook.appevents.codeless.internal.ViewHierarchy;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ButtonIndexer {
    private static final String TAG = ButtonIndexer.class.getCanonicalName();
    private final Handler uiThreadHandler = new Handler(Looper.getMainLooper());
    private Set<Activity> activitiesSet = new HashSet();
    private Set<ViewProcessor> viewProcessors = new HashSet();
    private HashSet<String> delegateSet = new HashSet<>();

    protected static class ViewProcessor implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener, Runnable {
        private final String activityName;
        private HashSet<String> delegateSet;
        private final Handler handler;
        private WeakReference<View> rootView;
        private HashMap<String, WeakReference<View>> viewMap = new HashMap<>();

        public ViewProcessor(View view, String str, HashSet<String> hashSet, Handler handler) {
            this.rootView = new WeakReference<>(view);
            this.handler = handler;
            this.activityName = str;
            this.delegateSet = hashSet;
            this.handler.postDelayed(this, 200L);
        }

        private void attachListener(View view, String str) {
            if (view == null) {
                return;
            }
            try {
                View.AccessibilityDelegate existingDelegate = ViewHierarchy.getExistingDelegate(view);
                boolean z = existingDelegate != null;
                boolean z2 = z && (existingDelegate instanceof CodelessLoggingEventListener.AutoLoggingAccessibilityDelegate);
                boolean z3 = z2 && ((CodelessLoggingEventListener.AutoLoggingAccessibilityDelegate) existingDelegate).getSupportButtonIndexing();
                if (this.delegateSet.contains(str)) {
                    return;
                }
                if (z && z2 && z3) {
                    return;
                }
                view.setAccessibilityDelegate(ButtonIndexingEventListener.getAccessibilityDelegate(view, str));
                this.delegateSet.add(str);
            } catch (FacebookException e) {
                Log.e(ButtonIndexer.TAG, "Failed to attach auto logging event listener.", e);
            }
        }

        private void process() {
            View view = this.rootView.get();
            if (view != null) {
                attachListeners(view);
            }
        }

        public void attachListeners(View view) {
            getClickableElementsOfView(view, -1, this.activityName);
            FacebookSdk.getExecutor().execute(new Runnable() { // from class: com.facebook.marketing.internal.ButtonIndexer.ViewProcessor.1
                @Override // java.lang.Runnable
                public void run() {
                    ButtonIndexingLogger.logAllIndexing(FacebookSdk.getApplicationId(), ViewProcessor.this.viewMap, FacebookSdk.getApplicationContext());
                }
            });
            for (Map.Entry<String, WeakReference<View>> entry : this.viewMap.entrySet()) {
                attachListener(entry.getValue().get(), entry.getKey());
            }
        }

        public void getClickableElementsOfView(View view, int i, String str) {
            String str2 = str + "." + String.valueOf(i);
            if (view == null) {
                return;
            }
            if (ViewHierarchy.isClickableView(view)) {
                this.viewMap.put(str2, new WeakReference<>(view));
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    getClickableElementsOfView(viewGroup.getChildAt(i2), i2, str2);
                }
            }
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            process();
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            process();
        }

        @Override // java.lang.Runnable
        public void run() {
            RemoteConfig remoteConfigWithoutQuery = RemoteConfigManager.getRemoteConfigWithoutQuery(FacebookSdk.getApplicationId());
            if (remoteConfigWithoutQuery == null || !remoteConfigWithoutQuery.getEnableButtonIndexing()) {
                return;
            }
            process();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processViews() {
        for (Activity activity : this.activitiesSet) {
            this.viewProcessors.add(new ViewProcessor(activity.getWindow().getDecorView().getRootView(), activity.getClass().getSimpleName(), this.delegateSet, this.uiThreadHandler));
        }
    }

    private void startTracking() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            processViews();
        } else {
            this.uiThreadHandler.post(new Runnable() { // from class: com.facebook.marketing.internal.ButtonIndexer.1
                @Override // java.lang.Runnable
                public void run() {
                    ButtonIndexer.this.processViews();
                }
            });
        }
    }

    public void add(Activity activity) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new FacebookException("Can't add activity to ButtonIndexer on non-UI thread");
        }
        this.activitiesSet.add(activity);
        this.delegateSet.clear();
        startTracking();
    }

    public void remove(Activity activity) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new FacebookException("Can't remove activity from ButtonIndexer on non-UI thread");
        }
        this.activitiesSet.remove(activity);
        this.viewProcessors.clear();
        this.delegateSet.clear();
    }
}
