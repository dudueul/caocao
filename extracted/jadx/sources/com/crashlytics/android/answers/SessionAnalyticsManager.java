package com.crashlytics.android.answers;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import io.fabric.sdk.android.ActivityLifecycleManager;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.ExecutorUtils;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.network.DefaultHttpRequestFactory;
import io.fabric.sdk.android.services.persistence.FileStoreImpl;
import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class SessionAnalyticsManager implements BackgroundManager.Listener {
    static final String EXECUTOR_SERVICE = "Answers Events Handler";
    static final String ON_CRASH_ERROR_MSG = "onCrash called from main thread!!!";
    final BackgroundManager backgroundManager;
    final AnswersEventsHandler eventsHandler;
    private final long installedAt;
    final ActivityLifecycleManager lifecycleManager;
    final AnswersPreferenceManager preferenceManager;

    SessionAnalyticsManager(AnswersEventsHandler answersEventsHandler, ActivityLifecycleManager activityLifecycleManager, BackgroundManager backgroundManager, AnswersPreferenceManager answersPreferenceManager, long j) {
        this.eventsHandler = answersEventsHandler;
        this.lifecycleManager = activityLifecycleManager;
        this.backgroundManager = backgroundManager;
        this.preferenceManager = answersPreferenceManager;
        this.installedAt = j;
    }

    public static SessionAnalyticsManager build(Kit kit, Context context, IdManager idManager, String str, String str2, long j) {
        SessionMetadataCollector sessionMetadataCollector = new SessionMetadataCollector(context, idManager, str, str2);
        AnswersFilesManagerProvider answersFilesManagerProvider = new AnswersFilesManagerProvider(context, new FileStoreImpl(kit));
        DefaultHttpRequestFactory defaultHttpRequestFactory = new DefaultHttpRequestFactory(Fabric.getLogger());
        ActivityLifecycleManager activityLifecycleManager = new ActivityLifecycleManager(context);
        ScheduledExecutorService scheduledExecutorServiceBuildSingleThreadScheduledExecutorService = ExecutorUtils.buildSingleThreadScheduledExecutorService(EXECUTOR_SERVICE);
        return new SessionAnalyticsManager(new AnswersEventsHandler(kit, context, answersFilesManagerProvider, sessionMetadataCollector, defaultHttpRequestFactory, scheduledExecutorServiceBuildSingleThreadScheduledExecutorService), activityLifecycleManager, new BackgroundManager(scheduledExecutorServiceBuildSingleThreadScheduledExecutorService), AnswersPreferenceManager.build(context), j);
    }

    public void disable() {
        this.lifecycleManager.resetCallbacks();
        this.eventsHandler.disable();
    }

    public void enable() {
        this.eventsHandler.enable();
        this.lifecycleManager.registerCallbacks(new AnswersLifecycleCallbacks(this, this.backgroundManager));
        this.backgroundManager.registerListener(this);
        if (isFirstLaunch()) {
            onInstall(this.installedAt);
            this.preferenceManager.setAnalyticsLaunched();
        }
    }

    boolean isFirstLaunch() {
        return !this.preferenceManager.hasAnalyticsLaunched();
    }

    @Override // com.crashlytics.android.answers.BackgroundManager.Listener
    public void onBackground() {
        Fabric.getLogger().d(Answers.TAG, "Flush events when app is backgrounded");
        this.eventsHandler.flushEvents();
    }

    public void onCrash(String str, String str2) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException(ON_CRASH_ERROR_MSG);
        }
        Fabric.getLogger().d(Answers.TAG, "Logged crash");
        this.eventsHandler.processEventSync(SessionEvent.crashEventBuilder(str, str2));
    }

    public void onCustom(CustomEvent customEvent) {
        Fabric.getLogger().d(Answers.TAG, "Logged custom event: " + customEvent);
        this.eventsHandler.processEventAsync(SessionEvent.customEventBuilder(customEvent));
    }

    public void onError(String str) {
    }

    public void onInstall(long j) {
        Fabric.getLogger().d(Answers.TAG, "Logged install");
        this.eventsHandler.processEventAsyncAndFlush(SessionEvent.installEventBuilder(j));
    }

    public void onLifecycle(Activity activity, SessionEvent.Type type) {
        Fabric.getLogger().d(Answers.TAG, "Logged lifecycle event: " + type.name());
        this.eventsHandler.processEventAsync(SessionEvent.lifecycleEventBuilder(type, activity));
    }

    public void onPredefined(PredefinedEvent predefinedEvent) {
        Fabric.getLogger().d(Answers.TAG, "Logged predefined event: " + predefinedEvent);
        this.eventsHandler.processEventAsync(SessionEvent.predefinedEventBuilder(predefinedEvent));
    }

    public void setAnalyticsSettingsData(AnalyticsSettingsData analyticsSettingsData, String str) {
        this.backgroundManager.setFlushOnBackground(analyticsSettingsData.flushOnBackground);
        this.eventsHandler.setAnalyticsSettingsData(analyticsSettingsData, str);
    }
}
