package com.facebook.marketing;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import com.facebook.marketing.internal.ButtonIndexer;
import com.facebook.marketing.internal.Constants;
import com.facebook.marketing.internal.MarketingLogger;
import com.facebook.marketing.internal.MarketingUtils;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.Locale;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class CodelessActivityLifecycleTracker {
    private static SensorManager sensorManager;
    private static ViewIndexer viewIndexer;
    private static final String TAG = CodelessActivityLifecycleTracker.class.getCanonicalName();
    private static final ViewIndexingTrigger viewIndexingTrigger = new ViewIndexingTrigger();
    private static String deviceSessionID = null;
    private static Boolean isAppIndexingEnabled = false;
    private static volatile Boolean isCheckingSession = false;
    private static final ButtonIndexer buttonIndexer = new ButtonIndexer();

    public static void checkCodelessSession(final String str, final MarketingLogger marketingLogger) {
        if (isCheckingSession.booleanValue()) {
            return;
        }
        isCheckingSession = true;
        FacebookSdk.getExecutor().execute(new Runnable() { // from class: com.facebook.marketing.CodelessActivityLifecycleTracker.2
            @Override // java.lang.Runnable
            public void run() {
                GraphRequest graphRequestNewPostRequest = GraphRequest.newPostRequest(null, String.format(Locale.US, "%s/app_indexing_session", str), null, null);
                Bundle parameters = graphRequestNewPostRequest.getParameters();
                if (parameters == null) {
                    parameters = new Bundle();
                }
                AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(FacebookSdk.getApplicationContext());
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(Build.MODEL != null ? Build.MODEL : "");
                if (attributionIdentifiers == null || attributionIdentifiers.getAndroidAdvertiserId() == null) {
                    jSONArray.put("");
                } else {
                    jSONArray.put(attributionIdentifiers.getAndroidAdvertiserId());
                }
                jSONArray.put("0");
                jSONArray.put(MarketingUtils.isEmulator() ? "1" : "0");
                Locale currentLocale = Utility.getCurrentLocale();
                jSONArray.put(currentLocale.getLanguage() + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + currentLocale.getCountry());
                String string = jSONArray.toString();
                parameters.putString(Constants.DEVICE_SESSION_ID, CodelessActivityLifecycleTracker.getCurrentDeviceSessionID());
                parameters.putString(Constants.EXTINFO, string);
                graphRequestNewPostRequest.setParameters(parameters);
                if (graphRequestNewPostRequest != null) {
                    JSONObject jSONObject = graphRequestNewPostRequest.executeAndWait().getJSONObject();
                    Boolean unused = CodelessActivityLifecycleTracker.isAppIndexingEnabled = Boolean.valueOf(jSONObject != null && jSONObject.optBoolean(Constants.APP_INDEXING_ENABLED, false));
                    if (CodelessActivityLifecycleTracker.isAppIndexingEnabled.booleanValue()) {
                        marketingLogger.logSessionReady();
                        CodelessActivityLifecycleTracker.viewIndexer.schedule();
                    } else {
                        String unused2 = CodelessActivityLifecycleTracker.deviceSessionID = null;
                    }
                }
                Boolean unused3 = CodelessActivityLifecycleTracker.isCheckingSession = false;
            }
        });
    }

    public static String getCurrentDeviceSessionID() {
        if (deviceSessionID == null) {
            deviceSessionID = UUID.randomUUID().toString();
        }
        return deviceSessionID;
    }

    public static boolean getIsAppIndexingEnabled() {
        return isAppIndexingEnabled.booleanValue();
    }

    public static void startTracking(Application application, String str) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.facebook.marketing.CodelessActivityLifecycleTracker.1
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                CodelessActivityLifecycleTracker.buttonIndexer.remove(activity);
                if (CodelessActivityLifecycleTracker.viewIndexer != null) {
                    CodelessActivityLifecycleTracker.viewIndexer.unschedule();
                }
                if (CodelessActivityLifecycleTracker.sensorManager != null) {
                    CodelessActivityLifecycleTracker.sensorManager.unregisterListener(CodelessActivityLifecycleTracker.viewIndexingTrigger);
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                CodelessActivityLifecycleTracker.buttonIndexer.add(activity);
                final Context applicationContext = activity.getApplicationContext();
                final String applicationId = FacebookSdk.getApplicationId();
                final FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(applicationId);
                if (appSettingsWithoutQuery == null || !appSettingsWithoutQuery.getCodelessSetupEnabled()) {
                    return;
                }
                SensorManager unused = CodelessActivityLifecycleTracker.sensorManager = (SensorManager) applicationContext.getSystemService("sensor");
                Sensor defaultSensor = CodelessActivityLifecycleTracker.sensorManager.getDefaultSensor(1);
                ViewIndexer unused2 = CodelessActivityLifecycleTracker.viewIndexer = new ViewIndexer(activity);
                CodelessActivityLifecycleTracker.viewIndexingTrigger.setOnShakeListener(new ViewIndexingTrigger.OnShakeListener() { // from class: com.facebook.marketing.CodelessActivityLifecycleTracker.1.1
                    @Override // com.facebook.marketing.ViewIndexingTrigger.OnShakeListener
                    public void onShake() {
                        MarketingLogger marketingLogger = new MarketingLogger(applicationContext, applicationId);
                        marketingLogger.logGestureTriggered();
                        if (appSettingsWithoutQuery == null || !appSettingsWithoutQuery.getCodelessEventsEnabled()) {
                            return;
                        }
                        CodelessActivityLifecycleTracker.checkCodelessSession(applicationId, marketingLogger);
                    }
                });
                CodelessActivityLifecycleTracker.sensorManager.registerListener(CodelessActivityLifecycleTracker.viewIndexingTrigger, defaultSensor, 2);
                if (appSettingsWithoutQuery == null || !appSettingsWithoutQuery.getCodelessEventsEnabled()) {
                    return;
                }
                CodelessActivityLifecycleTracker.viewIndexer.schedule();
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
            }
        });
    }

    public static void updateAppIndexing(Boolean bool) {
        isAppIndexingEnabled = bool;
    }
}
