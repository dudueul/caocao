package com.navercorp.volleyextensions.volleyer.util;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Assert {
    private static final String MESSAGE_FORMAT = "\"%s\" argument must be not null.";

    public static void notNull(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(String.format(MESSAGE_FORMAT, str));
        }
    }
}
