package com.nexon.core.log.model;

import com.nexon.core.requestpostman.request.NXToyByteArrayRequest;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyLogRequest {
    private NXToyByteArrayRequest byteArrayRequest;
    private boolean sentLog = false;
    private NXToyLog toyLog;

    public NXToyByteArrayRequest getByteArrayRequest() {
        return this.byteArrayRequest;
    }

    public NXToyLog getToyLog() {
        return this.toyLog;
    }

    public boolean isSentLog() {
        return this.sentLog;
    }

    public void setByteArrayRequest(NXToyByteArrayRequest nXToyByteArrayRequest) {
        this.byteArrayRequest = nXToyByteArrayRequest;
    }

    public void setSentLog(boolean z) {
        this.sentLog = z;
    }

    public void setToyLog(NXToyLog nXToyLog) {
        this.toyLog = nXToyLog;
    }
}
