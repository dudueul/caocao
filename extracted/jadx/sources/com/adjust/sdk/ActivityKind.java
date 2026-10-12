package com.adjust.sdk;

import com.tapjoy.TJAdUnitConstants;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public enum ActivityKind {
    UNKNOWN,
    SESSION,
    EVENT,
    CLICK,
    ATTRIBUTION,
    REVENUE,
    REATTRIBUTION,
    INFO;

    public static ActivityKind fromString(String str) {
        if ("session".equals(str)) {
            return SESSION;
        }
        if ("event".equals(str)) {
            return EVENT;
        }
        if (TJAdUnitConstants.String.CLICK.equals(str)) {
            return CLICK;
        }
        if ("attribution".equals(str)) {
            return ATTRIBUTION;
        }
        return TJAdUnitConstants.String.VIDEO_INFO.equals(str) ? INFO : UNKNOWN;
    }

    @Override // java.lang.Enum
    public String toString() {
        switch (this) {
            case SESSION:
                return "session";
            case EVENT:
                return "event";
            case CLICK:
                return TJAdUnitConstants.String.CLICK;
            case ATTRIBUTION:
                return "attribution";
            case INFO:
                return TJAdUnitConstants.String.VIDEO_INFO;
            default:
                return "unknown";
        }
    }
}
