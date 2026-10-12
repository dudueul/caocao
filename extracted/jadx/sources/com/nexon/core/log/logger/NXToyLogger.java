package com.nexon.core.log.logger;

import com.nexon.core.log.constant.NXToyLogLevel;
import com.nexon.core.log.model.NXToyStackTraceInfo;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class NXToyLogger {
    public abstract void addLog(NXToyStackTraceInfo nXToyStackTraceInfo, NXToyLogLevel nXToyLogLevel, String str, Object... objArr);
}
