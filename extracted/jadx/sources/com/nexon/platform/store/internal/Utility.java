package com.nexon.platform.store.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import com.nexon.core.requestpostman.constants.NXToyErrorCode;
import com.nexon.platform.store.NexonStore;
import com.nexon.platform.store.billing.Constants;
import com.nexon.platform.store.billing.Logger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import net.daum.mf.oauth.impl.Constant;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Utility {
    private static final String APP_SETTINGS_PREFS_STORE = "com.nexon.platform.store.internal.preferences.APP_SETTINGS";
    private static final String ONESTORE_LICENSE_KEY = "com.nexon.platform.store.internal.ONESTORE_LICENSE";
    static final String TAG = Utility.class.getName();
    private static final String UUID_KEY = "com.nexon.platform.store.internal.uuid";

    public static <T> List<T> asListNoNulls(T... tArr) {
        ArrayList arrayList = new ArrayList();
        for (T t : tArr) {
            if (t != null) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    public static String base64DecodeStr(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new String(Base64.decode(str.getBytes(), 0), Constant.DEFAULT_ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String base64EncodeStr(String str) {
        if (str == null) {
            return null;
        }
        return Base64.encodeToString(str.getBytes(), 0);
    }

    public static String getOneStoreLicenseKey() {
        SharedPreferences preference = getPreference();
        if (preference == null) {
            return null;
        }
        return preference.getString(ONESTORE_LICENSE_KEY, null);
    }

    private static SharedPreferences getPreference() {
        Context applicationContext = NexonStore.getApplicationContext();
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getSharedPreferences(APP_SETTINGS_PREFS_STORE, 0);
    }

    public static String getUUID() {
        String string = null;
        SharedPreferences preference = getPreference();
        if (preference != null && (string = preference.getString(UUID_KEY, null)) == null) {
            synchronized (Utility.class) {
                if (string == null) {
                    string = UUID.randomUUID().toString();
                    preference.edit().putString(UUID_KEY, string).commit();
                }
            }
        }
        return string;
    }

    public static boolean isNotEmpty(String str) {
        return !isNullOrEmpty(str);
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static <T> boolean isNullOrEmpty(Collection<T> collection) {
        return collection == null || collection.size() == 0;
    }

    public static void setOneStoreLicenseKey(String str) {
        SharedPreferences preference = getPreference();
        if (preference == null) {
            return;
        }
        preference.edit().putString(ONESTORE_LICENSE_KEY, str).commit();
    }

    public static JSONObject toJSONObject(String str) {
        try {
            return new JSONObject(str);
        } catch (Exception e) {
            Logger.d(TAG, e.toString());
            return null;
        }
    }

    public static int wrappingToyErrorToStoreErrorCode(int i) {
        if (i == NXToyErrorCode.NETWORK_UNAVAILABLE.getCode() || i == NXToyErrorCode.NETWORK_TIMEOUT.getCode() || i == NXToyErrorCode.NETWORK_CONNECTION_LOST.getCode()) {
            return Constants.ErrorCode.NetworkError.getValue();
        }
        return (i == NXToyErrorCode.PARSE_ERR1.getCode() || i == NXToyErrorCode.PARSE_ERR2.getCode() || i == NXToyErrorCode.PARSE_ERR3.getCode()) ? Constants.ErrorCode.InternalParseError.getValue() : i;
    }
}
