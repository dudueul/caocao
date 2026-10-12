package com.nexon.core.log.model;

import com.nexon.core.log.constant.NXToyLogLevel;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyLogcatLog extends NXToyLog {
    private String tag;

    public NXToyLogcatLog(NXToyLogLevel nXToyLogLevel, String str, String str2) {
        super(nXToyLogLevel, str);
        this.tag = str2;
    }

    public String getTag() {
        return this.tag;
    }
}
