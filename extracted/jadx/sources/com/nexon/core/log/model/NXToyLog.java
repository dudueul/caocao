package com.nexon.core.log.model;

import com.nexon.core.log.constant.NXToyLogLevel;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyLog {
    private String log;
    private NXToyLogLevel logLevel;

    public NXToyLog(NXToyLogLevel nXToyLogLevel, String str) {
        this.log = str;
        this.logLevel = nXToyLogLevel;
    }

    public String getLog() {
        return this.log;
    }

    public NXToyLogLevel getLogLevel() {
        return this.logLevel;
    }
}
