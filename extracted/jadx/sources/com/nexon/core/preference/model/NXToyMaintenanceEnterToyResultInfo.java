package com.nexon.core.preference.model;

import com.nexon.core.requestpostman.result.NXToyResult;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyMaintenanceEnterToyResultInfo {
    private final long delayTime = 60000;
    private NXToyResult enterToyResult;
    private long nextSendingTime;

    public NXToyMaintenanceEnterToyResultInfo(long j, NXToyResult nXToyResult) {
        this.nextSendingTime = 60000 + j;
        this.enterToyResult = nXToyResult;
    }

    public NXToyResult getEnterToyResult() {
        return this.enterToyResult;
    }

    public long getNextSendingTime() {
        return this.nextSendingTime;
    }
}
