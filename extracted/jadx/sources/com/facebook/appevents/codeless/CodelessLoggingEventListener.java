package com.facebook.appevents.codeless;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.codeless.internal.Constants;
import com.facebook.appevents.codeless.internal.EventBinding;
import com.facebook.appevents.codeless.internal.ViewHierarchy;
import com.facebook.appevents.internal.AppEventUtility;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class CodelessLoggingEventListener {
    private static final String TAG = CodelessLoggingEventListener.class.getCanonicalName();

    public static class AutoLoggingAccessibilityDelegate extends View.AccessibilityDelegate {
        private int accessibilityEventType;
        private View.AccessibilityDelegate existingDelegate;
        private WeakReference<View> hostView;
        private EventBinding mapping;
        private WeakReference<View> rootView;
        protected boolean supportButtonIndexing;
        private boolean supportCodelessLogging;

        public AutoLoggingAccessibilityDelegate() {
            this.supportCodelessLogging = false;
            this.supportButtonIndexing = false;
        }

        public AutoLoggingAccessibilityDelegate(EventBinding eventBinding, View view, View view2) {
            this.supportCodelessLogging = false;
            this.supportButtonIndexing = false;
            if (eventBinding == null || view == null || view2 == null) {
                return;
            }
            this.existingDelegate = ViewHierarchy.getExistingDelegate(view2);
            this.mapping = eventBinding;
            this.hostView = new WeakReference<>(view2);
            this.rootView = new WeakReference<>(view);
            EventBinding.ActionType type = eventBinding.getType();
            switch (eventBinding.getType()) {
                case CLICK:
                    this.accessibilityEventType = 1;
                    break;
                case SELECTED:
                    this.accessibilityEventType = 4;
                    break;
                case TEXT_CHANGED:
                    this.accessibilityEventType = 16;
                    break;
                default:
                    throw new FacebookException("Unsupported action type: " + type.toString());
            }
            this.supportCodelessLogging = true;
        }

        private void logEvent() {
            final String eventName = this.mapping.getEventName();
            final Bundle parameters = CodelessMatcher.getParameters(this.mapping, this.rootView.get(), this.hostView.get());
            if (parameters.containsKey(AppEventsConstants.EVENT_PARAM_VALUE_TO_SUM)) {
                parameters.putDouble(AppEventsConstants.EVENT_PARAM_VALUE_TO_SUM, AppEventUtility.normalizePrice(parameters.getString(AppEventsConstants.EVENT_PARAM_VALUE_TO_SUM)));
            }
            parameters.putString(Constants.IS_CODELESS_EVENT_KEY, "1");
            FacebookSdk.getExecutor().execute(new Runnable() { // from class: com.facebook.appevents.codeless.CodelessLoggingEventListener.AutoLoggingAccessibilityDelegate.1
                @Override // java.lang.Runnable
                public void run() {
                    AppEventsLogger.newLogger(FacebookSdk.getApplicationContext()).logEvent(eventName, parameters);
                }
            });
        }

        public boolean getSupportButtonIndexing() {
            return this.supportButtonIndexing;
        }

        public boolean getSupportCodelessLogging() {
            return this.supportCodelessLogging;
        }

        @Override // android.view.View.AccessibilityDelegate
        public void sendAccessibilityEvent(View view, int i) {
            if (i == -1) {
                Log.e(CodelessLoggingEventListener.TAG, "Unsupported action type");
            }
            if (i != this.accessibilityEventType) {
                return;
            }
            if (this.existingDelegate != null && !(this.existingDelegate instanceof AutoLoggingAccessibilityDelegate)) {
                this.existingDelegate.sendAccessibilityEvent(view, i);
            }
            logEvent();
        }
    }

    public static AutoLoggingAccessibilityDelegate getAccessibilityDelegate(EventBinding eventBinding, View view, View view2) {
        return new AutoLoggingAccessibilityDelegate(eventBinding, view, view2);
    }
}
