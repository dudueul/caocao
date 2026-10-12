package com.nexon.platform.store.internal;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import com.nexon.platform.store.NexonStore;
import com.nexon.platform.store.NexonStoreException;
import com.nexon.platform.store.NexonStoreNotInitializedException;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class Validate {
    private static final String BILLING_PERMISSION = "com.android.vending.BILLING";
    private static final String NO_BILLING_PERMISSION_REASON = "No billing permissions granted for the app, please add <uses-permission android:name=\"com.android.vending.BILLING\" /> to your AndroidManifest.xml.";
    private static final String NO_INTERNET_PERMISSION_REASON = "No internet permissions granted for the app, please add <uses-permission android:name=\"android.permission.INTERNET\" /> to your AndroidManifest.xml.";
    private static final String TAG = Validate.class.getName();

    public static void containsNoNullOrEmpty(Collection<String> collection, String str) {
        notNull(collection, str);
        for (String str2 : collection) {
            if (str2 == null) {
                throw new NullPointerException("Container '" + str + "' cannot contain null values");
            }
            if (str2.length() == 0) {
                throw new IllegalArgumentException("Container '" + str + "' cannot contain empty values");
            }
        }
    }

    public static <T> void containsNoNulls(Collection<T> collection, String str) {
        notNull(collection, str);
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                throw new NullPointerException("Container '" + str + "' cannot contain null values");
            }
        }
    }

    public static void hasBillingPermission(Context context) {
        hasBillingPermission(context, false);
    }

    public static void hasBillingPermission(Context context, boolean z) {
        notNull(context, "context");
        if (context.checkCallingOrSelfPermission(BILLING_PERMISSION) == -1) {
            if (z) {
                throw new IllegalStateException(NO_BILLING_PERMISSION_REASON);
            }
            Log.w(TAG, NO_BILLING_PERMISSION_REASON);
        }
    }

    public static String hasClientID() {
        String clientId = NexonStore.getClientId();
        if (clientId == null) {
            throw new IllegalStateException("No App ID found, please set the App ID.");
        }
        return clientId;
    }

    public static void hasInternetPermissions(Context context) {
        hasInternetPermissions(context, true);
    }

    public static void hasInternetPermissions(Context context, boolean z) {
        notNull(context, "context");
        if (context.checkCallingOrSelfPermission("android.permission.INTERNET") == -1) {
            if (z) {
                throw new IllegalStateException(NO_INTERNET_PERMISSION_REASON);
            }
            Log.w(TAG, NO_INTERNET_PERMISSION_REASON);
        }
    }

    public static void isInitialized() {
        if (!NexonStore.isInitialized()) {
            throw new NexonStoreNotInitializedException("The SDK has not been initialized, make sure to call NexonStore.initialize() first.");
        }
    }

    public static <T> void notEmpty(Collection<T> collection, String str) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("Container '" + str + "' cannot be empty");
        }
    }

    public static <T> void notEmptyAndContainsNoNulls(Collection<T> collection, String str) {
        containsNoNulls(collection, str);
        notEmpty(collection, str);
    }

    public static void notNull(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException("Argument '" + str + "' cannot be null");
        }
    }

    public static void notNullOrEmpty(String str, String str2) {
        if (Utility.isNullOrEmpty(str)) {
            throw new IllegalArgumentException("Argument '" + str2 + "' cannot be null or empty");
        }
    }

    public static void oneOf(Object obj, String str, Object... objArr) {
        for (Object obj2 : objArr) {
            if (obj2 != null) {
                if (obj2.equals(obj)) {
                    return;
                }
            } else if (obj == null) {
                return;
            }
        }
        throw new IllegalArgumentException("Argument '" + str + "' was not one of the allowed values");
    }

    public static void runningOnUiThread() {
        if (!Looper.getMainLooper().equals(Looper.myLooper())) {
            throw new NexonStoreException("This method should be called from the UI thread");
        }
    }
}
