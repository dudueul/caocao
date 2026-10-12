package com.nexon.nexonanalyticssdk;

import android.content.Context;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class NxScheduler {
    private static final int CONFIGURATION = 3;
    private static final int DISTPATCHER = 1;
    private static final int SNAPSHOT = 2;
    private static final int TIMESYNC = 4;
    private Future configFuture;
    private boolean isActiveCounter;
    private Future senderFuture;
    private Future snapshotFuture;
    private NxSnapshotWorker snapshotWorker;
    private NxStateCallback stateCallback;
    private Future summaryFuture;
    private Future timeSyncFuture;
    private int sendCounter = 100;
    private boolean logSend = true;
    private int senderPeriod = 3;
    private TimeUnit sendTimeType = TimeUnit.SECONDS;
    private int configPeriod = 10;
    private TimeUnit configTimeType = TimeUnit.MINUTES;
    private int snapshotPeriod = 10;
    private TimeUnit snapshotTimeType = TimeUnit.MINUTES;
    private int summaryPeriod = 3;
    private TimeUnit summaryTimeType = TimeUnit.MINUTES;
    private NxTimeSyncCallback onTimeSyncReceiver = new NxTimeSyncCallback() { // from class: com.nexon.nexonanalyticssdk.NxScheduler.1
        @Override // com.nexon.nexonanalyticssdk.NxTimeSyncCallback
        public void setUpTimeSyncInfo(String str) {
            JsonObject asJsonObject = new JsonParser().parse(str).getAsJsonObject();
            NxScheduler.this.logSend = asJsonObject.get("nxlog-flag").getAsString().equals("true");
            String asString = asJsonObject.get("ip-address").getAsString();
            String asString2 = asJsonObject.get("log-server").getAsString();
            String asString3 = asJsonObject.get("time").getAsString();
            NxLogcat.i("Log send : " + NxScheduler.this.logSend + " , ipAddress : " + asString + " , logServerDomain : " + asString2 + " , time : " + asString3);
            NxLogInfo nxLogInfo = NxLogInfo.getInstance();
            nxLogInfo.setIpAdress(asString);
            NxLogSendWorker unused = NxScheduler.this.logSendWorker;
            NxLogSendWorker.setDomain(asString2);
            NxDatabase.getInstance().updateBaseTimeAndTimeSync(NxUtils.convertToLongSyncTime(asString3), 1, nxLogInfo.getCurrentLogKey());
            NxScheduler.this.stateCallback.setCurrentState(NxScheduler.this.logSend ? 1 : 0);
        }
    };
    public NxConfigurationSetupCallback onConfigReceiver = new NxConfigurationSetupCallback() { // from class: com.nexon.nexonanalyticssdk.NxScheduler.2
        @Override // com.nexon.nexonanalyticssdk.NxConfigurationSetupCallback
        public void setupConfigurationInfo(String str) {
            JsonObject asJsonObject = new JsonParser().parse(str).getAsJsonObject();
            JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("log-server");
            String asString = asJsonObject2.get("default-protocol").getAsString();
            String asString2 = asJsonObject2.getAsJsonArray("address-list").get(0).getAsString();
            NxLogcat.d("Log Server protocol : " + asString);
            NxLogcat.d("Log Server address :" + asString2);
            JsonObject asJsonObject3 = asJsonObject.getAsJsonObject("config-server");
            String asString3 = asJsonObject3.get("default-protocol").getAsString();
            String asString4 = asJsonObject3.getAsJsonArray("address-list").get(0).getAsString();
            NxLogcat.d("Config protocol :" + asString3);
            NxLogcat.d("Config address : " + asString4);
            JsonObject asJsonObject4 = asJsonObject.getAsJsonObject("environment");
            String asString5 = asJsonObject4.get("reloading-period").getAsString();
            String asString6 = asJsonObject4.get("snapshot-period").getAsString();
            String asString7 = asJsonObject4.get("m-log-period").getAsString();
            int asInt = asJsonObject4.get("m-chunk-count").getAsInt();
            int asInt2 = asJsonObject4.get("m-storage-capacity").getAsInt();
            NxLogcat.d("Environment, reload Period : " + asString5);
            NxLogcat.d("Environment, snapshot : " + asString6);
            NxLogcat.d("Environment, mobile log send preiod : " + asString7);
            NxLogcat.d("Environment, mobile chunk count : " + asInt);
            NxLogcat.d("Environment, mobile storage capacity : " + asInt2);
            NxLogSendWorker unused = NxScheduler.this.logSendWorker;
            NxLogSendWorker.setProtocol(asString);
            NxLogSendWorker unused2 = NxScheduler.this.logSendWorker;
            NxLogSendWorker.setDomain(asString2);
            NxScheduler.this.logSendWorker.setSendLimitCount(asInt);
            if (NxScheduler.this.setPeriod(asString7, 1)) {
                NxScheduler.this.cancelLogSender();
                NxScheduler.this.scheduleLogSender(NxScheduler.this.senderPeriod, NxScheduler.this.senderPeriod, NxScheduler.this.sendTimeType);
            }
            NxScheduler.this.configurationWorker.setProtocol(asString3);
            NxScheduler.this.configurationWorker.setDomain(asString4);
            if (NxScheduler.this.setPeriod(asString5, 3)) {
                NxScheduler.this.cancelConfiguration();
                NxScheduler.this.scheduleConfiguration(NxScheduler.this.configPeriod, NxScheduler.this.configPeriod, NxScheduler.this.configTimeType);
            }
            if (NxScheduler.this.setPeriod(asString6, 2)) {
                NxScheduler.this.cancelSnapshot();
                NxScheduler.this.scheduleSnapshot(NxScheduler.this.snapshotPeriod, NxScheduler.this.snapshotPeriod, NxScheduler.this.snapshotTimeType);
            }
            NxDatabase.getInstance().setStorageLimitCount(asInt2);
        }
    };
    private ScheduledExecutorService logDispatchExecutorService = Executors.newSingleThreadScheduledExecutor();
    private ScheduledExecutorService additionalExecutorService = Executors.newSingleThreadScheduledExecutor();
    private NxTimeSyncWorker timeSyncWorker = new NxTimeSyncWorker(this.onTimeSyncReceiver);
    private NxLogSendWorker logSendWorker = new NxLogSendWorker();
    private NxConfigurationWorker configurationWorker = new NxConfigurationWorker(this.onConfigReceiver);
    private NxLogSummaryWorker logSummaryWorker = new NxLogSummaryWorker();

    NxScheduler(Context context) {
        this.snapshotWorker = new NxSnapshotWorker(context);
    }

    void cancelAll() {
        cancelSummaryLog();
        cancelSnapshot();
        cancelConfiguration();
        cancelLogSender();
        cancelTimeSync();
        NxLogcat.d("Workers cancel!!");
    }

    boolean cancelConfiguration() {
        if (this.configFuture == null) {
            return false;
        }
        return this.configFuture.cancel(false);
    }

    boolean cancelLogSender() {
        if (this.senderFuture == null) {
            return false;
        }
        return this.senderFuture.cancel(false);
    }

    boolean cancelSnapshot() {
        if (this.snapshotFuture == null) {
            return false;
        }
        return this.snapshotFuture.cancel(false);
    }

    boolean cancelSummaryLog() {
        if (this.summaryFuture == null) {
            return false;
        }
        return this.summaryFuture.cancel(false);
    }

    boolean cancelTimeSync() {
        if (this.timeSyncFuture == null) {
            return false;
        }
        return this.timeSyncFuture.cancel(false);
    }

    boolean executeLogSenderInOverCounter() {
        if (NxDatabase.getInstance().getLogCount() <= this.sendCounter) {
            this.isActiveCounter = false;
        } else if (!this.isActiveCounter) {
            this.isActiveCounter = true;
            this.logDispatchExecutorService.execute(this.logSendWorker);
            return true;
        }
        return false;
    }

    Future scheduleConfiguration(long j, long j2, TimeUnit timeUnit) {
        ScheduledFuture<?> scheduledFutureScheduleWithFixedDelay = this.additionalExecutorService.scheduleWithFixedDelay(this.configurationWorker, j, j2, timeUnit);
        this.configFuture = scheduledFutureScheduleWithFixedDelay;
        return scheduledFutureScheduleWithFixedDelay;
    }

    Future scheduleLogSender(long j, long j2, TimeUnit timeUnit) {
        ScheduledFuture<?> scheduledFutureScheduleWithFixedDelay = this.logDispatchExecutorService.scheduleWithFixedDelay(this.logSendWorker, j, j2, timeUnit);
        this.senderFuture = scheduledFutureScheduleWithFixedDelay;
        return scheduledFutureScheduleWithFixedDelay;
    }

    Future scheduleSnapshot(long j, long j2, TimeUnit timeUnit) {
        ScheduledFuture<?> scheduledFutureScheduleWithFixedDelay = this.additionalExecutorService.scheduleWithFixedDelay(this.snapshotWorker, j, j2, timeUnit);
        this.snapshotFuture = scheduledFutureScheduleWithFixedDelay;
        return scheduledFutureScheduleWithFixedDelay;
    }

    Future scheduleSummaryLog(long j, long j2, TimeUnit timeUnit) {
        ScheduledFuture<?> scheduledFutureScheduleWithFixedDelay = this.additionalExecutorService.scheduleWithFixedDelay(this.logSummaryWorker, j, j2, timeUnit);
        this.summaryFuture = scheduledFutureScheduleWithFixedDelay;
        return scheduledFutureScheduleWithFixedDelay;
    }

    public boolean setPeriod(String str, int i) {
        TimeUnit timeUnit;
        if (str == null) {
            return false;
        }
        String str2 = "";
        String strSubstring = "";
        String strTrim = str.trim();
        for (int i2 = 0; i2 < strTrim.length(); i2++) {
            char cCharAt = strTrim.charAt(i2);
            if ('0' > cCharAt || cCharAt > '9') {
                strSubstring = strTrim.substring(i2, strTrim.length());
                break;
            }
            str2 = str2 + cCharAt;
        }
        int iIntValue = Integer.valueOf(str2).intValue();
        switch (strSubstring) {
            case "h":
                timeUnit = TimeUnit.HOURS;
                break;
            case "m":
                timeUnit = TimeUnit.MINUTES;
                break;
            case "s":
                timeUnit = TimeUnit.SECONDS;
                break;
            case "ms":
                timeUnit = TimeUnit.MILLISECONDS;
                break;
            case "":
                timeUnit = TimeUnit.MILLISECONDS;
                break;
            default:
                NxLogcat.e("Type is not correct!");
                return false;
        }
        switch (i) {
            case 1:
                if (iIntValue == this.senderPeriod && timeUnit == this.sendTimeType) {
                    return false;
                }
                this.senderPeriod = iIntValue;
                this.sendTimeType = timeUnit;
                break;
            case 2:
                if (iIntValue == this.snapshotPeriod && timeUnit == this.snapshotTimeType) {
                    return false;
                }
                this.snapshotPeriod = iIntValue;
                this.snapshotTimeType = timeUnit;
                break;
            case 3:
                if (iIntValue == this.configPeriod && timeUnit == this.configTimeType) {
                    return false;
                }
                this.configPeriod = iIntValue;
                this.configTimeType = timeUnit;
                break;
            default:
                NxLogcat.e("Worker Type not exist!");
                return false;
        }
        NxLogcat.i("Period set Period!");
        return true;
    }

    void setStateCallback(NxStateCallback nxStateCallback) {
        this.stateCallback = nxStateCallback;
    }

    public void setUpConfigInfo_TEST(Map<String, Object> map) {
        if (map == null) {
            return;
        }
        String str = (String) map.get("logServerProtocol");
        String str2 = (String) map.get("logServerDomain");
        Integer num = (Integer) map.get("sendRetryDelayTime");
        int iIntValue = ((Integer) map.get("chunkCount")).intValue();
        int iIntValue2 = ((Integer) map.get("sendCounter")).intValue();
        int iIntValue3 = ((Integer) map.get("storageCapacity")).intValue();
        String str3 = (String) map.get("logPeriod");
        NxLogSendWorker nxLogSendWorker = this.logSendWorker;
        NxLogSendWorker.setProtocol(str);
        NxLogSendWorker nxLogSendWorker2 = this.logSendWorker;
        NxLogSendWorker.setDomain(str2);
        if (iIntValue2 != 0) {
            this.sendCounter = iIntValue2;
        }
        this.logSendWorker.setRetryDelayTime(num);
        this.logSendWorker.setSendLimitCount(iIntValue);
        NxDatabase.getInstance().setStorageLimitCount(iIntValue3);
        if (setPeriod(str3, 1)) {
            cancelLogSender();
            scheduleLogSender(this.senderPeriod, this.senderPeriod, this.sendTimeType);
        }
    }

    void startAll() {
        try {
            submitTimeSync().get();
        } catch (Exception e) {
            NxLogcat.e("startAll : " + e.getMessage());
        }
        if (!this.logSend) {
            NxLogcat.w("Log collection is stopped! " + this.logSend);
            return;
        }
        if (this.senderFuture == null) {
            scheduleLogSender(this.senderPeriod, this.senderPeriod, this.sendTimeType);
        }
        if (this.configFuture == null) {
            scheduleConfiguration(0L, this.configPeriod, this.configTimeType);
        }
        if (this.snapshotFuture == null) {
            scheduleSnapshot(0L, this.snapshotPeriod, this.snapshotTimeType);
        }
        if (this.summaryFuture == null) {
            scheduleSummaryLog(this.summaryPeriod, this.summaryPeriod, this.summaryTimeType);
        }
        NxLogcat.d("Workers started!");
    }

    Future submitTimeSync() {
        Future<?> futureSubmit = this.additionalExecutorService.submit(this.timeSyncWorker);
        this.timeSyncFuture = futureSubmit;
        return futureSubmit;
    }
}
