package com.facebook.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.FacebookException;
import com.tapjoy.TapjoyConstants;
import io.fabric.sdk.android.services.common.AdvertisingInfoServiceStrategy;
import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class AttributionIdentifiers {
    private static final String ANDROID_ID_COLUMN_NAME = "androidid";
    private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER = "com.facebook.katana.provider.AttributionIdProvider";
    private static final String ATTRIBUTION_ID_CONTENT_PROVIDER_WAKIZASHI = "com.facebook.wakizashi.provider.AttributionIdProvider";
    private static final int CONNECTION_RESULT_SUCCESS = 0;
    private static final long IDENTIFIER_REFRESH_INTERVAL_MILLIS = 3600000;
    private static final String LIMIT_TRACKING_COLUMN_NAME = "limit_tracking";
    private static final String TAG = AttributionIdentifiers.class.getCanonicalName();
    private static AttributionIdentifiers recentlyFetchedIdentifiers;
    private String androidAdvertiserId;
    private String androidInstallerPackage;
    private String attributionId;
    private long fetchTime;
    private boolean limitTracking;

    private static final class GoogleAdInfo implements IInterface {
        private static final int FIRST_TRANSACTION_CODE = 1;
        private static final int SECOND_TRANSACTION_CODE = 2;
        private IBinder binder;

        GoogleAdInfo(IBinder iBinder) {
            this.binder = iBinder;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this.binder;
        }

        public String getAdvertiserId() throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(AdvertisingInfoServiceStrategy.AdvertisingInterface.ADVERTISING_ID_SERVICE_INTERFACE_TOKEN);
                this.binder.transact(1, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readString();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        public boolean isTrackingLimited() throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(AdvertisingInfoServiceStrategy.AdvertisingInterface.ADVERTISING_ID_SERVICE_INTERFACE_TOKEN);
                parcelObtain.writeInt(1);
                this.binder.transact(2, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readInt() != 0;
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }
    }

    private static final class GoogleAdServiceConnection implements ServiceConnection {
        private AtomicBoolean consumed;
        private final BlockingQueue<IBinder> queue;

        private GoogleAdServiceConnection() {
            this.consumed = new AtomicBoolean(false);
            this.queue = new LinkedBlockingDeque();
        }

        public IBinder getBinder() throws InterruptedException {
            if (this.consumed.compareAndSet(true, true)) {
                throw new IllegalStateException("Binder already consumed");
            }
            return this.queue.take();
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder != null) {
                try {
                    this.queue.put(iBinder);
                } catch (InterruptedException e) {
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    private static AttributionIdentifiers cacheAndReturnIdentifiers(AttributionIdentifiers attributionIdentifiers) {
        attributionIdentifiers.fetchTime = System.currentTimeMillis();
        recentlyFetchedIdentifiers = attributionIdentifiers;
        return attributionIdentifiers;
    }

    private static AttributionIdentifiers getAndroidId(Context context) {
        AttributionIdentifiers androidIdViaReflection = getAndroidIdViaReflection(context);
        if (androidIdViaReflection != null) {
            return androidIdViaReflection;
        }
        AttributionIdentifiers androidIdViaService = getAndroidIdViaService(context);
        return androidIdViaService == null ? new AttributionIdentifiers() : androidIdViaService;
    }

    private static AttributionIdentifiers getAndroidIdViaReflection(Context context) {
        Object objInvokeMethodQuietly;
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                throw new FacebookException("getAndroidId cannot be called on the main thread.");
            }
            Method methodQuietly = Utility.getMethodQuietly("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", (Class<?>[]) new Class[]{Context.class});
            if (methodQuietly == null) {
                return null;
            }
            Object objInvokeMethodQuietly2 = Utility.invokeMethodQuietly(null, methodQuietly, context);
            if (!(objInvokeMethodQuietly2 instanceof Integer) || ((Integer) objInvokeMethodQuietly2).intValue() != 0) {
                return null;
            }
            Method methodQuietly2 = Utility.getMethodQuietly("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", (Class<?>[]) new Class[]{Context.class});
            if (methodQuietly2 != null && (objInvokeMethodQuietly = Utility.invokeMethodQuietly(null, methodQuietly2, context)) != null) {
                Method methodQuietly3 = Utility.getMethodQuietly(objInvokeMethodQuietly.getClass(), "getId", (Class<?>[]) new Class[0]);
                Method methodQuietly4 = Utility.getMethodQuietly(objInvokeMethodQuietly.getClass(), "isLimitAdTrackingEnabled", (Class<?>[]) new Class[0]);
                if (methodQuietly3 == null || methodQuietly4 == null) {
                    return null;
                }
                AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
                attributionIdentifiers.androidAdvertiserId = (String) Utility.invokeMethodQuietly(objInvokeMethodQuietly, methodQuietly3, new Object[0]);
                attributionIdentifiers.limitTracking = ((Boolean) Utility.invokeMethodQuietly(objInvokeMethodQuietly, methodQuietly4, new Object[0])).booleanValue();
                return attributionIdentifiers;
            }
            return null;
        } catch (Exception e) {
            Utility.logd(TapjoyConstants.TJC_ANDROID_ID, e);
            return null;
        }
    }

    private static AttributionIdentifiers getAndroidIdViaService(Context context) {
        GoogleAdServiceConnection googleAdServiceConnection = new GoogleAdServiceConnection();
        Intent intent = new Intent(AdvertisingInfoServiceStrategy.GOOGLE_PLAY_SERVICES_INTENT);
        intent.setPackage("com.google.android.gms");
        try {
            if (context.bindService(intent, googleAdServiceConnection, 1)) {
                GoogleAdInfo googleAdInfo = new GoogleAdInfo(googleAdServiceConnection.getBinder());
                AttributionIdentifiers attributionIdentifiers = new AttributionIdentifiers();
                attributionIdentifiers.androidAdvertiserId = googleAdInfo.getAdvertiserId();
                attributionIdentifiers.limitTracking = googleAdInfo.isTrackingLimited();
                return attributionIdentifiers;
            }
        } catch (Exception e) {
            Utility.logd(TapjoyConstants.TJC_ANDROID_ID, e);
        } finally {
            context.unbindService(googleAdServiceConnection);
        }
        return null;
    }

    public static AttributionIdentifiers getAttributionIdentifiers(Context context) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.e(TAG, "getAttributionIdentifiers should not be called from the main thread");
        }
        if (recentlyFetchedIdentifiers != null && System.currentTimeMillis() - recentlyFetchedIdentifiers.fetchTime < IDENTIFIER_REFRESH_INTERVAL_MILLIS) {
            return recentlyFetchedIdentifiers;
        }
        AttributionIdentifiers androidId = getAndroidId(context);
        Cursor cursor = null;
        try {
            try {
                String[] strArr = {ATTRIBUTION_ID_COLUMN_NAME, ANDROID_ID_COLUMN_NAME, LIMIT_TRACKING_COLUMN_NAME};
                Uri uri = null;
                if (context.getPackageManager().resolveContentProvider(ATTRIBUTION_ID_CONTENT_PROVIDER, 0) != null) {
                    uri = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
                } else if (context.getPackageManager().resolveContentProvider(ATTRIBUTION_ID_CONTENT_PROVIDER_WAKIZASHI, 0) != null) {
                    uri = Uri.parse("content://com.facebook.wakizashi.provider.AttributionIdProvider");
                }
                String installerPackageName = getInstallerPackageName(context);
                if (installerPackageName != null) {
                    androidId.androidInstallerPackage = installerPackageName;
                }
                if (uri == null) {
                    AttributionIdentifiers attributionIdentifiersCacheAndReturnIdentifiers = cacheAndReturnIdentifiers(androidId);
                    if (0 == 0) {
                        return attributionIdentifiersCacheAndReturnIdentifiers;
                    }
                    cursor.close();
                    return attributionIdentifiersCacheAndReturnIdentifiers;
                }
                Cursor cursorQuery = context.getContentResolver().query(uri, strArr, null, null, null);
                if (cursorQuery == null || !cursorQuery.moveToFirst()) {
                    AttributionIdentifiers attributionIdentifiersCacheAndReturnIdentifiers2 = cacheAndReturnIdentifiers(androidId);
                    if (cursorQuery == null) {
                        return attributionIdentifiersCacheAndReturnIdentifiers2;
                    }
                    cursorQuery.close();
                    return attributionIdentifiersCacheAndReturnIdentifiers2;
                }
                int columnIndex = cursorQuery.getColumnIndex(ATTRIBUTION_ID_COLUMN_NAME);
                int columnIndex2 = cursorQuery.getColumnIndex(ANDROID_ID_COLUMN_NAME);
                int columnIndex3 = cursorQuery.getColumnIndex(LIMIT_TRACKING_COLUMN_NAME);
                androidId.attributionId = cursorQuery.getString(columnIndex);
                if (columnIndex2 > 0 && columnIndex3 > 0 && androidId.getAndroidAdvertiserId() == null) {
                    androidId.androidAdvertiserId = cursorQuery.getString(columnIndex2);
                    androidId.limitTracking = Boolean.parseBoolean(cursorQuery.getString(columnIndex3));
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return cacheAndReturnIdentifiers(androidId);
            } catch (Exception e) {
                Utility.logd(TAG, "Caught unexpected exception in getAttributionId(): " + e.toString());
                if (0 == 0) {
                    return null;
                }
                cursor.close();
                return null;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    @Nullable
    private static String getInstallerPackageName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            return packageManager.getInstallerPackageName(context.getPackageName());
        }
        return null;
    }

    public String getAndroidAdvertiserId() {
        return this.androidAdvertiserId;
    }

    public String getAndroidInstallerPackage() {
        return this.androidInstallerPackage;
    }

    public String getAttributionId() {
        return this.attributionId;
    }

    public boolean isTrackingLimited() {
        return this.limitTracking;
    }
}
