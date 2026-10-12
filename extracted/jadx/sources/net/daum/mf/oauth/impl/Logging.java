package net.daum.mf.oauth.impl;

import android.util.Log;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class Logging {
    public static boolean DEBUG = false;
    public static boolean LOGGING_ERROR_LEVEL = false;
    public static final String LOG_TAG = "OAuth 2.0 SDK";

    public static void debug(String str) {
        if (DEBUG) {
            if (LOGGING_ERROR_LEVEL) {
                Log.e(LOG_TAG, str);
            } else {
                Log.d(LOG_TAG, str);
            }
        }
    }
}
