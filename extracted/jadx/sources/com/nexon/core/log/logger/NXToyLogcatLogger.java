package com.nexon.core.log.logger;

import android.util.Log;
import com.nexon.core.log.constant.NXToyLogLevel;
import com.nexon.core.log.model.NXToyLogcatLog;
import com.nexon.core.log.model.NXToyStackTraceInfo;
import com.nexon.core.log.util.NXToyLogUtil;
import com.nexon.core.util.NXJsonUtil;
import com.nexon.core.util.NXStringUtil;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyLogcatLogger extends NXToyLogger {
    private final int LOG_CHUNK_SIZE = 2048;
    private final BlockingQueue<NXToyLogcatLog> logBuffer = new LinkedBlockingQueue();
    private final Thread logger = new Thread(new Runnable() { // from class: com.nexon.core.log.logger.NXToyLogcatLogger.1
        @Override // java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    NXToyLogcatLog nXToyLogcatLog = (NXToyLogcatLog) NXToyLogcatLogger.this.logBuffer.take();
                    String log = nXToyLogcatLog.getLog();
                    String tag = nXToyLogcatLog.getTag();
                    for (int i = 0; i < log.length(); i += 2048) {
                        String strSubstring = log.substring(i, Math.min(i + 2048, log.length()));
                        switch (AnonymousClass2.$SwitchMap$com$nexon$core$log$constant$NXToyLogLevel[nXToyLogcatLog.getLogLevel().ordinal()]) {
                            case 1:
                                Log.v(tag, strSubstring);
                                break;
                            case 2:
                                Log.d(tag, strSubstring);
                                break;
                            case 3:
                                Log.e(tag, strSubstring);
                                break;
                            case 4:
                                Log.i(tag, strSubstring);
                                break;
                            case 5:
                                Log.d("d" + tag, strSubstring);
                                break;
                            default:
                                Log.v(tag, strSubstring);
                                break;
                        }
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    });

    /* JADX INFO: renamed from: com.nexon.core.log.logger.NXToyLogcatLogger$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$nexon$core$log$constant$NXToyLogLevel = new int[NXToyLogLevel.values().length];

        static {
            try {
                $SwitchMap$com$nexon$core$log$constant$NXToyLogLevel[NXToyLogLevel.VERBOSE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$nexon$core$log$constant$NXToyLogLevel[NXToyLogLevel.DEBUG.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$nexon$core$log$constant$NXToyLogLevel[NXToyLogLevel.ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$nexon$core$log$constant$NXToyLogLevel[NXToyLogLevel.INFO.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$nexon$core$log$constant$NXToyLogLevel[NXToyLogLevel.DEVELOP.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public NXToyLogcatLogger() {
        this.logger.start();
    }

    @Override // com.nexon.core.log.logger.NXToyLogger
    public void addLog(NXToyStackTraceInfo nXToyStackTraceInfo, NXToyLogLevel nXToyLogLevel, String str, Object... objArr) {
        if (NXStringUtil.isNull(str)) {
            return;
        }
        this.logBuffer.add(new NXToyLogcatLog(nXToyLogLevel, NXJsonUtil.toJsonString(NXToyLogUtil.parseVarargs(str, objArr)), nXToyStackTraceInfo.getTag()));
    }
}
