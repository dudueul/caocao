package com.facebook.marketing.internal;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import com.facebook.FacebookSdk;
import com.facebook.appevents.codeless.CodelessLoggingEventListener;
import com.facebook.appevents.codeless.internal.ViewHierarchy;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class ButtonIndexingEventListener {
    private static final String TAG = ButtonIndexingEventListener.class.getCanonicalName();

    public static class ButtonIndexingAccessibilityDelegate extends CodelessLoggingEventListener.AutoLoggingAccessibilityDelegate {

        @Nullable
        private View.AccessibilityDelegate existingDelegate;
        private String mapKey;

        public ButtonIndexingAccessibilityDelegate(View view, String str) {
            if (view == null) {
                return;
            }
            this.existingDelegate = ViewHierarchy.getExistingDelegate(view);
            this.mapKey = str;
            this.supportButtonIndexing = true;
        }

        @Override // com.facebook.appevents.codeless.CodelessLoggingEventListener.AutoLoggingAccessibilityDelegate, android.view.View.AccessibilityDelegate
        public void sendAccessibilityEvent(final View view, int i) {
            if (i == -1) {
                Log.e(ButtonIndexingEventListener.TAG, "Unsupported action type");
            }
            if (this.existingDelegate != null && !(this.existingDelegate instanceof ButtonIndexingAccessibilityDelegate)) {
                this.existingDelegate.sendAccessibilityEvent(view, i);
            }
            final String str = this.mapKey;
            FacebookSdk.getExecutor().execute(new Runnable() { // from class: com.facebook.marketing.internal.ButtonIndexingEventListener.ButtonIndexingAccessibilityDelegate.1
                @Override // java.lang.Runnable
                public void run() {
                    ButtonIndexingLogger.logIndexing(FacebookSdk.getApplicationId(), view, str, FacebookSdk.getApplicationContext());
                }
            });
        }
    }

    public static ButtonIndexingAccessibilityDelegate getAccessibilityDelegate(View view, String str) {
        return new ButtonIndexingAccessibilityDelegate(view, str);
    }
}
