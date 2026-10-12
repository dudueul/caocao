package com.facebook.appevents;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import com.adjust.sdk.Constants;
import com.facebook.FacebookSdk;
import com.facebook.appevents.internal.AppEventUtility;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class UserDataStore {
    public static final String CITY = "ct";
    public static final String COUNTRY = "country";
    public static final String DATE_OF_BIRTH = "db";
    public static final String EMAIL = "em";
    public static final String FIRST_NAME = "fn";
    public static final String GENDER = "ge";
    public static final String LAST_NAME = "ln";
    public static final String PHONE = "ph";
    public static final String STATE = "st";
    private static final String USER_DATA_KEY = "com.facebook.appevents.UserDataStore.userData";
    public static final String ZIP = "zp";
    private static String hashedUserData;
    private static final String TAG = UserDataStore.class.getSimpleName();
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static volatile boolean initialized = false;

    private static String encryptData(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(Constants.SHA256);
            messageDigest.update(str.getBytes());
            return AppEventUtility.bytesToHex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String getHashedUserData() {
        if (!initialized) {
            Log.w(TAG, "initStore should have been called before calling setUserID");
            initAndWait();
        }
        lock.readLock().lock();
        try {
            return hashedUserData;
        } finally {
            lock.readLock().unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String hashUserData(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            try {
                String string = bundle.get(str).toString();
                if (maybeSHA256Hashed(string)) {
                    jSONObject.put(str, string.toLowerCase());
                } else {
                    String strEncryptData = encryptData(normalizeData(str, bundle.get(str).toString()));
                    if (strEncryptData != null) {
                        jSONObject.put(str, strEncryptData);
                    }
                }
            } catch (JSONException e) {
            }
        }
        return jSONObject.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void initAndWait() {
        if (initialized) {
            return;
        }
        lock.writeLock().lock();
        try {
            if (initialized) {
                return;
            }
            hashedUserData = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).getString(USER_DATA_KEY, null);
            initialized = true;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static void initStore() {
        if (initialized) {
            return;
        }
        AppEventsLogger.getAnalyticsExecutor().execute(new Runnable() { // from class: com.facebook.appevents.UserDataStore.1
            @Override // java.lang.Runnable
            public void run() {
                UserDataStore.initAndWait();
            }
        });
    }

    private static boolean maybeSHA256Hashed(String str) {
        return str.matches("[A-Fa-f0-9]{64}");
    }

    private static String normalizeData(String str, String str2) {
        switch (str) {
            case "em":
            case "fn":
            case "ln":
            case "ct":
            case "st":
            case "country":
                return str2.trim().toLowerCase();
            case "ph":
                return str2.trim().replaceAll("[^0-9]", "");
            case "ge":
                String lowerCase = str2.trim().toLowerCase();
                return lowerCase.length() > 0 ? lowerCase.substring(0, 1) : "";
            default:
                return "";
        }
    }

    public static void setUserDataAndHash(final Bundle bundle) {
        if (!initialized) {
            Log.w(TAG, "initStore should have been called before calling setUserData");
            initAndWait();
        }
        AppEventsLogger.getAnalyticsExecutor().execute(new Runnable() { // from class: com.facebook.appevents.UserDataStore.2
            @Override // java.lang.Runnable
            public void run() {
                UserDataStore.lock.writeLock().lock();
                try {
                    String unused = UserDataStore.hashedUserData = UserDataStore.hashUserData(bundle);
                    SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
                    editorEdit.putString(UserDataStore.USER_DATA_KEY, UserDataStore.hashedUserData);
                    editorEdit.apply();
                } finally {
                    UserDataStore.lock.writeLock().unlock();
                }
            }
        });
    }
}
