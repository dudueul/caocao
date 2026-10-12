package com.crashlytics.android.core;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.common.BackgroundPriorityRunnable;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class ReportUploader {
    static final Map<String, String> HEADER_INVALID_CLS_FILE = Collections.singletonMap("X-CRASHLYTICS-INVALID-SESSION", "1");
    private static final short[] RETRY_INTERVALS = {10, 20, 30, 60, 120, 300};
    private final String apiKey;
    private final CreateReportSpiCall createReportCall;
    private final Object fileAccessLock = new Object();
    private final HandlingExceptionCheck handlingExceptionCheck;
    private final ReportFilesProvider reportFilesProvider;
    private Thread uploadThread;

    static final class AlwaysSendCheck implements SendCheck {
        AlwaysSendCheck() {
        }

        @Override // com.crashlytics.android.core.ReportUploader.SendCheck
        public boolean canSendReports() {
            return true;
        }
    }

    interface HandlingExceptionCheck {
        boolean isHandlingException();
    }

    interface ReportFilesProvider {
        File[] getCompleteSessionFiles();

        File[] getInvalidSessionFiles();
    }

    interface SendCheck {
        boolean canSendReports();
    }

    private class Worker extends BackgroundPriorityRunnable {
        private final float delay;
        private final SendCheck sendCheck;

        Worker(float f, SendCheck sendCheck) {
            this.delay = f;
            this.sendCheck = sendCheck;
        }

        private void attemptUploadWithRetry() {
            Fabric.getLogger().d(CrashlyticsCore.TAG, "Starting report processing in " + this.delay + " second(s)...");
            if (this.delay > 0.0f) {
                try {
                    Thread.sleep((long) (this.delay * 1000.0f));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            List<Report> listFindReports = ReportUploader.this.findReports();
            if (ReportUploader.this.handlingExceptionCheck.isHandlingException()) {
                return;
            }
            if (!listFindReports.isEmpty() && !this.sendCheck.canSendReports()) {
                Fabric.getLogger().d(CrashlyticsCore.TAG, "User declined to send. Removing " + listFindReports.size() + " Report(s).");
                Iterator<Report> it = listFindReports.iterator();
                while (it.hasNext()) {
                    it.next().remove();
                }
                return;
            }
            int i = 0;
            while (!listFindReports.isEmpty() && !ReportUploader.this.handlingExceptionCheck.isHandlingException()) {
                Fabric.getLogger().d(CrashlyticsCore.TAG, "Attempting to send " + listFindReports.size() + " report(s)");
                Iterator<Report> it2 = listFindReports.iterator();
                while (it2.hasNext()) {
                    ReportUploader.this.forceUpload(it2.next());
                }
                listFindReports = ReportUploader.this.findReports();
                if (!listFindReports.isEmpty()) {
                    int i2 = i + 1;
                    long j = ReportUploader.RETRY_INTERVALS[Math.min(i, ReportUploader.RETRY_INTERVALS.length - 1)];
                    Fabric.getLogger().d(CrashlyticsCore.TAG, "Report submisson: scheduling delayed retry in " + j + " seconds");
                    try {
                        Thread.sleep(1000 * j);
                        i = i2;
                    } catch (InterruptedException e2) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }

        @Override // io.fabric.sdk.android.services.common.BackgroundPriorityRunnable
        public void onRun() {
            try {
                attemptUploadWithRetry();
            } catch (Exception e) {
                Fabric.getLogger().e(CrashlyticsCore.TAG, "An unexpected error occurred while attempting to upload crash reports.", e);
            }
            ReportUploader.this.uploadThread = null;
        }
    }

    public ReportUploader(String str, CreateReportSpiCall createReportSpiCall, ReportFilesProvider reportFilesProvider, HandlingExceptionCheck handlingExceptionCheck) {
        if (createReportSpiCall == null) {
            throw new IllegalArgumentException("createReportCall must not be null.");
        }
        this.createReportCall = createReportSpiCall;
        this.apiKey = str;
        this.reportFilesProvider = reportFilesProvider;
        this.handlingExceptionCheck = handlingExceptionCheck;
    }

    List<Report> findReports() {
        File[] completeSessionFiles;
        File[] invalidSessionFiles;
        Fabric.getLogger().d(CrashlyticsCore.TAG, "Checking for crash reports...");
        synchronized (this.fileAccessLock) {
            completeSessionFiles = this.reportFilesProvider.getCompleteSessionFiles();
            invalidSessionFiles = this.reportFilesProvider.getInvalidSessionFiles();
        }
        LinkedList linkedList = new LinkedList();
        if (completeSessionFiles != null) {
            for (File file : completeSessionFiles) {
                Fabric.getLogger().d(CrashlyticsCore.TAG, "Found crash report " + file.getPath());
                linkedList.add(new SessionReport(file));
            }
        }
        HashMap map = new HashMap();
        if (invalidSessionFiles != null) {
            for (File file2 : invalidSessionFiles) {
                String sessionIdFromSessionFile = CrashlyticsController.getSessionIdFromSessionFile(file2);
                if (!map.containsKey(sessionIdFromSessionFile)) {
                    map.put(sessionIdFromSessionFile, new LinkedList());
                }
                ((List) map.get(sessionIdFromSessionFile)).add(file2);
            }
        }
        for (String str : map.keySet()) {
            Fabric.getLogger().d(CrashlyticsCore.TAG, "Found invalid session: " + str);
            List list = (List) map.get(str);
            linkedList.add(new InvalidSessionReport(str, (File[]) list.toArray(new File[list.size()])));
        }
        if (linkedList.isEmpty()) {
            Fabric.getLogger().d(CrashlyticsCore.TAG, "No reports found.");
        }
        return linkedList;
    }

    boolean forceUpload(Report report) {
        boolean z = false;
        synchronized (this.fileAccessLock) {
            try {
                boolean zInvoke = this.createReportCall.invoke(new CreateReportRequest(this.apiKey, report));
                Fabric.getLogger().i(CrashlyticsCore.TAG, "Crashlytics report upload " + (zInvoke ? "complete: " : "FAILED: ") + report.getIdentifier());
                if (zInvoke) {
                    report.remove();
                    z = true;
                }
            } catch (Exception e) {
                Fabric.getLogger().e(CrashlyticsCore.TAG, "Error occurred sending report " + report, e);
            }
        }
        return z;
    }

    boolean isUploading() {
        return this.uploadThread != null;
    }

    public synchronized void uploadReports(float f, SendCheck sendCheck) {
        if (this.uploadThread != null) {
            Fabric.getLogger().d(CrashlyticsCore.TAG, "Report upload has already been started.");
        } else {
            this.uploadThread = new Thread(new Worker(f, sendCheck), "Crashlytics Report Uploader");
            this.uploadThread.start();
        }
    }
}
