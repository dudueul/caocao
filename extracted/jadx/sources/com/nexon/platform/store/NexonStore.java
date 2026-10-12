package com.nexon.platform.store;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import com.nexon.platform.NXPFinalizer;
import com.nexon.platform.store.billing.Billing;
import com.nexon.platform.store.billing.Constants;
import com.nexon.platform.store.billing.Logger;
import com.nexon.platform.store.billing.vendor.VendorHolder;
import com.nexon.platform.store.billing.vendor.interfaces.VendorInterface;
import com.nexon.platform.store.internal.Utility;
import com.nexon.platform.store.internal.Validate;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NexonStore {
    private static final String CLIENT_ID_PROPERTY = "com.nexon.platform.store.NexonStoreClientId";
    public static final String MARKET_TYPE_GOOGLE_STORE = "gps";
    public static final String MARKET_TYPE_ONE_STORE = "one";
    private static final String TICKET_PREFIX = "ttc.ne1.";
    public static final String USER_TYPE = "toy";
    private static WeakReference<Activity> activityForDebug;
    private static Context applicationContext;
    private static volatile String clientId;
    private static volatile Executor executor;
    private static volatile String marketType;
    private static volatile String ticket;
    private static final String TAG = NexonStore.class.getName();
    private static Boolean initialized = false;
    private static final Object LOCK = new Object();
    private static NXPFinalizer finalizer = new NXPFinalizer() { // from class: com.nexon.platform.store.NexonStore.1
        @Override // com.nexon.platform.NXPFinalizer
        public void finalize() {
            Boolean unused = NexonStore.initialized = false;
            Context unused2 = NexonStore.applicationContext = null;
            String unused3 = NexonStore.marketType = null;
            String unused4 = NexonStore.clientId = null;
            if (NexonStore.activityForDebug != null) {
                NexonStore.activityForDebug.clear();
                WeakReference unused5 = NexonStore.activityForDebug = null;
            }
        }
    };

    public static class Error {
        public final int code;
        public final String description;
        public final String message;

        public Error(int i, String str, String str2) {
            this.code = i;
            this.message = str;
            this.description = str2;
        }

        public String toString() {
            return "{code:" + this.code + ", message:" + this.message + ", description:" + this.description + "}";
        }
    }

    public interface InitializeCallback {
        void onInitialized(Error error);
    }

    public static void dispose() {
        VendorInterface vendorInterface = VendorHolder.getInstance().getVendorInterface();
        if (vendorInterface != null) {
            vendorInterface.dispose();
        }
        initialized = false;
    }

    @Nullable
    public static Activity getActivityForDebug() {
        if (activityForDebug == null) {
            return null;
        }
        return activityForDebug.get();
    }

    public static Context getApplicationContext() {
        Validate.isInitialized();
        return applicationContext;
    }

    public static String getClientId() {
        Validate.isInitialized();
        return clientId;
    }

    public static Executor getExecutor() {
        synchronized (LOCK) {
            if (executor == null) {
                executor = AsyncTask.THREAD_POOL_EXECUTOR;
            }
        }
        return executor;
    }

    public static String getMarketType() {
        Validate.isInitialized();
        return marketType;
    }

    public static String getSdkVersion() {
        return NexonStoreVersion.BUILD;
    }

    public static String getTicket() {
        return ticket;
    }

    public static void handleActivityResult(int i, int i2, Intent intent) {
        Billing.handleActivityResult(i, i2, intent);
    }

    public static synchronized void initialize(Context context, InitializeCallback initializeCallback) {
        initialize(null, context, initializeCallback);
    }

    public static synchronized void initialize(String str, Context context, final InitializeCallback initializeCallback) {
        Logger.d(TAG, "nexon-store initialize called initialized:" + initialized);
        Logger.d(TAG, "nexon-store sdk version: [" + getSdkVersion() + "]");
        if (!initialized.booleanValue()) {
            VendorInterface vendorInterface = VendorHolder.getInstance().getVendorInterface();
            if (vendorInterface != null) {
                String strLoadClientIdFromMetadata = str;
                if (Utility.isNullOrEmpty(strLoadClientIdFromMetadata)) {
                    strLoadClientIdFromMetadata = loadClientIdFromMetadata(context);
                }
                setClientId(strLoadClientIdFromMetadata);
                Validate.notNull(context, "ApplicationContext");
                Validate.hasInternetPermissions(context);
                Validate.hasBillingPermission(context);
                applicationContext = context.getApplicationContext();
                initialized = true;
                setTicket(TICKET_PREFIX + Utility.getUUID());
                marketType = vendorInterface.getStoreType();
                vendorInterface.initializeIAB(context, strLoadClientIdFromMetadata, new VendorInterface.IABInitCallback() { // from class: com.nexon.platform.store.NexonStore.2
                    @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface.IABInitCallback
                    public void onFailedToRequest(int i, String str2) {
                        Boolean unused = NexonStore.initialized = false;
                        if (initializeCallback != null) {
                            initializeCallback.onInitialized(new Error(i, str2, str2));
                        }
                    }

                    @Override // com.nexon.platform.store.billing.vendor.interfaces.VendorInterface.IABInitCallback
                    public void onSuccess() {
                        if (initializeCallback != null) {
                            initializeCallback.onInitialized(null);
                        }
                    }
                });
            } else if (initializeCallback != null) {
                int value = Constants.ErrorCode.StoreLibraryNotFound.getValue();
                String message = Constants.ErrorCode.StoreLibraryNotFound.getMessage();
                initializeCallback.onInitialized(new Error(value, message, message));
            }
        } else if (initializeCallback != null) {
            initializeCallback.onInitialized(null);
        }
    }

    public static synchronized boolean isInitialized() {
        return initialized.booleanValue();
    }

    static String loadClientIdFromMetadata(Context context) {
        String str = null;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                Object obj = applicationInfo.metaData.get(CLIENT_ID_PROPERTY);
                str = obj instanceof String ? (String) obj : null;
                if (Utility.isNullOrEmpty(str)) {
                    throw new NexonStoreException("Check the 'com.nexon.platform.store.NexonStoreClientId' meta-data in AndroidManifest.xml.");
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return str;
    }

    public static void setActivityForDebug(@Nullable Activity activity) {
        if (activityForDebug != null) {
            activityForDebug.clear();
            activityForDebug = null;
        }
        if (activity != null) {
            activityForDebug = new WeakReference<>(activity);
        }
    }

    public static void setClientId(String str) {
        clientId = str;
    }

    public static void setExecutor(Executor executor2) {
        Validate.notNull(executor2, "executor");
        synchronized (LOCK) {
            executor = executor2;
        }
    }

    public static void setTicket(String str) {
        ticket = str;
    }
}
