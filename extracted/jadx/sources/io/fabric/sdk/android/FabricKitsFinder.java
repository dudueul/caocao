package io.fabric.sdk.android;

import android.os.SystemClock;
import android.text.TextUtils;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
class FabricKitsFinder implements Callable<Map<String, KitInfo>> {
    private static final String FABRIC_BUILD_TYPE_KEY = "fabric-build-type";
    static final String FABRIC_DIR = "fabric/";
    private static final String FABRIC_IDENTIFIER_KEY = "fabric-identifier";
    private static final String FABRIC_VERSION_KEY = "fabric-version";
    final String apkFileName;

    FabricKitsFinder(String str) {
        this.apkFileName = str;
    }

    private Map<String, KitInfo> findImplicitKits() {
        HashMap map = new HashMap();
        try {
            Class.forName("com.google.android.gms.ads.AdView");
            KitInfo kitInfo = new KitInfo("com.google.firebase.firebase-ads", "0.0.0", "binary");
            map.put(kitInfo.getIdentifier(), kitInfo);
            Fabric.getLogger().v(Fabric.TAG, "Found kit: com.google.firebase.firebase-ads");
        } catch (Exception e) {
        }
        return map;
    }

    private Map<String, KitInfo> findRegisteredKits() throws Exception {
        KitInfo kitInfoLoadKitInfo;
        HashMap map = new HashMap();
        ZipFile zipFileLoadApkFile = loadApkFile();
        Enumeration<? extends ZipEntry> enumerationEntries = zipFileLoadApkFile.entries();
        while (enumerationEntries.hasMoreElements()) {
            ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
            if (zipEntryNextElement.getName().startsWith(FABRIC_DIR) && zipEntryNextElement.getName().length() > FABRIC_DIR.length() && (kitInfoLoadKitInfo = loadKitInfo(zipEntryNextElement, zipFileLoadApkFile)) != null) {
                map.put(kitInfoLoadKitInfo.getIdentifier(), kitInfoLoadKitInfo);
                Fabric.getLogger().v(Fabric.TAG, String.format("Found kit:[%s] version:[%s]", kitInfoLoadKitInfo.getIdentifier(), kitInfoLoadKitInfo.getVersion()));
            }
        }
        if (zipFileLoadApkFile != null) {
            try {
                zipFileLoadApkFile.close();
            } catch (IOException e) {
            }
        }
        return map;
    }

    private KitInfo loadKitInfo(ZipEntry zipEntry, ZipFile zipFile) {
        try {
            try {
                InputStream inputStream = zipFile.getInputStream(zipEntry);
                Properties properties = new Properties();
                properties.load(inputStream);
                String property = properties.getProperty(FABRIC_IDENTIFIER_KEY);
                String property2 = properties.getProperty(FABRIC_VERSION_KEY);
                String property3 = properties.getProperty(FABRIC_BUILD_TYPE_KEY);
                if (TextUtils.isEmpty(property) || TextUtils.isEmpty(property2)) {
                    throw new IllegalStateException("Invalid format of fabric file," + zipEntry.getName());
                }
                KitInfo kitInfo = new KitInfo(property, property2, property3);
                CommonUtils.closeQuietly(inputStream);
                return kitInfo;
            } catch (IOException e) {
                Fabric.getLogger().e(Fabric.TAG, "Error when parsing fabric properties " + zipEntry.getName(), e);
                CommonUtils.closeQuietly(null);
                return null;
            }
        } catch (Throwable th) {
            CommonUtils.closeQuietly(null);
            throw th;
        }
    }

    @Override // java.util.concurrent.Callable
    public Map<String, KitInfo> call() throws Exception {
        HashMap map = new HashMap();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        map.putAll(findImplicitKits());
        map.putAll(findRegisteredKits());
        Fabric.getLogger().v(Fabric.TAG, "finish scanning in " + (SystemClock.elapsedRealtime() - jElapsedRealtime));
        return map;
    }

    protected ZipFile loadApkFile() throws IOException {
        return new ZipFile(this.apkFileName);
    }
}
