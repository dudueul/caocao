package com.nexon.nexonanalyticssdk;

import android.os.SystemClock;
import com.adjust.sdk.Constants;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class NxTimeSyncWorker implements Runnable {
    private NxTimeSyncCallback timeSyncCallback;
    private String timeURLFormat = "%s://%s/v2/configurations/na_time_sync?key-path=%s";
    private String timeProtocol = Constants.SCHEME;
    private String timeDomain = "config.livelog.nexon.com";

    public NxTimeSyncWorker(NxTimeSyncCallback nxTimeSyncCallback) {
        this.timeSyncCallback = nxTimeSyncCallback;
    }

    @Override // java.lang.Runnable
    public void run() {
        NxLogInfo nxLogInfo = NxLogInfo.getInstance();
        NxNetwork nxNetwork = NxNetwork.getInstance();
        String str = (String) nxLogInfo.getGameClientInfo().get(NxLogInfo.KEY_SERVICE_ID);
        try {
            long jUptimeMillis = SystemClock.uptimeMillis();
            String info = nxNetwork.getInfo(String.format(this.timeURLFormat, this.timeProtocol, this.timeDomain, str));
            long jUptimeMillis2 = SystemClock.uptimeMillis();
            if (info != null && !info.isEmpty()) {
                nxLogInfo.setInitUpTime(nxLogInfo.getInitUpTime() + (jUptimeMillis2 - jUptimeMillis));
                this.timeSyncCallback.setUpTimeSyncInfo(info);
            }
            NxLogSummaryWorker.initalizeSummaryBeginTime();
        } catch (Exception e) {
            NxLogcat.e("Error : " + e.getMessage());
        }
    }

    public void setTimeDomain(String str) {
        if (str.contains("http://")) {
            str = str.replace("http://", "");
        } else if (str.contains("https://")) {
            str = str.replace("https://", "");
        }
        this.timeDomain = str;
    }

    public void setTimeProtocol(String str) {
        if (str.equals("http") || str.equals(Constants.SCHEME)) {
            this.timeProtocol = str;
        }
    }
}
