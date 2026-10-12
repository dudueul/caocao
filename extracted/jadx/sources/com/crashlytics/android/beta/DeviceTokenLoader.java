package com.crashlytics.android.beta;

import android.content.Context;
import android.content.pm.PackageManager;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.cache.ValueLoader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class DeviceTokenLoader implements ValueLoader<String> {
    private static final String BETA_APP_PACKAGE_NAME = "io.crash.air";
    private static final String DIRFACTOR_DEVICE_TOKEN_PREFIX = "assets/com.crashlytics.android.beta/dirfactor-device-token=";

    String determineDeviceToken(ZipInputStream zipInputStream) throws IOException {
        ZipEntry nextEntry = zipInputStream.getNextEntry();
        if (nextEntry != null) {
            String name = nextEntry.getName();
            if (name.startsWith(DIRFACTOR_DEVICE_TOKEN_PREFIX)) {
                return name.substring(DIRFACTOR_DEVICE_TOKEN_PREFIX.length(), name.length() - 1);
            }
        }
        return "";
    }

    ZipInputStream getZipInputStreamOfApkFrom(Context context, String str) throws PackageManager.NameNotFoundException, FileNotFoundException {
        return new ZipInputStream(new FileInputStream(context.getPackageManager().getApplicationInfo(str, 0).sourceDir));
    }

    @Override // io.fabric.sdk.android.services.cache.ValueLoader
    public String load(Context context) throws Exception {
        long jNanoTime = System.nanoTime();
        String strDetermineDeviceToken = "";
        ZipInputStream zipInputStreamOfApkFrom = null;
        try {
            try {
                try {
                    try {
                        zipInputStreamOfApkFrom = getZipInputStreamOfApkFrom(context, "io.crash.air");
                        strDetermineDeviceToken = determineDeviceToken(zipInputStreamOfApkFrom);
                        if (zipInputStreamOfApkFrom != null) {
                            try {
                                zipInputStreamOfApkFrom.close();
                            } catch (IOException e) {
                                Fabric.getLogger().e(Beta.TAG, "Failed to close the APK file", e);
                            }
                        }
                    } catch (Throwable th) {
                        if (zipInputStreamOfApkFrom != null) {
                            try {
                                zipInputStreamOfApkFrom.close();
                            } catch (IOException e2) {
                                Fabric.getLogger().e(Beta.TAG, "Failed to close the APK file", e2);
                            }
                        }
                        throw th;
                    }
                } catch (PackageManager.NameNotFoundException e3) {
                    Fabric.getLogger().d(Beta.TAG, "Beta by Crashlytics app is not installed");
                    if (zipInputStreamOfApkFrom != null) {
                        try {
                            zipInputStreamOfApkFrom.close();
                        } catch (IOException e4) {
                            Fabric.getLogger().e(Beta.TAG, "Failed to close the APK file", e4);
                        }
                    }
                }
            } catch (FileNotFoundException e5) {
                Fabric.getLogger().e(Beta.TAG, "Failed to find the APK file", e5);
                if (zipInputStreamOfApkFrom != null) {
                    try {
                        zipInputStreamOfApkFrom.close();
                    } catch (IOException e6) {
                        Fabric.getLogger().e(Beta.TAG, "Failed to close the APK file", e6);
                    }
                }
            }
        } catch (IOException e7) {
            Fabric.getLogger().e(Beta.TAG, "Failed to read the APK file", e7);
            if (zipInputStreamOfApkFrom != null) {
                try {
                    zipInputStreamOfApkFrom.close();
                } catch (IOException e8) {
                    Fabric.getLogger().e(Beta.TAG, "Failed to close the APK file", e8);
                }
            }
        }
        Fabric.getLogger().d(Beta.TAG, "Beta device token load took " + ((System.nanoTime() - jNanoTime) / 1000000.0d) + "ms");
        return strDetermineDeviceToken;
    }
}
