package com.facebook.marketing.internal;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.marketing.CodelessActivityLifecycleTracker;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class MarketingInitProvider extends ContentProvider {
    private static final String TAG = MarketingInitProvider.class.getSimpleName();

    /* JADX INFO: Access modifiers changed from: private */
    public void setupCodeless() {
        CodelessActivityLifecycleTracker.startTracking((Application) FacebookSdk.getApplicationContext(), FacebookSdk.getApplicationId());
        RemoteConfigManager.loadRemoteConfig();
        new MarketingLogger((Application) FacebookSdk.getApplicationContext(), FacebookSdk.getApplicationId()).logCodelessInitialized();
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        try {
            if (FacebookSdk.isInitialized()) {
                setupCodeless();
            } else {
                FacebookSdk.sdkInitialize(getContext(), new FacebookSdk.InitializeCallback() { // from class: com.facebook.marketing.internal.MarketingInitProvider.1
                    @Override // com.facebook.FacebookSdk.InitializeCallback
                    public void onInitialized() {
                        MarketingInitProvider.this.setupCodeless();
                    }
                });
            }
            return false;
        } catch (Exception e) {
            Log.i(TAG, "Failed to auto initialize the Marketing SDK", e);
            return false;
        }
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
