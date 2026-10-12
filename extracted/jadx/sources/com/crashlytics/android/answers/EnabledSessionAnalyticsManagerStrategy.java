package com.crashlytics.android.answers;

import android.content.Context;
import com.tapjoy.TJAdUnitConstants;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.services.common.ApiKey;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.events.FilesSender;
import io.fabric.sdk.android.services.events.TimeBasedFileRollOverRunnable;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.settings.AnalyticsSettingsData;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class EnabledSessionAnalyticsManagerStrategy implements SessionAnalyticsManagerStrategy {
    static final int UNDEFINED_ROLLOVER_INTERVAL_SECONDS = -1;
    private final Context context;
    private final ScheduledExecutorService executorService;
    private final SessionAnalyticsFilesManager filesManager;
    FilesSender filesSender;
    private final HttpRequestFactory httpRequestFactory;
    private final Kit kit;
    final SessionEventMetadata metadata;
    private final AtomicReference<ScheduledFuture<?>> rolloverFutureRef = new AtomicReference<>();
    ApiKey apiKey = new ApiKey();
    EventFilter eventFilter = new KeepAllEventFilter();
    boolean customEventsEnabled = true;
    boolean predefinedEventsEnabled = true;
    volatile int rolloverIntervalSeconds = -1;

    public EnabledSessionAnalyticsManagerStrategy(Kit kit, Context context, ScheduledExecutorService scheduledExecutorService, SessionAnalyticsFilesManager sessionAnalyticsFilesManager, HttpRequestFactory httpRequestFactory, SessionEventMetadata sessionEventMetadata) {
        this.kit = kit;
        this.context = context;
        this.executorService = scheduledExecutorService;
        this.filesManager = sessionAnalyticsFilesManager;
        this.httpRequestFactory = httpRequestFactory;
        this.metadata = sessionEventMetadata;
    }

    @Override // io.fabric.sdk.android.services.events.FileRollOverManager
    public void cancelTimeBasedFileRollOver() {
        if (this.rolloverFutureRef.get() != null) {
            CommonUtils.logControlled(this.context, "Cancelling time-based rollover because no events are currently being generated.");
            this.rolloverFutureRef.get().cancel(false);
            this.rolloverFutureRef.set(null);
        }
    }

    @Override // com.crashlytics.android.answers.SessionAnalyticsManagerStrategy
    public void deleteAllEvents() {
        this.filesManager.deleteAllEventsFiles();
    }

    @Override // com.crashlytics.android.answers.SessionAnalyticsManagerStrategy
    public void processEvent(SessionEvent.Builder builder) {
        SessionEvent sessionEventBuild = builder.build(this.metadata);
        if (!this.customEventsEnabled && SessionEvent.Type.CUSTOM.equals(sessionEventBuild.type)) {
            Fabric.getLogger().d(Answers.TAG, "Custom events tracking disabled - skipping event: " + sessionEventBuild);
            return;
        }
        if (!this.predefinedEventsEnabled && SessionEvent.Type.PREDEFINED.equals(sessionEventBuild.type)) {
            Fabric.getLogger().d(Answers.TAG, "Predefined events tracking disabled - skipping event: " + sessionEventBuild);
            return;
        }
        if (this.eventFilter.skipEvent(sessionEventBuild)) {
            Fabric.getLogger().d(Answers.TAG, "Skipping filtered event: " + sessionEventBuild);
            return;
        }
        try {
            this.filesManager.writeEvent(sessionEventBuild);
        } catch (IOException e) {
            Fabric.getLogger().e(Answers.TAG, "Failed to write event: " + sessionEventBuild, e);
        }
        scheduleTimeBasedRollOverIfNeeded();
    }

    @Override // io.fabric.sdk.android.services.events.FileRollOverManager
    public boolean rollFileOver() {
        try {
            return this.filesManager.rollFileOver();
        } catch (IOException e) {
            CommonUtils.logControlledError(this.context, "Failed to roll file over.", e);
            return false;
        }
    }

    void scheduleTimeBasedFileRollOver(long j, long j2) {
        if (this.rolloverFutureRef.get() == null) {
            TimeBasedFileRollOverRunnable timeBasedFileRollOverRunnable = new TimeBasedFileRollOverRunnable(this.context, this);
            CommonUtils.logControlled(this.context, "Scheduling time based file roll over every " + j2 + " seconds");
            try {
                this.rolloverFutureRef.set(this.executorService.scheduleAtFixedRate(timeBasedFileRollOverRunnable, j, j2, TimeUnit.SECONDS));
            } catch (RejectedExecutionException e) {
                CommonUtils.logControlledError(this.context, "Failed to schedule time based file roll over", e);
            }
        }
    }

    @Override // io.fabric.sdk.android.services.events.FileRollOverManager
    public void scheduleTimeBasedRollOverIfNeeded() {
        if (this.rolloverIntervalSeconds != -1) {
            scheduleTimeBasedFileRollOver(this.rolloverIntervalSeconds, this.rolloverIntervalSeconds);
        }
    }

    @Override // com.crashlytics.android.answers.SessionAnalyticsManagerStrategy
    public void sendEvents() {
        if (this.filesSender == null) {
            CommonUtils.logControlled(this.context, "skipping files send because we don't yet know the target endpoint");
            return;
        }
        CommonUtils.logControlled(this.context, "Sending all files");
        int size = 0;
        List<File> batchOfFilesToSend = this.filesManager.getBatchOfFilesToSend();
        while (batchOfFilesToSend.size() > 0) {
            try {
                CommonUtils.logControlled(this.context, String.format(Locale.US, "attempt to send batch of %d files", Integer.valueOf(batchOfFilesToSend.size())));
                boolean zSend = this.filesSender.send(batchOfFilesToSend);
                if (zSend) {
                    size += batchOfFilesToSend.size();
                    this.filesManager.deleteSentFiles(batchOfFilesToSend);
                }
                if (!zSend) {
                    break;
                } else {
                    batchOfFilesToSend = this.filesManager.getBatchOfFilesToSend();
                }
            } catch (Exception e) {
                CommonUtils.logControlledError(this.context, "Failed to send batch of analytics files to server: " + e.getMessage(), e);
            }
        }
        if (size == 0) {
            this.filesManager.deleteOldestInRollOverIfOverMax();
        }
    }

    @Override // com.crashlytics.android.answers.SessionAnalyticsManagerStrategy
    public void setAnalyticsSettingsData(AnalyticsSettingsData analyticsSettingsData, String str) {
        this.filesSender = AnswersRetryFilesSender.build(new SessionAnalyticsFilesSender(this.kit, str, analyticsSettingsData.analyticsURL, this.httpRequestFactory, this.apiKey.getValue(this.context)));
        this.filesManager.setAnalyticsSettingsData(analyticsSettingsData);
        this.customEventsEnabled = analyticsSettingsData.trackCustomEvents;
        Fabric.getLogger().d(Answers.TAG, "Custom event tracking " + (this.customEventsEnabled ? TJAdUnitConstants.String.ENABLED : "disabled"));
        this.predefinedEventsEnabled = analyticsSettingsData.trackPredefinedEvents;
        Fabric.getLogger().d(Answers.TAG, "Predefined event tracking " + (this.predefinedEventsEnabled ? TJAdUnitConstants.String.ENABLED : "disabled"));
        if (analyticsSettingsData.samplingRate > 1) {
            Fabric.getLogger().d(Answers.TAG, "Event sampling enabled");
            this.eventFilter = new SamplingEventFilter(analyticsSettingsData.samplingRate);
        }
        this.rolloverIntervalSeconds = analyticsSettingsData.flushIntervalSeconds;
        scheduleTimeBasedFileRollOver(0L, this.rolloverIntervalSeconds);
    }
}
