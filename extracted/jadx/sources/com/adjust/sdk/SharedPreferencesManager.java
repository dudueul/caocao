package com.adjust.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class SharedPreferencesManager {
    private static final int INDEX_CLICK_TIME = 1;
    private static final int INDEX_IS_SENDING = 2;
    private static final int INDEX_RAW_REFERRER = 0;
    private static final String PREFS_KEY_INSTALL_TRACKED = "install_tracked";
    private static final String PREFS_KEY_PUSH_TOKEN = "push_token";
    private static final String PREFS_KEY_RAW_REFERRERS = "raw_referrers";
    private static final String PREFS_NAME = "adjust_preferences";
    private static final int REFERRERS_COUNT = 10;
    private final SharedPreferences sharedPreferences;

    public SharedPreferencesManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
    }

    private synchronized boolean getBoolean(String str, boolean z) {
        try {
            z = this.sharedPreferences.getBoolean(str, z);
        } catch (ClassCastException e) {
        }
        return z;
    }

    private synchronized int getRawReferrerIndex(String str, long j) {
        int i;
        try {
            JSONArray rawReferrerArray = getRawReferrerArray();
            i = 0;
            while (i < rawReferrerArray.length()) {
                JSONArray jSONArray = rawReferrerArray.getJSONArray(i);
                String strOptString = jSONArray.optString(0, null);
                if (strOptString == null || !strOptString.equals(str) || jSONArray.optLong(1, -1L) != j) {
                    i++;
                }
            }
        } catch (JSONException e) {
        }
        i = -1;
        return i;
    }

    private synchronized String getString(String str) {
        String string = null;
        synchronized (this) {
            try {
                string = this.sharedPreferences.getString(str, null);
            } catch (ClassCastException e) {
            } catch (Throwable th) {
                if (str.equals(PREFS_KEY_RAW_REFERRERS)) {
                    remove(PREFS_KEY_RAW_REFERRERS);
                }
            }
        }
        return string;
    }

    private synchronized void remove(String str) {
        this.sharedPreferences.edit().remove(str).apply();
    }

    private synchronized void saveBoolean(String str, boolean z) {
        this.sharedPreferences.edit().putBoolean(str, z).apply();
    }

    private synchronized void saveString(String str, String str2) {
        this.sharedPreferences.edit().putString(str, str2).apply();
    }

    public synchronized void clear() {
        this.sharedPreferences.edit().clear().apply();
    }

    public synchronized boolean getInstallTracked() {
        return getBoolean(PREFS_KEY_INSTALL_TRACKED, false);
    }

    public synchronized String getPushToken() {
        return getString(PREFS_KEY_PUSH_TOKEN);
    }

    public synchronized JSONArray getRawReferrer(String str, long j) {
        JSONArray jSONArray;
        int rawReferrerIndex = getRawReferrerIndex(str, j);
        if (rawReferrerIndex >= 0) {
            try {
                jSONArray = getRawReferrerArray().getJSONArray(rawReferrerIndex);
            } catch (JSONException e) {
                jSONArray = null;
            }
        } else {
            jSONArray = null;
        }
        return jSONArray;
    }

    public synchronized JSONArray getRawReferrerArray() {
        JSONArray jSONArray;
        String string = getString(PREFS_KEY_RAW_REFERRERS);
        if (string != null) {
            try {
                JSONArray jSONArray2 = new JSONArray(string);
                if (jSONArray2.length() > 10) {
                    jSONArray = new JSONArray();
                    for (int i = 0; i < 10; i++) {
                        jSONArray.put(jSONArray2.get(i));
                    }
                    saveRawReferrerArray(jSONArray);
                } else {
                    jSONArray = new JSONArray(string);
                }
            } catch (JSONException e) {
                jSONArray = new JSONArray();
            } catch (Throwable th) {
                jSONArray = new JSONArray();
            }
        } else {
            jSONArray = new JSONArray();
        }
        return jSONArray;
    }

    public synchronized void removePushToken() {
        remove(PREFS_KEY_PUSH_TOKEN);
    }

    public synchronized void removeRawReferrer(String str, long j) {
        int rawReferrerIndex;
        if (str != null) {
            if (str.length() != 0 && (rawReferrerIndex = getRawReferrerIndex(str, j)) >= 0) {
                JSONArray rawReferrerArray = getRawReferrerArray();
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < rawReferrerArray.length(); i++) {
                    if (i != rawReferrerIndex) {
                        try {
                            jSONArray.put(rawReferrerArray.getJSONArray(i));
                        } catch (JSONException e) {
                        }
                    }
                }
                saveString(PREFS_KEY_RAW_REFERRERS, jSONArray.toString());
            }
        }
    }

    public synchronized void savePushToken(String str) {
        saveString(PREFS_KEY_PUSH_TOKEN, str);
    }

    public synchronized void saveRawReferrer(String str, long j) {
        try {
            if (getRawReferrer(str, j) == null) {
                JSONArray rawReferrerArray = getRawReferrerArray();
                if (rawReferrerArray.length() != 10) {
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(0, str);
                    jSONArray.put(1, j);
                    jSONArray.put(2, 0);
                    rawReferrerArray.put(jSONArray);
                    saveRawReferrerArray(rawReferrerArray);
                }
            }
        } catch (JSONException e) {
        }
    }

    public synchronized void saveRawReferrerArray(JSONArray jSONArray) {
        try {
            saveString(PREFS_KEY_RAW_REFERRERS, jSONArray.toString());
        } catch (Throwable th) {
            remove(PREFS_KEY_RAW_REFERRERS);
        }
    }

    public synchronized void setInstallTracked() {
        saveBoolean(PREFS_KEY_INSTALL_TRACKED, true);
    }

    public synchronized void setSendingReferrersAsNotSent() {
        try {
            JSONArray rawReferrerArray = getRawReferrerArray();
            boolean z = false;
            for (int i = 0; i < rawReferrerArray.length(); i++) {
                JSONArray jSONArray = rawReferrerArray.getJSONArray(i);
                if (jSONArray.optInt(2, -1) == 1) {
                    jSONArray.put(2, 0);
                    z = true;
                }
            }
            if (z) {
                saveRawReferrerArray(rawReferrerArray);
            }
        } catch (JSONException e) {
        }
    }
}
