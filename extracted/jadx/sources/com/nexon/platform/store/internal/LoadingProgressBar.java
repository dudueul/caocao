package com.nexon.platform.store.internal;

import android.R;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class LoadingProgressBar {
    private static RelativeLayout progressLayout;
    private static ProgressBar progressbar;
    private static Window window;

    /* JADX INFO: Access modifiers changed from: private */
    public static ProgressBar createProgressBar(Activity activity) {
        if (activity == null) {
            return null;
        }
        progressbar = new ProgressBar(activity, null, R.attr.progressBarStyleLarge);
        progressbar.setIndeterminate(false);
        progressLayout = new RelativeLayout(activity);
        progressLayout.setGravity(17);
        progressLayout.addView(progressbar);
        activity.addContentView(progressLayout, new ViewGroup.LayoutParams(-1, -1));
        window = activity.getWindow();
        return progressbar;
    }

    public static void dismiss() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.nexon.platform.store.internal.LoadingProgressBar.2
            @Override // java.lang.Runnable
            public void run() {
                if (LoadingProgressBar.progressbar == null) {
                    return;
                }
                LoadingProgressBar.progressbar.setVisibility(8);
                LoadingProgressBar.progressLayout.removeView(LoadingProgressBar.progressbar);
                ((ViewManager) LoadingProgressBar.progressLayout.getParent()).removeView(LoadingProgressBar.progressLayout);
                LoadingProgressBar.window.clearFlags(16);
                Window unused = LoadingProgressBar.window = null;
                ProgressBar unused2 = LoadingProgressBar.progressbar = null;
                RelativeLayout unused3 = LoadingProgressBar.progressLayout = null;
            }
        });
    }

    public static void show(final Activity activity) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.nexon.platform.store.internal.LoadingProgressBar.1
            @Override // java.lang.Runnable
            public void run() {
                if (LoadingProgressBar.progressbar == null) {
                    synchronized (LoadingProgressBar.class) {
                        if (LoadingProgressBar.progressbar == null) {
                            ProgressBar unused = LoadingProgressBar.progressbar = LoadingProgressBar.createProgressBar(activity);
                            if (LoadingProgressBar.progressbar == null) {
                                return;
                            }
                        }
                    }
                }
                LoadingProgressBar.window.addFlags(16);
                LoadingProgressBar.progressbar.setVisibility(0);
            }
        });
    }
}
